package com.example.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.mirrarrassigment.R
import com.example.mirrarrassigment.base.BaseActivity
import com.example.mirrarrassigment.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init(){
        val anim: Animation = AnimationUtils.loadAnimation(baseContext, R.anim.zoom)

        binding.nasaLogo.animation=anim



        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                val mHandler = Handler(mainLooper)
                val mRunnable = Runnable {
                    finish()
                    val i = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(i)
                    rightToLeftMove()
                }
                mHandler.postDelayed(mRunnable, 2500)
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })

    }
}