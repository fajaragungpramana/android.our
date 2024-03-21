package com.github.fajaragungpramana.our.widget.textfield

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.github.fajaragungpramana.our.widget.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class OurFloatingTextField(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var textInputLayout: TextInputLayout
    private var textInputEditText: TextInputEditText

    var hintFloating: String
        set (value) {
            textInputLayout.hint = value
        }
        get() = textInputLayout.hint.toString()

    var hintPlaceHolder: String
        set (value) {
            textInputEditText.hint = value
        }
        get() = textInputEditText.hint.toString()

    var inputType: Int
        set (value) {
            textInputEditText.inputType = value
        }
        get() = textInputEditText.inputType

    init {
        View.inflate(context, R.layout.text_field_floating_our, this)

        textInputLayout = findViewById(R.id.til_floating_our)
        textInputEditText = findViewById(R.id.tie_floating_our)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.OurFloatingTextField)
        try {
            hintFloating = ta.getString(R.styleable.OurFloatingTextField_hintFloating).orEmpty()
            hintPlaceHolder = ta.getString(R.styleable.OurFloatingTextField_hintPlaceHolder).orEmpty()
            inputType = ta.getInt(R.styleable.OurFloatingTextField_floatingInputType, 0)
        } finally {
            ta.recycle()
        }
    }

    enum class FieldInputType {
        NONE, NAME, EMAIL, PASSWORD
    }

}