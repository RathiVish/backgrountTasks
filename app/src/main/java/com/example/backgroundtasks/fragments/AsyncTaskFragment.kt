package com.example.backgroundtasks.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.backgroundtasks.R
import com.example.backgroundtasks.databinding.FragmentAsyncTaskBinding
import okhttp3.OkHttpClient
import okhttp3.Request

// This fragment uses AsyncTask to call api in background

class AsyncTaskFragment : Fragment() {

    // DataBinding used
    private lateinit var binding: FragmentAsyncTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_async_task, container,
            false
        )

        setClickListeners()
        return binding.root
    }

    private fun setClickListeners() {
        // On Next Click Navigating to next fragment
        binding.btnNext.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_asyncTaskFragment_to_serviceFragment)
        }

        // Call api on start click
        binding.btnStart.setOnClickListener {
            val task = DownloadWebPageTask(this)
            task.execute("https://www.keepinspiring.me/famous-quotes/")
        }
    }

    // Async Task is made static to avoid memory leak\
    companion object {
        private class DownloadWebPageTask(private val asyncTaskFragment: AsyncTaskFragment) :
            AsyncTask<String, Void, String>() {

            override fun onPreExecute() {}

            override fun doInBackground(vararg urls: String): String {
                // OkHttp library is used to hit api

                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(urls[0])
                    .build()
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    return response.body?.string()!!
                }
                return "Download failed"
            }


            // Showing api result in string form
            override fun onPostExecute(result: String) {
                asyncTaskFragment.binding.tvResult.text = result
            }
        }
    }


}