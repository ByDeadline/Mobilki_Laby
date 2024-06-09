package com.student151915_151917.mobilki

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.student151915_151917.mobilki.databinding.ActivityTrailViewBinding

class TrailView : AppCompatActivity(), StopwatchInterface {
    private lateinit var trail: Trail
    private lateinit var binding: ActivityTrailViewBinding
    private lateinit var trailTime: TrailTime
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = ActivityTrailViewBinding.inflate(layoutInflater)
        drawerLayout = binding.drawerLayout
        navigationView = binding.navView

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle item selection here
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Handle the home action
                }
                R.id.nav_gallery -> {
                    // Handle the gallery action
                }
                R.id.nav_slideshow -> {
                    // Handle the slideshow action
                }
            }

            // Close the drawer after selection
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        binding.fab.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(cameraIntent)
        }
        fillData()
    }
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun fillData() {
        this.trail = intent.getSerializableExtra("trail", Trail::class.java)!!

        this.binding.trailName.text = this.trail.name
        this.binding.trailDiff.text = this.trail.getTrailDifficulty()
        this.binding.trailDiscription.text = this.trail.description
        this.binding.trailLen.text = this.trail.length.toString()

        this.trailTime = TrailGlobalData.getTrailTime(trail)
        this.setupStopwatchFragment()
        supportActionBar?.apply {
            setDisplayUseLogoEnabled(true)  // Enables the logo display
            setLogo(this@TrailView.trail.imageSource)  // Sets the logo
            setDisplayShowTitleEnabled(true)  // Enables the title display
            title = "Your Title"  // Set your desired title
        }
    }

    private fun setupStopwatchFragment() {
        val bundle = Bundle()
        bundle.putSerializable("trailTime", this.trailTime)

        val fragment = StopwatchFragment()
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, fragment).commit()
    }

    override fun saveStopwatch(hours: Int, minutes: Int, seconds: Int) {
        this.trailTime.hours = hours
        this.trailTime.minutes = minutes
        this.trailTime.seconds = seconds
        TrailGlobalData.saveTrailTime(this.trail, this.trailTime)
    }

}