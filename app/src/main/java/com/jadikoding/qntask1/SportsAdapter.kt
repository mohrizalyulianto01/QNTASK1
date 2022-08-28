package com.jadikoding.qntask1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SportsAdapter(private val listSports: List<SportsItem>) :
    RecyclerView.Adapter<SportsAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sportIcon: ImageView = itemView.findViewById(R.id.sportIcon)
        val sportName: TextView = itemView.findViewById(R.id.sportName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sportItem = listSports[position]

        Glide.with(holder.itemView.context)
            .load(sportItem.strSportIconGreen)
            .into(holder.sportIcon)
        holder.sportName.text = sportItem.strSport
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(sportItem)
        }
    }

    override fun getItemCount(): Int = listSports.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: SportsItem)
    }
}