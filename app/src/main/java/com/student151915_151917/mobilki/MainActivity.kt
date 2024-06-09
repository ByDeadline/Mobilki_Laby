package com.student151915_151917.mobilki

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.student151915_151917.mobilki.databinding.ActivityMainBinding
import kotlin.random.Random

object Constants {
    fun getRandomImageSource(): Int {
        return if (Random.nextBoolean()) {
            R.drawable.gorski_szlak
        } else {
            R.drawable.gorski_szlak2
        }
    }
    fun getTrails(): List<Trail> {
        return listOf(
            Trail(
            name = "Szlak Orlich Gniazd",
            length = 164.0,
            difficulty = TrailDifficulty.Easy,
            description = "Szlak turystyczny wiodący przez ruiny średniowiecznych zamków i warowni na Wyżynie Krakowsko-Częstochowskiej.",
            imageSource = getRandomImageSource())
        ,
        Trail(
            name = "Główny Szlak Beskidzki",
            length = 496.0,
            difficulty = TrailDifficulty.Hard,
            description = "Najdłuższy pieszy szlak w polskich górach, biegnący przez Beskidy od Ustronia do Wołosatego.",
            imageSource = getRandomImageSource()
        ),
        Trail(
            name = "Szlak Piastowski",
            length = 146.0,
            difficulty = TrailDifficulty.Easy,
            description = "Szlak turystyczny prowadzący przez zamki i pałace Dolnego Śląska, związane z dynastią Piastów.",
            imageSource = getRandomImageSource()
        ),
        Trail(
            name = "Szlak Wokół Tatr",
            length = 250.0,
            difficulty = TrailDifficulty.Easy,
            description = "Szlak rowerowy prowadzący wokół Tatr, umożliwiający podziwianie pięknych widoków i krajobrazów.",
            imageSource = getRandomImageSource()
        ),
        Trail(
            name = "Główny Szlak Sudecki",
            length = 440.0,
            difficulty = TrailDifficulty.Hard,
            description = "Szlak turystyczny biegnący przez Sudety, od Świeradowa-Zdroju do Prudnika.",
            imageSource = getRandomImageSource()
        ),
        Trail(
            name = "Szlak Nadmorski",
            length = 370.0,
            difficulty = TrailDifficulty.Easy,
            description = "Szlak turystyczny biegnący wzdłuż wybrzeża Bałtyku, od Świnoujścia do Helu.",
            imageSource = getRandomImageSource()
        ),
        Trail(
            name = "Szlak Kopernikowski",
            length = 238.0,
            difficulty = TrailDifficulty.Easy,
            description = "Szlak turystyczny prowadzący przez miejsca związane z życiem i działalnością Mikołaja Kopernika.",
            imageSource = getRandomImageSource()
        ),
        Trail(
            name = "Szlak Bursztynowy",
            length = 450.0,
            difficulty = TrailDifficulty.Easy,
            description = "Szlak turystyczny biegnący wzdłuż historycznego traktu handlowego, łączącego wybrzeże Bałtyku z południem Europy.",
            imageSource = getRandomImageSource()
        ),
        Trail(
            name = "Szlak Papieski",
            length = 120.0,
            difficulty = TrailDifficulty.Easy,
            description = "Szlak turystyczny upamiętniający wędrówki Karola Wojtyły po polskich górach.",
            imageSource = getRandomImageSource()
        ),
        Trail(
            name = "Szlak Świętego Jakuba",
            length = 640.0,
            difficulty = TrailDifficulty.Hard,
            description = "Polska część europejskiego szlaku pielgrzymkowego do Santiago de Compostela.",
            imageSource = getRandomImageSource()
        ))
    }
}
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val trails = Constants.getTrails()

    private enum class Tabs {
        Home,
        Easy,
        Hard
    }

    private val tabs = mapOf(
            0 to Tabs.Home,
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
        val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayUseLogoEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        this.setupAdapter()
        this.setupTabLayout()

    }

    private fun setupAdapter() {
        this.changeAdapter(this.trails)
        binding.trailsList.layoutManager = GridLayoutManager(applicationContext, 2)
    }

    private fun homeTab() {
        this.binding.helloLayout.visibility = VISIBLE
        this.binding.trailsList.visibility = GONE
        this.changeAdapter(this.trails)
    }

    private fun easyTab() {
        this.binding.helloLayout.visibility = GONE
        this.binding.trailsList.visibility = VISIBLE
        this.changeAdapter(this.trails.filter { trail: Trail -> trail.getTrialDifficultyEnum() == TrailDifficulty.Easy })
    }

    private fun hardTab() {
        this.binding.helloLayout.visibility = GONE
        this.binding.trailsList.visibility = VISIBLE
        this.changeAdapter(this.trails.filter { trail: Trail -> trail.getTrialDifficultyEnum() == TrailDifficulty.Hard })
    }

    private fun setupTabLayout() {
        val thisActivity = this
        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // Handle tab selected
                val position = thisActivity.tabs[tab.position]
                when (position) {
                    Tabs.Home -> thisActivity.homeTab()
                    Tabs.Easy -> thisActivity.easyTab()
                    Tabs.Hard -> thisActivity.hardTab()
                    null -> thisActivity.homeTab()
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
        val intent = Intent(applicationContext, TrailView::class.java)
        intent.putExtra("trail",trail)
        startActivity(intent)
    }
}