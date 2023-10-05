package com.sdv.kit.application.viewholder

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.sdv.kit.application.R
import com.sdv.kit.application.util.VehicleRemover
import com.sdv.kit.application.entity.Bike
import com.sdv.kit.application.entity.PassengerCar
import com.sdv.kit.application.entity.Truck
import com.sdv.kit.application.entity.Vehicle

class VehicleViewHolder(
    itemView: View,
    private val vehicleRemover: VehicleRemover
) : RecyclerView.ViewHolder(itemView) {
    private val vehicleTypeImage: ImageView = itemView.findViewById(R.id.vehicleTypeImage)
    private val vehicleName: TextView = itemView.findViewById(R.id.vehicleName)
    private val vehicleInfo: TextView = itemView.findViewById(R.id.vehicleInfo)
    private val removeButton: AppCompatButton = itemView.findViewById(R.id.removeButton)

    fun bind(vehicle: Vehicle) {
        when (vehicle) {
            is Bike -> setBikeName(vehicle)
            is PassengerCar -> setPassengerCarName(vehicle)
            is Truck -> setTruckName(vehicle)
        }

        setVehicleInfo(vehicle)
        setOnRemoveButtonClicked(vehicle)
    }

    @SuppressLint("SetTextI18n")
    private fun setVehicleInfo(vehicle: Vehicle) {
        vehicleInfo.text = "${vehicle.speed} km/h. ${vehicle.tirePuncturePercent}% t.p."
    }

    @SuppressLint("SetTextI18n")
    private fun setBikeName(bike: Bike) {
        vehicleTypeImage.setImageResource(R.drawable.motorcycle)
        vehicleName.text = "Bike (${bike.hasSidecar}) #${bike.id}"
    }

    @SuppressLint("SetTextI18n")
    private fun setTruckName(truck: Truck) {
        vehicleTypeImage.setImageResource(R.drawable.truck)
        vehicleName.text = "Truck (${truck.cargoWeight}) #${truck.id}"
    }

    @SuppressLint("SetTextI18n")
    private fun setPassengerCarName(passengerCar: PassengerCar) {
        vehicleTypeImage.setImageResource(R.drawable.car)
        vehicleName.text = "Pass. car (${passengerCar.passengersNumber}) #${passengerCar.id}"
    }

    private fun setOnRemoveButtonClicked(vehicleToRemove: Vehicle) {
        removeButton.setOnClickListener {
            vehicleRemover.removeVehicle(vehicleToRemove)
        }
    }
}