package com.student151915_151917.mobilki

import java.io.Serializable

class Trail(val name: String, val length: Double, val difficulty: TrailDifficulty, val description: String) : Serializable {

    private val trialDifficultyMap = mapOf(
        TrailDifficulty.Easy to "easy",
        TrailDifficulty.Medium to "medium",
        TrailDifficulty.Hard to "hard"
        )

    fun getTrailDifficulty(trialDifficulty: TrailDifficulty): String {
        return this.trialDifficultyMap.getOrDefault(trialDifficulty, "unknown")
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