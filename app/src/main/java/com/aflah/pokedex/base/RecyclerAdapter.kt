package com.aflah.pokedex.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class RecyclerAdapter<E, V : RecyclerAdapter<E, V>.BaseViewHolder>(
        protected val context: Context, protected var items: MutableList<E>) :
        RecyclerView.Adapter<V>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.bind(items[position])
    }

    open fun getBindingViewHolder(parent: ViewGroup, layoutResource: Int): ViewDataBinding {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DataBindingUtil.inflate(layoutInflater, layoutResource, parent, false)
    }

    open fun addItem(item: E) {
        items.add(item)
        notifyDataSetChanged()
    }

    open fun addItems(newItems: List<E>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    open fun replaceItems(newItems: List<E>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    open fun removeItem(item: E) {
        items.remove(item)
        notifyDataSetChanged()
    }

    open fun removeItem(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()
    }

    open fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: E)
    }
}