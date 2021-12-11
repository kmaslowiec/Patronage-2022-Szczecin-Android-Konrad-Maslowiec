package com.example.android.intivetaskone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.intivetaskone.R
import com.example.android.intivetaskone.RecyclerViewAdapter
import com.example.android.intivetaskone.databinding.FragmentMainBinding
import com.example.android.intivetaskone.databinding.GridViewItemBinding
import com.example.android.intivetaskone.network.InfoProperty

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
        //FragmentMainBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this.context,2)

        val item = listOf<InfoProperty>(InfoProperty("test1", "desc1", "url"),
            InfoProperty("test2", "desc2", "url"))

        mAdapter = RecyclerViewAdapter(item)
        recyclerView.adapter = mAdapter

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        setHasOptionsMenu(true)
        return binding.root
    }
}