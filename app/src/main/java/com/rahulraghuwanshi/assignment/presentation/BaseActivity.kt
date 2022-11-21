package com.rahulraghuwanshi.assignment.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rahulraghuwanshi.assignment.R

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}