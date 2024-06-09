package com.student151915_151917.mobilki

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.Tab
import com.student151915_151917.mobilki.databinding.ActivityMainBinding

object Constants {
    fun getTrails(): List<Trail> {
        return listOf(
            Trail("Trail 1", 5.0, TrailDifficulty.Easy, "Trail 1 description"),
            Trail("Trail 2", 10.0, TrailDifficulty.Hard, "Trail 2 description"),
            Trail("Trail 3", 15.0, TrailDifficulty.Hard, "Trail 3 description"),
            Trail("Trail 4", 20.0, TrailDifficulty.Easy, "Trail 4 description"),
            Trail("Trail 5", 25.0, TrailDifficulty.Hard, "Trail 5 description"),
            Trail("Trail 6", 30.0, TrailDifficulty.Hard, "Trail 6 description"),
            Trail("Trail 7", 35.0, TrailDifficulty.Easy, "Trail 7 description"),
            Trail("Trail 8", 40.0, TrailDifficulty.Hard, "Trail 8 description"),
            Trail("Trail 9", 45.0, TrailDifficulty.Hard, "Trail 9 description"),
            Trail("Trail 10", 50.0, TrailDifficulty.Easy, "Trail 10 description"),
            Trail("Trail 11", 55.0, TrailDifficulty.Hard, "Trail 11 description"),
            Trail("Trail 12", 60.0, TrailDifficulty.Hard, "Trail 12 description"),
            Trail("Trail 13", 65.0, TrailDifficulty.Easy, "Trail 13 description"),
            Trail("Trail 14", 70.0, TrailDifficulty.Hard, "Trail 14 description"),
            Trail("Trail 15", 75.0, TrailDifficulty.Hard, "Trail 15 description"),
            Trail("Trail 16", 80.0, TrailDifficulty.Easy, "Trail 16 description"),
            Trail("Trail 17", 85.0, TrailDifficulty.Hard, "Trail 17 description"),
            Trail("Trail 18", 90.0, TrailDifficulty.Hard, "Trail 18 description"),
            Trail("Trail 19", 95.0, TrailDifficulty.Easy, "Trail 19 description"),
            Trail("Trail 20", 100.0, TrailDifficulty.Hard, "Trail 20 description")
        )
    }
}
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val trails = Constants.getTrails()

    private enum class Tabs {
        All,
        Easy,
        Hard
    }

    private val tabs = mapOf(
            0 to Tabs.All,
            1 to Tabs.Easy,
            2 to Tabs.Hard
    )

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

        this.setupAdapter()
        this.setupTabLayout()
    }

    private fun setupAdapter() {
        this.changeAdapter(this.trails)
        binding.trailsList.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun allTab() {
        this.changeAdapter(this.trails)
    }

    private fun easyTab() {
        this.changeAdapter(this.trails.filter { trail: Trail -> trail.getTrialDifficultyEnum() == TrailDifficulty.Easy })
    }

    private fun hardTab() {
        this.changeAdapter(this.trails.filter { trail: Trail -> trail.getTrialDifficultyEnum() == TrailDifficulty.Hard })
    }

    private fun setupTabLayout() {
        val thisActivity = this
        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // Handle tab selected
                val position = thisActivity.tabs[tab.position]
                when (position) {
                    Tabs.All -> thisActivity.allTab()
                    Tabs.Easy -> thisActivity.easyTab()
                    Tabs.Hard -> thisActivity.hardTab()
                    null -> thisActivity.allTab()
                }

                // You can perform actions based on the tab position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Handle tab unselected
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Handle tab reselected
            }
        })
    }

    private fun changeAdapter(trails : List<Trail>) {
        val adapter = TrailAdapter(trails,::trailOnClick)
        binding.trailsList.adapter = adapter
    }

    private fun trailOnClick(trail : Trail){
        val intent = Intent(applicationContext,TrailView::class.java)
        intent.putExtra("trail",trail)
        startActivity(intent)
    }
}