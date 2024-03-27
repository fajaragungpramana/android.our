package com.github.fajaragungpramana.our.widget.textfield

import android.content.Context
import android.util.AttributeSet
import android.util.Patterns
import android.view.View
import android.widget.FrameLayout
import androidx.core.widget.addTextChangedListener
import com.github.fajaragungpramana.our.widget.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class OurFloatingTextField(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var textInputLayout: TextInputLayout
    private var textInputEditText: TextInputEditText

    var hintFloating: String
        set(value) {
            textInputLayout.hint = value
        }
        get() = textInputLayout.hint.toString()

    var hintPlaceHolder: String
        set(value) {
            textInputEditText.hint = value
        }
        get() = textInputEditText.hint.toString()

    var text: String
        set(value) {
            textInputEditText.setText(value)
        }
        get() = textInputEditText.text.toString()

    private var inputType: Int
        set(value) {
            textInputEditText.inputType = value
        }
        get() = textInputEditText.inputType

    var error: String?
        set(value) {
            textInputLayout.error = value
        }
        get() = textInputLayout.error.toString()

    init {
        View.inflate(context, R.layout.text_field_floating_our, this)

        textInputLayout = findViewById(R.id.til_floating_our)
        textInputEditText = findViewById(R.id.tie_floating_our)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.OurFloatingTextField)
        try {
            hintFloating = ta.getString(R.styleable.OurFloatingTextField_hintFloating).orEmpty()
            hintPlaceHolder =
                ta.getString(R.styleable.OurFloatingTextField_hintPlaceHolder).orEmpty()
            inputType = ta.getInt(R.styleable.OurFloatingTextField_inputType, 0)
        } finally {
            ta.recycle()
        }
    }

    fun addTextChangedListener(value: (String) -> Unit) {
        textInputEditText.addTextChangedListener {
            error = when (inputType) {

                // Person Name
                0x00000061 -> if (text.length < 4)
                    resources.getString(R.string.error_name)
                else
                    null

                // Email
                0x00000021 -> if (!Patterns.EMAIL_ADDRESS.matcher(text).matches())
                    resources.getString(R.string.error_email)
                else
                    null

                // Password
                0x00000081 -> if (text.length < 8)
                    resources.getString(R.string.error_password)
                else
                    null

                else -> null

            }

            value.invoke(it.toString())
        }
    }

}