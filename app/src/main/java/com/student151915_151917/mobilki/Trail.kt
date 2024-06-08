package com.student151915_151917.mobilki

import java.io.Serializable

data class TrailTime(var hours: Int, var minutes: Int, var seconds: Int) : Serializable

class TrailGlobalData {
    companion object {
        private var objectIdCounter: Int = -1
        private var trailTimes: MutableMap<Int, TrailTime> = mutableMapOf()

        fun getTrailTime(trail: Trail): TrailTime {
            return this.trailTimes.getOrDefault(trail.id, TrailTime(0,0,0))
        }

        fun saveTrailTime(trail: Trail, trailTime: TrailTime) {
            this.trailTimes[trail.id] = trailTime
        }

        fun getObjectId() : Int {
            return ++this.objectIdCounter
        }
    }
}

class Trail(val name: String, val length: Double, private val difficulty: TrailDifficulty, val description: String) : Serializable {

    var id : Int = -1

    init {
        this.id = TrailGlobalData.getObjectId()
    }


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