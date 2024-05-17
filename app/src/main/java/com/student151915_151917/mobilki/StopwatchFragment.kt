package com.student151915_151917.mobilki

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.student151915_151917.mobilki.databinding.FragmentStopwatchBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StopwatchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StopwatchFragment : Fragment() {
    private var _binding: FragmentStopwatchBinding? = null
    private val binding get() = _binding!!

    private var seconds = 0
    private var minutes = 0
    private var hours = 0

    private var timerEnabled = true

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

        this.runTimer()
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

    private fun oneSecond() {
        if (this.timerEnabled) {
            this.addSecond()
            binding.stopwatch.text = this.seconds.toString()
        }
        Handler(Looper.getMainLooper()).postDelayed(::oneSecond, 1000)
    }

    private fun runTimer() {
        Handler(Looper.getMainLooper()).postDelayed(::oneSecond, 1000)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                StopwatchFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}