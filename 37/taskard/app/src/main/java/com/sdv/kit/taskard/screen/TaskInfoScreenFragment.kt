package com.sdv.kit.taskard.screen

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.sdv.kit.taskard.R
import com.sdv.kit.taskard.component.TaskActionsBottomSheetDialog
import com.sdv.kit.taskard.contract.ScreenChanger
import com.sdv.kit.taskard.entity.Task
import com.sdv.kit.taskard.viewmodel.TaskInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskInfoScreenFragment : Fragment(R.layout.fragment_task_info_screen) {
    private lateinit var backButton: AppCompatButton
    private lateinit var actionsButton: AppCompatButton
    private lateinit var isCompletedCheckBox: CheckBox
    private lateinit var isFavouriteCheckBox: CheckBox
    private lateinit var taskNameEditText: EditText
    private lateinit var descriptionEditText: EditText

    private var screenChanger: ScreenChanger? = null
    private var taskInfoViewModel: TaskInfoViewModel? = null
    private var currentTask: Task? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        screenChanger = context as ScreenChanger
    }

    override fun onDetach() {
        super.onDetach()
        taskInfoViewModel = null
        currentTask = null
        screenChanger = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        buildTask()
        configureViews()
        configureViewModel()
        setOnCheckboxesClicked()
        setClickListeners()
        setTextChanged()
    }

    private fun configureViewModel() {
        taskInfoViewModel = TaskInfoViewModel(requireContext())
    }

    private fun setTextChanged() {
        taskNameEditText.addTextChangedListener { taskName ->
            currentTask?.name = taskName.toString()
            updateTask()
        }

        descriptionEditText.addTextChangedListener { taskDescription ->
            currentTask?.description = taskDescription.toString()
            updateTask()
        }
    }

    private fun configureViews() = CoroutineScope(Dispatchers.Main).launch {
        taskNameEditText.text = Editable.Factory.getInstance().newEditable(currentTask!!.name)
        descriptionEditText.text = Editable.Factory.getInstance().newEditable(currentTask!!.description)

        isCompletedCheckBox.isChecked = currentTask!!.isCompleted
        isFavouriteCheckBox.isChecked = currentTask!!.isFavourite
        setTaskNameColor()
    }

    private fun setOnCheckboxesClicked() {
        isCompletedCheckBox.setOnCheckedChangeListener { _, isChecked ->
            currentTask!!.isCompleted = isChecked
            updateTask()
            playCheckBoxAnimation()
        }

        isFavouriteCheckBox.setOnCheckedChangeListener { _, isChecked ->
            currentTask!!.isFavourite = isChecked
            updateTask()
            setTaskNameColor()
        }
    }

    private fun playCheckBoxAnimation() = CoroutineScope(Dispatchers.Main).launch {
        isCompletedCheckBox.startAnimation(AnimationUtils
            .loadAnimation(requireContext(), R.anim.is_completed_checkbox_anim))
    }

    private fun setTaskNameColor() {
        val colorId = if (currentTask!!.isFavourite) R.color.primary else R.color.primary_grey
        taskNameEditText.setTextColor(ContextCompat.getColor(requireContext(), colorId))
    }

    private fun updateTask() = CoroutineScope(Dispatchers.IO).launch {
        taskInfoViewModel?.updateTask(currentTask!!)
    }

    private fun buildTask() {
        currentTask = Task.Builder()
            .id(arguments?.getLong(TASK_ID_KEY))
            .projectId(arguments?.getLong(TASK_PROJECT_ID_KEY)!!)
            .name(arguments?.getString(TASK_NAME_KEY, "")!!)
            .description(arguments?.getString(TASK_DESCRIPTION_KEY, "")!!)
            .isCompleted(arguments?.getBoolean(TASK_COMPLETED_KEY, false)!!)
            .isFavourite(arguments?.getBoolean(TASK_FAVOURITE_KEY, false)!!)
            .build()
    }

    private fun setClickListeners() {
        backButton.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        actionsButton.setOnClickListener {
            TaskActionsBottomSheetDialog(currentTask!!)
                .show(childFragmentManager, null)
        }
    }

    private fun initializeViews() {
        backButton = requireView().findViewById(R.id.backButton)
        actionsButton = requireView().findViewById(R.id.actionsButton)
        isCompletedCheckBox = requireView().findViewById(R.id.isCompletedCheckBox)
        isFavouriteCheckBox = requireView().findViewById(R.id.isFavouriteCheckBox)
        taskNameEditText = requireView().findViewById(R.id.taskNameEditText)
        descriptionEditText = requireView().findViewById(R.id.descriptionEditText)
    }

    companion object {
        private const val TASK_ID_KEY = "taskId"
        private const val TASK_PROJECT_ID_KEY = "taskProjectId"
        private const val TASK_NAME_KEY = "taskName"
        private const val TASK_DESCRIPTION_KEY = "taskDescription"
        private const val TASK_COMPLETED_KEY = "taskCompleted"
        private const val TASK_FAVOURITE_KEY = "taskFavourite"

        fun newInstance(task: Task): TaskInfoScreenFragment {
            val args = Bundle().apply {
                putLong(TASK_ID_KEY, task.id!!)
                putLong(TASK_PROJECT_ID_KEY, task.projectId)
                putString(TASK_NAME_KEY, task.name)
                putString(TASK_DESCRIPTION_KEY, task.description)
                putBoolean(TASK_COMPLETED_KEY, task.isCompleted)
                putBoolean(TASK_FAVOURITE_KEY, task.isFavourite)
            }

            return TaskInfoScreenFragment().apply { arguments = args }
        }
    }
}