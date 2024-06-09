package com.student151915_151917.mobilki

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val carImageView: ImageView = findViewById(R.id.car)
        val mountainsImageView: ImageView = findViewById(R.id.montains)

        // Wait for the layout to be measured
        carImageView.post {
            // Calculate the distance to move
            val distance = mountainsImageView.x - carImageView.x - carImageView.width / 2

            // Create and start the animation
            val animator = ObjectAnimator.ofFloat(carImageView, "translationX", 0f, distance)
            animator.duration = 2000 // Duration in milliseconds
            animator.start()

            // Set a listener to start the next activity when the animation ends
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
        }
    }
}