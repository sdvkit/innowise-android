package com.sdv.kit.application

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sdv.kit.application.adapter.VehicleRecyclerViewAdapter
import com.sdv.kit.application.component.AddNewBikeDialog
import com.sdv.kit.application.component.AddNewPassengerCarDialog
import com.sdv.kit.application.component.AddNewTruckDialog
import com.sdv.kit.application.component.BeforeRaceDialog
import com.sdv.kit.application.component.ResultsDialog
import com.sdv.kit.application.entity.Race
import com.sdv.kit.application.entity.Vehicle
import com.sdv.kit.application.util.RaceRunner
import com.sdv.kit.application.util.RaceRunnerUtil
import com.sdv.kit.application.util.VehicleAdder
import com.sdv.kit.application.util.VehicleRemover

class MainActivity : AppCompatActivity(), VehicleRemover, VehicleAdder, RaceRunner {
    private lateinit var recyclerView: RecyclerView
    private lateinit var startRaceButton: AppCompatButton
    private lateinit var addBike: AppCompatButton
    private lateinit var addTruck: AppCompatButton
    private lateinit var addCar: AppCompatButton
    private lateinit var raceStatusTextView: TextView
    private lateinit var progressBar: ProgressBar

    private val currentParticipants = mutableListOf<Vehicle>()
    private lateinit var vehicleRecyclerViewAdapter: VehicleRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        setUpRecyclerView()
        setClickListeners()
    }

    private fun setUpRecyclerView() {
        vehicleRecyclerViewAdapter = VehicleRecyclerViewAdapter(this)
        recyclerView.adapter = vehicleRecyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun setClickListeners() {
        startRaceButton.setOnClickListener {
            BeforeRaceDialog(this).show()
        }

        addBike.setOnClickListener {
            AddNewBikeDialog(this).show()
        }

        addTruck.setOnClickListener {
            AddNewTruckDialog(this).show()
        }

        addCar.setOnClickListener {
            AddNewPassengerCarDialog(this).show()
        }
    }

    private fun initializeViews() {
        recyclerView = findViewById(R.id.recyclerView)
        startRaceButton = findViewById(R.id.startRaceButton)
        addBike = findViewById(R.id.addBike)
        addTruck = findViewById(R.id.addTruck)
        addCar = findViewById(R.id.addCar)
        raceStatusTextView = findViewById(R.id.raceStatusTextView)
        progressBar = findViewById(R.id.progressBar)
    }

    override fun removeVehicle(vehicle: Vehicle) {
        currentParticipants.remove(vehicle)
        vehicleRecyclerViewAdapter.submitList(currentParticipants.toList())
    }

    override fun addVehicle(vehicle: Vehicle) {
        currentParticipants.add(vehicle)
        vehicleRecyclerViewAdapter.submitList(currentParticipants.toList())
    }

    override fun runRace(race: Race) {
        onRaceStarted()

        RaceRunnerUtil.startRace(race, currentParticipants) { finishedRace ->
            ResultsDialog(this@MainActivity, finishedRace).show()
            onRaceEnded()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onRaceStarted() {
        raceStatusTextView.text = "Running..."
        raceStatusTextView.setTextColor(ContextCompat.getColor(this, R.color.green))
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE
        startRaceButton.visibility = View.INVISIBLE
        addCar.visibility = View.INVISIBLE
        addBike.visibility = View.INVISIBLE
        addTruck.visibility = View.INVISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun onRaceEnded() {
        raceStatusTextView.text = "Not running"
        raceStatusTextView.setTextColor(ContextCompat.getColor(this, R.color.grey))
        progressBar.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
        startRaceButton.visibility = View.VISIBLE
        addCar.visibility = View.VISIBLE
        addBike.visibility = View.VISIBLE
        addTruck.visibility = View.VISIBLE
    }
}
