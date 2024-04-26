package com.student151915_151917.mobilki

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TrailAdapter(private val trails: List<Trail>) : RecyclerView.Adapter<TrailAdapter.TrailViewHolder>() {

    private var onClickListener: View.OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trail_item, parent, false)
        return TrailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trails.size
    }

    override fun onBindViewHolder(holder: TrailViewHolder, position: Int) {
        val trail = trails[position]
        holder.name.text = trail.name
        holder.length.text = trail.length
        holder.difficulty.text = trail.difficulty
    }
    class TrailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.TrailName)
        val length: TextView = itemView.findViewById(R.id.TrailLen)
        val difficulty: TextView = itemView.findViewById(R.id.TrailDiff)
    }
}