package com.sdv.kit.application.viewholder

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sdv.kit.application.R
import com.sdv.kit.application.entity.Bike
import com.sdv.kit.application.entity.PassengerCar
import com.sdv.kit.application.entity.Truck
import com.sdv.kit.application.entity.Vehicle

class ResultItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val vehicleTypeImage: ImageView = itemView.findViewById(R.id.vehicleTypeImage)
    private val vehicleName: TextView = itemView.findViewById(R.id.vehicleName)
    private val vehicleInfo: TextView = itemView.findViewById(R.id.vehicleInfo)
    private val placeImage: ImageView = itemView.findViewById(R.id.placeImage)

    fun bind(vehicle: Vehicle, place: Int) {
        when (vehicle) {
            is Bike -> setBikeName(vehicle)
            is PassengerCar -> setPassengerCarName(vehicle)
            is Truck -> setTruckName(vehicle)
        }

        setVehicleInfo(vehicle)
        setPlaceImage(place)
    }

    private fun setPlaceImage(place: Int) {
        placeImage.visibility = if (place == 0) View.VISIBLE else View.INVISIBLE
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
}