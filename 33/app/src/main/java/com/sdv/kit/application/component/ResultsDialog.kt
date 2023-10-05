package com.sdv.kit.application.component

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sdv.kit.application.R
import com.sdv.kit.application.adapter.ResultItemRecyclerViewAdapter
import com.sdv.kit.application.entity.Race
import com.sdv.kit.application.util.RaceRunner

class ResultsDialog(
    context: Context,
    private val finishedRace: Race
) : Dialog(context) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var runRaceAgainButton: AppCompatButton

    private val raceRunner = context as RaceRunner

    private lateinit var resultItemRecyclerViewAdapter: ResultItemRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_results)
        initializeViews()
        setUpRecyclerView()
        setClickListeners()
    }

    private fun setClickListeners() {
        runRaceAgainButton.setOnClickListener {
            finishedRace.finishers.clear()
            raceRunner.runRace(finishedRace)
            dismiss()
        }
    }

    private fun setUpRecyclerView() {
        resultItemRecyclerViewAdapter = ResultItemRecyclerViewAdapter(context)
        resultItemRecyclerViewAdapter.submitList(finishedRace.finishers.toList())
        recyclerView.adapter = resultItemRecyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun initializeViews() {
        recyclerView = findViewById(R.id.recyclerView)
        runRaceAgainButton = findViewById(R.id.runRaceAgainButton)
    }
}