package com.aflah.pokedex.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.aflah.pokedex.R

class SplashActivity : AppCompatActivity() {

    private val handler: Handler by lazy { Handler() }
    private var runnable: Runnable = Runnable {
        openNextActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler.postDelayed(runnable, SPLASH_TIME.toLong())
    }

    private fun openNextActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val SPLASH_TIME = 2500
    }
}