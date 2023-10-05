package com.sdv.kit.application.component

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.sdv.kit.application.R
import com.sdv.kit.application.entity.Truck
import com.sdv.kit.application.util.VehicleAdder

class AddNewTruckDialog(context: Context) : Dialog(context) {
    private lateinit var speedEditText: EditText
    private lateinit var tirePunctureEditText: EditText
    private lateinit var cargoWeightEditText: EditText
    private lateinit var saveButton: AppCompatButton

    private val vehicleAdder: VehicleAdder by lazy { context as VehicleAdder }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_truck)
        initializeViews()
        setSaveButtonClickListener()
    }

    private fun setSaveButtonClickListener() {
        saveButton.setOnClickListener {
            if (isFieldNotEmpty()) {
                val speed = speedEditText.text.toString().toInt()
                val tirePuncture = tirePunctureEditText.text.toString().toInt()
                val cargoWeight = cargoWeightEditText.text.toString().toInt()

                vehicleAdder.addVehicle(Truck(speed, tirePuncture, cargoWeight))
                dismiss()
            }
        }
    }

    private fun isFieldNotEmpty() = speedEditText.text.toString().toInt() > 0 &&
            tirePunctureEditText.text.toString().toInt() > 0 &&
            cargoWeightEditText.text.toString().toInt() > 0

    private fun initializeViews() {
        speedEditText = findViewById(R.id.speedEditText)
        tirePunctureEditText = findViewById(R.id.tirePunctureEditText)
        cargoWeightEditText = findViewById(R.id.cargoWeightEditText)
        saveButton = findViewById(R.id.saveButton)
    }
}