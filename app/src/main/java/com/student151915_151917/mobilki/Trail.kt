package com.student151915_151917.mobilki

import java.io.Serializable

data class TrailTime(var hours: Int, var minutes: Int, var seconds: Int)

class TrailGlobalData {
    companion object {
        private var trailTimes: MutableMap<Trail, TrailTime> = mutableMapOf()

        fun getTrailTime(trail: Trail): TrailTime {
            return this.trailTimes.getOrDefault(trail, TrailTime(0,0,0))
        }

        fun saveTrailTime(trail: Trail, trailTime: TrailTime) {
            this.trailTimes[trail] = trailTime
        }
    }
}

class Trail(val name: String, val length: Double, private val difficulty: TrailDifficulty, val description: String) : Serializable {

    private val trialDifficultyMap = mapOf(
        TrailDifficulty.Easy to "easy",
        TrailDifficulty.Medium to "medium",
        TrailDifficulty.Hard to "hard"
        )

    fun getTrailDifficulty(): String {
        return this.trialDifficultyMap.getOrDefault(this.difficulty, "unknown")
    }

    fun getTrialDifficulty(trailDifficulty: String) : TrailDifficulty {
        for (trial in this.trialDifficultyMap) {
            if (trial.value == trailDifficulty) {
                return trial.key
            }
        }
        return TrailDifficulty.Unknown
    }

}

enum class TrailDifficulty {
    Easy,
    Medium,
    Hard,
    Unknown
}