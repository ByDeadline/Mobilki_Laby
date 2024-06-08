package com.student151915_151917.mobilki

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.student151915_151917.mobilki.databinding.FragmentStopwatchBinding

class StopwatchFragment : Fragment() {
    private var _binding: FragmentStopwatchBinding? = null
    private val binding get() = _binding!!

    private var seconds = 0
    private var minutes = 0
    private var hours = 0

    private var timerEnabled = false

    private lateinit var stopwatchActivity: StopwatchInterface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStopwatchBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.stopwatchActivity = activity as StopwatchInterface
        this.loadSentData()
        this.runTimer()
        this.setOnClick()
    }

    private fun setOnClick() {
        binding.startButton.setOnClickListener { start() }
        binding.pauseButton.setOnClickListener { pause() }
        binding.resetButton.setOnClickListener { reset() }
    }

    private fun start() {
        this.timerEnabled = true
    }

    private fun pause () {
        this.timerEnabled = false
        this.stopwatchActivity.saveStopwatch(this.hours, this.minutes, this.seconds)
    }

    private fun reset() {
        this.timerEnabled = false
        this.hours = 0
        this.minutes = 0
        this.seconds = 0
        this.binding.stopwatch.text = this.convertTimeToText()
        this.stopwatchActivity.saveStopwatch(this.hours, this.minutes, this.seconds)
    }

    private fun loadSentData() {
        val sentData = arguments?.getSerializable("trailTime", TrailTime::class.java)
        if (sentData != null) {
            this.seconds = sentData.seconds
            this.minutes = sentData.minutes
            this.hours = sentData.hours

            this.binding.stopwatch.text = this.convertTimeToText()
        }
    }

    private fun addSecond() {
        this.seconds++
        if (this.seconds == 60) {
            this.seconds = 0
            this.minutes++
        }
        if (this.minutes == 60) {
            this.minutes = 0
            this.hours++
        }
    }

    private fun convertTimeToText() : String {
        var hoursText = ""
        var minutesText = ""
        var secondsText = ""

        hoursText = if (this.hours < 10) {
            "0" + this.hours.toString()
        } else {
            this.hours.toString()
        }

        minutesText = if (this.minutes < 10) {
            "0" + this.minutes.toString()
        } else {
            this.minutes.toString()
        }

        secondsText = if (this.seconds < 10) {
            "0" + this.seconds.toString()
        } else {
            this.seconds.toString()
        }

        return "$hoursText:$minutesText:$secondsText"
    }

    private fun oneSecond() {
        if (this.timerEnabled) {
            this.addSecond()
            binding.stopwatch.text = this.convertTimeToText()
        }
        Handler(Looper.getMainLooper()).postDelayed(::oneSecond, 1000)
    }

    private fun runTimer() {
        Handler(Looper.getMainLooper()).postDelayed(::oneSecond, 1000)
    }
}