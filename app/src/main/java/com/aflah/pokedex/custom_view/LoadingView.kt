package com.aflah.pokedex.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.aflah.pokedex.R
import com.aflah.pokedex.extension.isVisible
import kotlinx.android.synthetic.main.loading_view.view.*

class LoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.loading_view, this)
    }

    fun setText(message: String) {
        titleLoadingTextView.text = message
    }

    fun showLoadingText(isVisible: Boolean = true) {
        titleLoadingTextView.isVisible = isVisible
    }
}