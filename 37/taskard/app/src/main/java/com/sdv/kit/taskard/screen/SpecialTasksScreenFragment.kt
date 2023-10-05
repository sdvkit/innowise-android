package com.sdv.kit.taskard.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.sdv.kit.taskard.R
import com.sdv.kit.taskard.adapter.TasksRecyclerViewAdapter
import com.sdv.kit.taskard.entity.Task
import com.sdv.kit.taskard.viewmodel.SpecialTasksViewModel

class SpecialTasksScreenFragment : Fragment(R.layout.fragment_special_tasks_screen) {
    private lateinit var tabLayout: TabLayout
    private lateinit var tasksRecyclerView: RecyclerView

    private var tasksRecyclerViewAdapter: TasksRecyclerViewAdapter? = null
    private var specialTasksViewModel: SpecialTasksViewModel? = null
    private var currentTasks: List<Task> = listOf()

    override fun onDetach() {
        super.onDetach()
        tasksRecyclerViewAdapter = null
        specialTasksViewModel = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        configureRecyclerView()
        configureViewModel()
        setTabSelectedListener()
    }

    private fun configureViewModel() {
        specialTasksViewModel = SpecialTasksViewModel(requireContext())

        specialTasksViewModel?.tasks?.observe(viewLifecycleOwner) { tasks ->
            currentTasks = tasks

            when (tabLayout.selectedTabPosition) {
                0 -> submitCurrentTasks { it.isFavourite }
                else -> submitCurrentTasks { it.isCompleted }
            }
        }

        specialTasksViewModel?.startObservingTasks()
    }

    private fun submitCurrentTasks(filterPredicate: (Task) -> Boolean) {
        tasksRecyclerViewAdapter?.submitList(currentTasks.filter { filterPredicate(it) })
    }

    private fun configureRecyclerView() {
        tasksRecyclerViewAdapter = TasksRecyclerViewAdapter(requireContext())
        tasksRecyclerView.adapter = tasksRecyclerViewAdapter
        tasksRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private fun setTabSelectedListener() {
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?): Unit = when (tab) {
                tabLayout.getTabAt(0) -> submitCurrentTasks { it.isFavourite }
                else -> submitCurrentTasks { it.isCompleted }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun initializeViews() {
        tabLayout = requireView().findViewById(R.id.tabLayout)
        tasksRecyclerView = requireView().findViewById(R.id.tasksRecyclerView)
    }
}