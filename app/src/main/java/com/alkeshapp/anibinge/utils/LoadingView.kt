package com.alkeshapp.anibinge.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.alkeshapp.anibinge.R

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val progressBar: ProgressBar

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.loading_layout, this, true)
        progressBar = view.findViewById(R.id.progressBar)
    }

    fun showLoading() {
        visibility = View.VISIBLE
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        visibility = View.GONE
        progressBar.visibility = View.GONE
    }
}