package com.app.ola.core.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DynamicAdapter<T>(
        val layout: Int,
        val entries: List<T>,
        val action: (vh: DynamicAdapter.ViewHolder, view: View, entry: T, position: Int) -> Unit
): androidx.recyclerview.widget.RecyclerView.Adapter<DynamicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        action.invoke(holder, holder.view, entries.get(position), position)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    class ViewHolder (v: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(v) {
        val view = v
    }

}