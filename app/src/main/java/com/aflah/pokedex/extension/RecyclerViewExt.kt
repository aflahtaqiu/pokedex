package com.aflah.pokedex.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.infiniteScrollListener(visibleThreshold: Int, listener: () -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
            val lastVisibleItemPosition = when (recyclerView.layoutManager) {
                is LinearLayoutManager -> (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                else -> 0
            }

            if (totalItemCount <= (lastVisibleItemPosition + visibleThreshold)) {
                listener()
            }
        }
    })
}