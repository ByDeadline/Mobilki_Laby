package com.student151915_151917.mobilki

import android.health.connect.datatypes.units.Length

class Trail(val name: String, val length: Double, val difficulty: TrailDifficulty, val description: String) {

}

enum class TrailDifficulty {
    easy,
    medium,
    hard
}