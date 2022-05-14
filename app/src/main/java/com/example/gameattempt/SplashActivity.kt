package com.example.gameattempt

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnticipateInterpolator
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import com.bumptech.glide.Glide

class SplashActivity : AppCompatActivity() {
    lateinit var  imageView: ImageView //declaring the imageView in the xml file
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        imageView=findViewById(R.id.imageView) //binding it to the imageView via its ID.
        /*using the glide library and its methods to load the gif into the imageView*/
        Glide.with(this).load(R.raw.gif).into(imageView)
//        This is used to hide the status bar and make
//         the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)

    }
}