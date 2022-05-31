package com.talkon.talkon.activity

import android.os.Bundle
import android.widget.Toast
import com.talkon.talkon.R
import kotlinx.android.synthetic.main.activity_balance.*


/**
 * In BalanceActivity, user can fill balance and see balance history
 */

class BalanceActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance)

        bt_buy_coins.setOnClickListener {
            try {
                val presentValStr: String = tv_balance_count.getText().toString()
                var presentIntVal = presentValStr.toInt()
                presentIntVal += 20
                tv_balance_count.setText(presentIntVal.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Some error :(", Toast.LENGTH_LONG).show()
            }
        }

    }


}