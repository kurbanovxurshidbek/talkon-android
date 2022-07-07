package com.talkon.uz.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import com.talkon.uz.R
import kotlinx.android.synthetic.main.activity_balance.*


/*** In BalanceActivity, user can fill balance and see balance history */

class BalanceActivity : BaseActivity() {

    private var isClicked : Boolean = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance)

        bt_plus_coins.setOnClickListener {
            isClicked = true
            if (isClicked){
                bt_buy_coins.text = "Pay"
            }
            try {
                val presentValStr: String = tv_balance_count.text.toString()
                var presentIntVal = presentValStr.toInt()
                presentIntVal += 5
                tv_balance_count.text = presentIntVal.toString()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Some error :(", Toast.LENGTH_LONG).show()
            }
        }

        bt_minus_coins.setOnClickListener {
            try {
                val presentValStr: String = tv_balance_count.text.toString()
                var presentIntVal = presentValStr.toInt()

                if (presentIntVal>0){
                    presentIntVal -= 5
                }
                tv_balance_count.text = presentIntVal.toString()
                if (presentIntVal == 0) {
                    bt_buy_coins.text = "Buy coin"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Some error :(", Toast.LENGTH_LONG).show()
            }
        }

        bt_buy_coins.setOnClickListener {

            Toast.makeText(context, "You have got ${tv_balance_count.text} coins :)",
                Toast.LENGTH_SHORT).show()

        }

    }



}