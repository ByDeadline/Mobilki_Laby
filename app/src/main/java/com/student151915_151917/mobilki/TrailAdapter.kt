package com.student151915_151917.mobilki

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TrailAdapter(private val trails: List<Trail>, private val onItemClicked: (Trail) -> Unit ) : RecyclerView.Adapter<TrailAdapter.TrailViewHolder>() {

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
        holder.length.text = trail.length.toString()
        holder.difficulty.text = trail.getTrailDifficulty()
        holder.imageView.setImageResource(trail.imageSource)
        holder.itemView.setOnClickListener {
            onItemClicked(trail)
            }
        }
    class TrailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.TrailName)
        val length: TextView = itemView.findViewById(R.id.TrailLen)
        val difficulty: TextView = itemView.findViewById(R.id.TrailDiff)
        val imageView: ImageView = itemView.findViewById((R.id.trailImage))
    }

}