package com.example.mirrarrassigment.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mirrarrassigment.R

open class BaseFragment:Fragment() {
    fun toastMsg(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun rightToLeftMove() {
        activity?.overridePendingTransition(
            R.anim.slide_in_right, R.anim.slide_out_left
        );
    }

    fun leftToRightMove() {
        activity?.overridePendingTransition(
            R.anim.slide_in_left, R.anim.slide_out_right
        );
    }
}