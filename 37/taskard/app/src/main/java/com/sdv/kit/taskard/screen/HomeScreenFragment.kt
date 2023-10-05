package com.sdv.kit.taskard.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sdv.kit.taskard.R
import com.sdv.kit.taskard.adapter.ProjectsRecyclerViewAdapter
import com.sdv.kit.taskard.adapter.TasksRecyclerViewAdapter
import com.sdv.kit.taskard.component.NewProjectBottomSheetDialog
import com.sdv.kit.taskard.component.NewTaskBottomSheetDialog
import com.sdv.kit.taskard.contract.FirebaseSynchronizer
import com.sdv.kit.taskard.entity.Task
import com.sdv.kit.taskard.entity.relation.ProjectWithTasks
import com.sdv.kit.taskard.util.AvatarLoaderUtil
import com.sdv.kit.taskard.util.ViewPagerCustomizerUtil
import com.sdv.kit.taskard.viewmodel.HomeViewModel

class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {
    private lateinit var projectsViewPager: ViewPager2
    private lateinit var newTaskButton: FloatingActionButton
    private lateinit var emptyProjectsTextView: TextView
    private lateinit var newProjectButton: AppCompatButton
    private lateinit var tasksRecyclerView: RecyclerView
    private lateinit var userAvatarImageView: ImageView

    private var homeViewModel: HomeViewModel? = null
    private var projectsRecyclerViewAdapter: ProjectsRecyclerViewAdapter? = null
    private var tasksRecyclerViewAdapter: TasksRecyclerViewAdapter? = null
    private var projectsList: List<ProjectWithTasks> = listOf()
    private var currentProject: ProjectWithTasks? = null
    private var currentTasks: List<Task> = listOf()

    private val avatarLoaderUtil: AvatarLoaderUtil by lazy {
        AvatarLoaderUtil(requireContext())
    }

    override fun onDetach() {
        super.onDetach()
        projectsRecyclerViewAdapter = null
        tasksRecyclerViewAdapter = null
        homeViewModel = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        configureViewModel()
        setClickListeners()
        configureProjectsRecyclerView()
        configureTasksRecyclerView()
        configureUserAvatar()
        (context as FirebaseSynchronizer).synchronizeProjects()
    }

    private fun configureUserAvatar() {
        avatarLoaderUtil.loadUserAvatar(userAvatarImageView)
    }

    private fun setClickListeners() {
        newTaskButton.setOnClickListener {
            NewTaskBottomSheetDialog { name, description ->
                val task = buildTask(name, description)
                homeViewModel?.saveTask(task)
            }.show(childFragmentManager, null)
        }

        newProjectButton.setOnClickListener {
            NewProjectBottomSheetDialog { projectName ->
                homeViewModel?.saveProject(projectName)
            }.show(childFragmentManager, null)
        }
    }

    private fun buildTask(name: String, description: String): Task {
        return Task.Builder()
            .name(name)
            .description(description)
            .projectId(currentProject!!.project.id!!)
            .build()
    }

    private fun configureTasksRecyclerView() {
        tasksRecyclerViewAdapter = TasksRecyclerViewAdapter(requireContext())
        tasksRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        tasksRecyclerView.adapter = tasksRecyclerViewAdapter
    }

    private fun configureProjectsRecyclerView() {
        ViewPagerCustomizerUtil.customizeViewPager(projectsViewPager, resources)
        projectsRecyclerViewAdapter = ProjectsRecyclerViewAdapter(requireContext())
        projectsViewPager.adapter = projectsRecyclerViewAdapter

        projectsViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentProject = projectsList[position]
                homeViewModel?.updateTasksList(currentProject!!.project.id!!)
//                tasksRecyclerViewAdapter?.submitList(currentTasks.filter { it.projectId == currentProject?.project?.id })
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun configureViewModel() {
        homeViewModel = HomeViewModel(requireContext())
        observeProjects()
        observeTasks()
        homeViewModel?.startObservingProjects()
        homeViewModel?.startObservingTasks()
    }

    private fun observeTasks() {
        homeViewModel?.tasks?.observe(viewLifecycleOwner) { tasks ->
            currentTasks = tasks
            tasksRecyclerViewAdapter?.submitList(currentTasks.filter { it.projectId == currentProject?.project?.id })
        }
    }

    private fun observeProjects() {
        homeViewModel?.projects?.observe(viewLifecycleOwner) { projects ->
            projectsList = projects
            projectsRecyclerViewAdapter?.submitList(projects)

            newTaskButton.visibility = if (projects.isEmpty()) View.GONE else View.VISIBLE
            emptyProjectsTextView.visibility = if (projects.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun initializeViews() {
        projectsViewPager = requireView().findViewById(R.id.projectsViewPager)
        newTaskButton = requireView().findViewById(R.id.newTaskButton)
        emptyProjectsTextView = requireView().findViewById(R.id.emptyProjectsTextView)
        newProjectButton = requireView().findViewById(R.id.newProjectButton)
        tasksRecyclerView = requireView().findViewById(R.id.tasksRecyclerView)
        userAvatarImageView = requireView().findViewById(R.id.userAvatarImageView)
    }
}