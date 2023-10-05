package com.sdv.kit.application.component

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.sdv.kit.application.R
import com.sdv.kit.application.entity.PassengerCar
import com.sdv.kit.application.util.VehicleAdder

class AddNewPassengerCarDialog(context: Context) : Dialog(context) {
    private lateinit var speedEditText: EditText
    private lateinit var tirePunctureEditText: EditText
    private lateinit var passengersNumberEditText: EditText
    private lateinit var saveButton: AppCompatButton

    private val vehicleAdder: VehicleAdder by lazy { context as VehicleAdder }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_passenger_car)
        initializeViews()
        setSaveButtonClickListener()
    }

    private fun setSaveButtonClickListener() {
        saveButton.setOnClickListener {
            if (isFieldNotEmpty()) {
                val speed = speedEditText.text.toString().toInt()
                val tirePuncture = tirePunctureEditText.text.toString().toInt()
                val passengersNumber = passengersNumberEditText.text.toString().toInt()

                vehicleAdder.addVehicle(PassengerCar(speed, tirePuncture, passengersNumber))
                dismiss()
            }
        }
    }

    private fun isFieldNotEmpty() = speedEditText.text.toString().toInt() > 0 &&
            tirePunctureEditText.text.toString().toInt() > 0 &&
            passengersNumberEditText.text.toString().toInt() > 0

    private fun initializeViews() {
        speedEditText = findViewById(R.id.speedEditText)
        tirePunctureEditText = findViewById(R.id.tirePunctureEditText)
        passengersNumberEditText = findViewById(R.id.passengersNumberEditText)
        saveButton = findViewById(R.id.saveButton)
    }
}