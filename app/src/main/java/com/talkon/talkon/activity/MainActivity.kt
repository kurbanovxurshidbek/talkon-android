package com.talkon.talkon.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.talkon.talkon.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}