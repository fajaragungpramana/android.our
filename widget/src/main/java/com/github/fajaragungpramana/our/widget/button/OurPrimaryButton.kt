package com.github.fajaragungpramana.our.widget.button

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import com.github.fajaragungpramana.our.widget.R
import com.google.android.material.button.MaterialButton

class OurPrimaryButton(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private var materialButton: MaterialButton
    private var contentLoadingProgressBar: ContentLoadingProgressBar

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

    var isLoading: Boolean
        set(value) {
            materialButton.isVisible = !value
            if (value) contentLoadingProgressBar.show() else contentLoadingProgressBar.hide()
        }
        get() = !contentLoadingProgressBar.isVisible

    init {
        View.inflate(context, R.layout.button_primary_our, this)

        materialButton = findViewById(R.id.mb_primary)
        contentLoadingProgressBar = findViewById(R.id.clp_primary)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.OurPrimaryButton)
        try {
            isEnable = ta.getBoolean(R.styleable.OurPrimaryButton_enable, true)
            text = ta.getString(R.styleable.OurPrimaryButton_text).orEmpty()
            isLoading = false
        } finally {
            ta.recycle()
        }
    }

}