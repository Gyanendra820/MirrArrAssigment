package com.example.mirrarrassigment.utils

import android.view.View
import android.widget.Toast

object CommonHelper {

    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide() {
        this.visibility = View.GONE
    }

}