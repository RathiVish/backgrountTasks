package com.example.backgroundtasks.fragments

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.backgroundtasks.R
import com.example.backgroundtasks.databinding.FragmentThreadBinding

// This fragment uses thread for background task

class ThreadFragment : Fragment() {


    // DataBinding used
    private lateinit var binding: FragmentThreadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_thread, container, false)

        setClickListeners()
        return binding.root
    }

    private fun setClickListeners() {

        // Creating a thread with Runnable on start click

        binding.btnStart.setOnClickListener {
            binding.tvUpdate.text = getString(R.string.progressThreadText)
            val runnable = Runnable {
                for (i in 0..10) {
                    pause()
                    binding.progressBar.post {
                        binding.progressBar.progress = i
                    }
                }
            }
            Thread(runnable).start()
        }

        // On Next Click Navigating to next fragment
        binding.btnNext.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_threadFragment_to_asyncTaskFragment)
        }
    }

    // Providing delay in above process
    private fun pause() {
        try {
            SystemClock.sleep(1000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}