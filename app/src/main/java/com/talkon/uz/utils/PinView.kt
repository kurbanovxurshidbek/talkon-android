package com.talkon.uz.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputConnectionWrapper
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.Nullable
import com.talkon.uz.R


class PinView : LinearLayout {
    private var mPinCount = 0
    private var mPinSize = 0
    private var mPinWidth = 0
    private var mPinHeight = 0
    private var mPinTextSize = 0
    private var mPinGap = 0
    private var mPinTextColor = 0
    private var mPinTextColorSelected = 0
    private var mPinBackground = 0
    private var mPinBackgroundFilled = 0
    private var mPinTypeface: Typeface? = null
    private var mKeyboardManager: InputMethodManager? = null
    private var currentPin = 0
    private var mPinCompletedListener: OnPinCompletedListener? = null

    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(context: Context?, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        mKeyboardManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.PinView)
            val fontName = a.getString(R.styleable.PinView_pFont)
            mPinCount = a.getInt(R.styleable.PinView_pCount, 6)
            mPinSize = a.getDimensionPixelSize(R.styleable.PinView_pSize, 100)
            mPinWidth = a.getDimensionPixelSize(R.styleable.PinView_pWidth, -1)
            mPinHeight = a.getDimensionPixelSize(R.styleable.PinView_pHeight, -1)
            mPinGap = a.getDimensionPixelSize(R.styleable.PinView_pGap, 30)
            mPinTextSize = a.getDimensionPixelSize(R.styleable.PinView_pTextSize, 16)
            mPinTextColor = a.getColor(R.styleable.PinView_pTextColor, Color.WHITE)
            mPinTextColorSelected = a.getColor(R.styleable.PinView_pTextColorSelected, Color.BLACK)
            mPinBackground =
                a.getResourceId(R.styleable.PinView_pBackground, R.drawable.background_pin)
            mPinBackgroundFilled = a.getResourceId(R.styleable.PinView_pBackgroundFilled,
                R.drawable.background_pin_filled)
            try {
                if (fontName != null) {
                    mPinTypeface = Typeface.createFromAsset(context.assets,
                        "fonts/$fontName")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                mPinTypeface = null
            }
            a.recycle()
        }
        orientation = HORIZONTAL
        createPins()
    }

    private fun createPins() {
        for (i in 0 until mPinCount) {
            val pinEditText: PinEditText = PinEditText(context)
            pinEditText.id = i
            if (mPinTypeface != null) pinEditText.setTypeface(mPinTypeface)
            pinEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mPinTextSize.toFloat())
            pinEditText.gravity = Gravity.CENTER
            pinEditText.setTextColor(mPinTextColor)
            pinEditText.inputType =
                InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            pinEditText.filters = arrayOf<InputFilter>(LengthFilter(1))
            pinEditText.background = getDrawable(mPinBackground)
            pinEditText.isCursorVisible = false
            pinEditText.setPadding(0, 0, 0, 0)
            if (mPinWidth == -1 || mPinHeight == -1) {
                mPinWidth = mPinSize
                mPinHeight = mPinSize
            }
            val params = LayoutParams(mPinWidth, mPinHeight)
            if (i != 0) params.setMargins(mPinGap, 0, 0, 0)
            pinEditText.layoutParams = params
            addView(pinEditText)
        }
    }

    private fun getDrawable(resourceId: Int): Drawable? {
        return resources.getDrawable(resourceId)
    }

    override fun setOrientation(orientation: Int) {
        super.setOrientation(HORIZONTAL)
    }

    private fun nextPin(): PinEditText? {
        return findViewById(currentPin + 1)
    }

    private fun previousPin(): PinEditText? {
        return findViewById(currentPin - 1)
    }

    fun setPinCompletedListener(listener: OnPinCompletedListener?) {
        mPinCompletedListener = listener
    }

    /**
     * @return A [String] containing pin or null if all fields are not filled
     */
    val pin: String?
        get() {
            val stringBuilder = StringBuilder()
            for (i in 0 until mPinCount) {
                val pinEditText: PinEditText = findViewById(i)
                if (pinEditText.text.toString().isEmpty()) {
                    return null
                } else {
                    stringBuilder.append(pinEditText.text.toString())
                }
            }
            return stringBuilder.toString()
        }

    /**
     * Sets a pin to the [PinView]. Does nothing if the Pin length and pin count doesn't match
     *
     * @param pin Pin to show in the PinView
     */
    @SuppressLint("SetTextI18n")
    fun setPin(pin: String) {
        val pins = pin.toCharArray()
        if (pins.size == mPinCount) for (i in pins.indices) {
            val pinEditText: PinEditText = findViewById(i)
            val pinValue = pins[i].toString()
            pinEditText.setText(pinValue)
        }
    }

    @SuppressLint("AppCompatCustomView")
    private inner class PinEditText(context: Context?) :
        EditText(context) {
        override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
            return CustomInputConnection(super.onCreateInputConnection(outAttrs),
                true)
        }

        /**
         * Sets the cursor at the end of the EditText
         */
        private fun setCursorAtEnd() {
            if (!text.toString().isEmpty()) {
                setSelection(1)
            }
        }

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouchEvent(event: MotionEvent): Boolean {
            super.onTouchEvent(event)
            setCursorAtEnd()
            return true
        }

        override fun onFocusChanged(
            focused: Boolean,
            direction: Int,
            previouslyFocusedRect: Rect?,
        ) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect)
            if (focused) {
                currentPin = id // if the EditText is currently focused set it to currentPin
            }
            if (getDrawable(mPinBackground) != null && getDrawable(mPinBackgroundFilled) != null) {
                if (focused) {
                    setCursorAtEnd()
                    background = null
                    background = getDrawable(mPinBackground)
                    setTextColor(mPinTextColorSelected)
                } else {
                    if (text.toString().isEmpty()) {
                        background = null
                        background = getDrawable(mPinBackground)
                    } else {
                        background = null
                        background = getDrawable(mPinBackgroundFilled)
                        setTextColor(mPinTextColor)
                    }
                }
            }
        }

        override fun onTextChanged(
            text: CharSequence,
            start: Int,
            lengthBefore: Int,
            lengthAfter: Int,
        ) {
            super.onTextChanged(text, start, lengthBefore, lengthAfter)
            if (getDrawable(mPinBackground) != null && getDrawable(mPinBackgroundFilled) != null) {
                if (getText().toString().isEmpty()) {
                    background = null
                    background = getDrawable(mPinBackground)
                } else {
                    setTextColor(mPinTextColor)
                    background = null
                    background = getDrawable(mPinBackgroundFilled)
                    if (nextPin() != null) { // There's a next pin
                        if (!nextPin()!!.text.toString().isEmpty()) { // the next pin isn't empty
                            clearAllFocus()
                            mKeyboardManager!!.hideSoftInputFromWindow(windowToken, 0)
                        } else { // the next pin is empty, and it'll get focus
                            nextPin()!!.requestFocus()
                        }
                    } else { // No more pin so clear all focus
                        clearAllFocus()
                        mKeyboardManager!!.hideSoftInputFromWindow(windowToken, 0)
                    }
                    val pin = pin
                    if (pin != null && pin.length == mPinCount) {
                        mPinCompletedListener!!.onPinCompleted(pin)
                    }
                }
            }
        }

        /**
         * Clears all focus from the PinView
         */
        private fun clearAllFocus() {
            val rootView = rootView as ViewGroup
            val dfValue = rootView.descendantFocusability
            rootView.descendantFocusability = FOCUS_BLOCK_DESCENDANTS
            clearFocus()
            rootView.descendantFocusability = dfValue
        }

        private inner class CustomInputConnection internal constructor(
            target: InputConnection?,
            mutable: Boolean,
        ) :
            InputConnectionWrapper(target, mutable) {
            override fun sendKeyEvent(event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN
                    && event.keyCode == KeyEvent.KEYCODE_DEL
                ) {
                    if (text.toString().isEmpty()) {
                        if (previousPin() != null) {
                            previousPin()!!.requestFocus()
                            return false // avoid from triggering the delete twice
                        } else {
                            clearAllFocus()
                            mKeyboardManager!!.hideSoftInputFromWindow(windowToken, 0)
                        }
                    }
                } else if (event.action == KeyEvent.ACTION_DOWN && !text.toString().isEmpty()) {
                    setText(event.number.toString())
                    return false // avoid from setting text twice
                }
                return super.sendKeyEvent(event)
            }

            override fun deleteSurroundingText(beforeLength: Int, afterLength: Int): Boolean {
                return if (beforeLength == 1 && afterLength == 0) {
                    (sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                            && sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL)))
                } else super.deleteSurroundingText(beforeLength, afterLength)
            }
        }
    }

    interface OnPinCompletedListener {
        fun onPinCompleted(pin: String?)
    }
}