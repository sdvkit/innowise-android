package com.sdv.kit.application.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sdv.kit.application.CustomViewModel
import com.sdv.kit.application.R
import com.sdv.kit.application.adapter.ListAdapter1
import com.sdv.kit.application.util.RandomUserGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScreenFragment5 : Fragment(R.layout.fragment_screen_5) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var clearButton: Button
    private lateinit var noAvailableUsers: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeToRefreshLayout: SwipeRefreshLayout

    private val listAdapter: ListAdapter1 by lazy { ListAdapter1(requireContext()) }
    private val viewModel: CustomViewModel by lazy { ViewModelProvider(this)[CustomViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        initializeRecyclerView()
        observeViewModel()
        setClickListeners()
        setOnSwipeRefresh()

        if (savedInstanceState == null) {
            viewModel.firstLoad()
        }
    }

    private fun setOnSwipeRefresh() {
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.updateList()
        }
    }

    private fun setClickListeners() {
        clearButton.setOnClickListener {
            viewModel.clearList()
        }
    }

    private fun initializeViews(root: View) {
        recyclerView = root.findViewById(R.id.recyclerView)
        clearButton = root.findViewById(R.id.clearButton)
        noAvailableUsers = root.findViewById(R.id.noAvailableUsers)
        progressBar = root.findViewById(R.id.progressBar)
        swipeToRefreshLayout = root.findViewById(R.id.swipeToRefreshLayout)
    }

    private fun showNoAvailableUsersLabel(show: Boolean) {
        noAvailableUsers.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    private fun observeViewModel() {
        viewModel.isRefreshing.observe(viewLifecycleOwner) { isRefreshing -> swipeToRefreshLayout.isRefreshing = isRefreshing }

        viewModel.isProcessing.observe(viewLifecycleOwner) { isProcessing ->
            progressBar.visibility = if (isProcessing) View.VISIBLE else View.INVISIBLE
        }

        viewModel.userList.observe(viewLifecycleOwner) { list ->
            showNoAvailableUsersLabel(list.isEmpty() && progressBar.visibility == View.INVISIBLE)
            recyclerView.visibility = if (list.isEmpty()) View.INVISIBLE else View.VISIBLE
            listAdapter.submitList(list)
        }
    }

    private fun initializeRecyclerView() {
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }
}