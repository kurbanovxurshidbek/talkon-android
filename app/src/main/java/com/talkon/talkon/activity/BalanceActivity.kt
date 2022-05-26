package com.talkon.talkon.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.talkon.talkon.R

/**
 * In BalanceActivity, user can fill balance and see balance history
 */

class BalanceActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance)
    }
}