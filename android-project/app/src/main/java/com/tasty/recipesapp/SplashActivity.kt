package com.tasty.recipesapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.tasty.recipesapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: SplashActivity created.")

//        binding.button.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("message", binding.splashEditText.text.toString())
//            startActivity(intent)
//            finish()
//        }

        // Use a HandlerThread to create a background thread
        val handlerThread = HandlerThread("SplashHandlerThread", -10)
        handlerThread.start() // Create a Handler on the new HandlerThread
        val handler = Handler(handlerThread.looper)
        val SPLASH_TIME_OUT = 2000
        handler.postDelayed(
            {
                // Navigate to MainActivity after the delay
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            SPLASH_TIME_OUT.toLong()
        )


//        Handler().postDelayed({
//            val mainIntent = Intent(this, MainActivity::class.java)
//            startActivity(mainIntent)
//            finish()
//        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: SplashActivity destroyed.")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: SplashActivity started.")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: SplashActivity resumed.")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: SplashActivity paused.")
    }


}