package com.student151915_151917.mobilki

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.student151915_151917.mobilki.databinding.ActivityMainBinding

data class Trail(val name: String, val length: String, val difficulty: String, val description: String)

object Constants {
    fun getTrails(): List<Trail> {
        val trails = listOf(
            Trail("Trail 1", "5 km", "easy", "Trail 1 description"),
            Trail("Trail 2", "10 km", "medium", "Trail 2 description"),
            Trail("Trail 3", "15 km", "hard", "Trail 3 description"),
            Trail("Trail 4", "20 km", "easy", "Trail 4 description"),
            Trail("Trail 5", "25 km", "medium", "Trail 5 description"),
            Trail("Trail 6", "30 km", "hard", "Trail 6 description"),
            Trail("Trail 7", "35 km", "easy", "Trail 7 description"),
            Trail("Trail 8", "40 km", "medium", "Trail 8 description"),
            Trail("Trail 9", "45 km", "hard", "Trail 9 description"),
            Trail("Trail 10", "50 km", "easy", "Trail 10 description"),
            Trail("Trail 11", "55 km", "medium", "Trail 11 description"),
            Trail("Trail 12", "60 km", "hard", "Trail 12 description"),
            Trail("Trail 13", "65 km", "easy", "Trail 13 description"),
            Trail("Trail 14", "70 km", "medium", "Trail 14 description"),
            Trail("Trail 15", "75 km", "hard", "Trail 15 description"),
            Trail("Trail 16", "80 km", "easy", "Trail 16 description"),
            Trail("Trail 17", "85 km", "medium", "Trail 17 description"),
            Trail("Trail 18", "90 km", "hard", "Trail 18 description"),
            Trail("Trail 19", "95 km", "easy", "Trail 19 description"),
            Trail("Trail 20", "100 km", "medium", "Trail 20 description"),
        )
        return trails
    }
}
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val trailsRecyclerView = Constants.getTrails()
        println(trailsRecyclerView)
        val adapter = TrailAdapter(trailsRecyclerView)
        binding.trailsList.adapter = adapter
        binding.trailsList.layoutManager = LinearLayoutManager(applicationContext)
    }
}