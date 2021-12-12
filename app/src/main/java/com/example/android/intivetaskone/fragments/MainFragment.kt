package com.example.android.intivetaskone.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.intivetaskone.R
import com.example.android.intivetaskone.RecyclerViewAdapter
import com.example.android.intivetaskone.databinding.FragmentMainBinding
import com.example.android.intivetaskone.network.ConnectivityStatus

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
        val span = if (isTablet(requireContext())) 4 else 2
        recyclerView.layoutManager = GridLayoutManager(this.context, span)

        //Update the RecyclerView
        viewModel.info.observe(viewLifecycleOwner, { list ->
            mAdapter = RecyclerViewAdapter(list)
            recyclerView.adapter = mAdapter
        })

        //Check the Internet Connection
        val connectivity = ConnectivityStatus(this.requireContext())
        connectivity.observe(viewLifecycleOwner, { isOnline ->
            if (!isOnline) Toast.makeText(this.context, getText(R.string.no_internet),
                Toast.LENGTH_LONG).show()
        })

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        return binding.root
    }

    private fun isTablet(context: Context): Boolean {
        return ((context.resources.configuration.screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE)
    }
}