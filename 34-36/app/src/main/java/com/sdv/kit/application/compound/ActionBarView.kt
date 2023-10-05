package com.sdv.kit.application.compound

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.sdv.kit.application.R

class ActionBarView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.view_action_bar, this)
    }
}