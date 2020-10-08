package com.example.backgroundtasks.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.backgroundtasks.R
import com.example.backgroundtasks.backgroundService.MyService
import com.example.backgroundtasks.databinding.FragmentServiceBinding

// This fragment uses Intent Service to start, stop a task

class ServiceFragment : Fragment() {

    // DataBinding used
    private lateinit var binding: FragmentServiceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_service, container, false)

        setClickListeners()
        return binding.root
    }

    private fun setClickListeners() {
        //Start a service on start click

        binding.btnStart.setOnClickListener {
            activity?.startService(Intent(activity, MyService::class.java))
        }

        //Stop the service on start click
        binding.btnStop.setOnClickListener {
            activity?.stopService(Intent(activity, MyService::class.java))
        }

        // On Next Click Navigating to next fragment
        binding.btnNext.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_serviceFragment_to_threadFragment);
        }
    }
}