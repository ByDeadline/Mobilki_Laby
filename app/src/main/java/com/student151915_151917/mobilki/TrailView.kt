package com.student151915_151917.mobilki

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.student151915_151917.mobilki.databinding.ActivityTrailViewBinding

class TrailView : AppCompatActivity() {
    private lateinit var trail: Trail
    private lateinit var binding: ActivityTrailViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = ActivityTrailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        fillData()
    }

    private fun fillData() {
        this.trail = intent.getSerializableExtra("trail", Trail::class.java)!!

        this.binding.trailName.text = this.trail.name
        this.binding.trailDiff.text = this.trail.getTrailDifficulty()
        this.binding.trailDiscription.text = this.trail.description
        this.binding.trailLen.text = this.trail.length.toString()
    }

    fun startClock() {

    }
    fun pauseClock() {

    }
    fun resetClock() {

    }
}