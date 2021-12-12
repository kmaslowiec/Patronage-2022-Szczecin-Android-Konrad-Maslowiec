package com.example.android.intivetaskone.fragments

import android.app.Activity
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.intivetaskone.RecyclerViewAdapter
import com.example.android.intivetaskone.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private lateinit var mAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentMainBinding.inflate(inflater)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this.context,2)

        viewModel.info.observe(viewLifecycleOwner, { list ->
            mAdapter = RecyclerViewAdapter(list)
            recyclerView.adapter = mAdapter
        })

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        if (isOnline()) {
            Toast.makeText(this.context, "INTERNET WORKS", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this.context, "NO INTERNET CONNECTION", Toast.LENGTH_LONG).show()
        }


        return binding.root
    }

    private fun isOnline(): Boolean {
        val cm = this.context?.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val cap = cm.getNetworkCapabilities(cm.activeNetwork)
        return (cap != null && cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))
    }
}