package com.example.mirrarrassigment.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mirrarrassigment.R

open class BaseActivity:AppCompatActivity() {
    fun toastMsg(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun rightToLeftMove() {
        overridePendingTransition(
            R.anim.slide_in_right, R.anim.slide_out_left
        );
    }

    fun leftToRightMove() {
        overridePendingTransition(
            R.anim.slide_in_left, R.anim.slide_out_right
        );
    }
}