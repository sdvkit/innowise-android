package com.sdv.kit.application.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sdv.kit.application.R
import com.sdv.kit.application.adapter.ListAdapter1
import com.sdv.kit.application.adapter.ListAdapter2
import com.sdv.kit.application.util.RandomUserGenerator

class ScreenFragment2 : Fragment(R.layout.fragment_screen_2) {
    private lateinit var recyclerView: RecyclerView
    private val listAdapter: ListAdapter2 by lazy { ListAdapter2(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView(view)
        fillRecyclerView()
    }

    private fun fillRecyclerView() {
        val randomGeneratedUsers = RandomUserGenerator.generate(30)
        listAdapter.submitList(randomGeneratedUsers)
    }

    private fun initializeRecyclerView(root: View) {
        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }
}