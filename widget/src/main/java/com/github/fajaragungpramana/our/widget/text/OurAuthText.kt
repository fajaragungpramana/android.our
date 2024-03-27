package com.github.fajaragungpramana.our.widget.text

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.github.fajaragungpramana.our.widget.R
import com.google.android.material.textview.MaterialTextView

class OurAuthText(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private var materialTextViewStart: MaterialTextView
    private var materialTextViewEnd: MaterialTextView

    var start: String
        set(value) {
            materialTextViewStart.text = value
        }
        get() = materialTextViewStart.text.toString()

    var end: String
        set(value) {
            materialTextViewEnd.text = value
        }
        get() = materialTextViewEnd.text.toString()

    init {
        View.inflate(context, R.layout.text_auth_our, this)

        materialTextViewStart = findViewById(R.id.mtv_start)
        materialTextViewEnd = findViewById(R.id.mtv_end)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.OurAuthText)
        try {
            start = ta.getString(R.styleable.OurAuthText_start).orEmpty()
            end = ta.getString(R.styleable.OurAuthText_end).orEmpty()
        } finally {
            ta.recycle()
        }
    }

    fun setOnClickListener(view: (View) -> Unit) {
        materialTextViewEnd.setOnClickListener { view.invoke(it) }
    }

}