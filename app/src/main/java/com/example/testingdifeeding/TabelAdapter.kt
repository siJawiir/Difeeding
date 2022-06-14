package com.example.testingdifeeding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testingdifeeding.TabelViewHolder
import com.example.testingdifeeding.dataTabel

class TabelAdapter (private val data: ArrayList<dataTabel>): RecyclerView.Adapter<TabelViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TabelViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TabelViewHolder, position: Int) {
        holder.bind(data[position])
    }

}