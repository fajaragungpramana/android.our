package com.github.fajaragungpramana.our.widget.button

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.github.fajaragungpramana.our.widget.R
import com.google.android.material.button.MaterialButton

class OurPrimaryButton(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private var materialButton: MaterialButton

    var isEnable: Boolean
        set(value) {
            materialButton.isEnabled = value
        }
        get() = materialButton.isEnabled

    var text: String
        set(value) {
            materialButton.text = value
        }
        get() = materialButton.text.toString()

    init {
        View.inflate(context, R.layout.button_primary_our, this)

        materialButton = findViewById(R.id.mb_primary)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.OurPrimaryButton)
        try {
            isEnable = ta.getBoolean(R.styleable.OurPrimaryButton_enable, true)
            text = ta.getString(R.styleable.OurPrimaryButton_text).orEmpty()
        } finally {
            ta.recycle()
        }
    }

}