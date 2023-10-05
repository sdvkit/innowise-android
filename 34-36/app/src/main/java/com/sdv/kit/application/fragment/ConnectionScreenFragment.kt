package com.sdv.kit.application.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.sdv.kit.application.R
import com.sdv.kit.application.contract.ApiConnector

class ConnectionScreenFragment : Fragment(R.layout.fragment_connect) {
    private lateinit var connectButton: AppCompatButton

    private var apiConnector: ApiConnector? = null

    override fun onDetach() {
        super.onDetach()
        apiConnector = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        apiConnector = context as ApiConnector
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        setClickListeners()
    }

    private fun setClickListeners() {
        connectButton.setOnClickListener {
            apiConnector?.connect()
        }
    }

    private fun initializeViews() {
        connectButton = requireView().findViewById(R.id.connectButton)
    }
}