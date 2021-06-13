package com.example.tp3desenvolvimentokotlin.ui.home.dashboard.addapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3desenvolvimentokotlin.R
import com.example.tp3desenvolvimentokotlin.domain.entity.Event
import java.text.SimpleDateFormat

class VaccineAdapter(val vaccines: ArrayList<Event>, private val actionClick: (Event) -> Unit) :
    RecyclerView.Adapter<VaccineAdapter.ViewHolder>() {

    override fun getItemCount() = vaccines.size;

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val firstName: TextView = itemView.findViewById(R.id.vacineName);
        val releaseDate: TextView = itemView.findViewById(R.id.releaseDate);
        val numberRecords: TextView = itemView.findViewById(R.id.numberRecords);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccineAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_vaccine, parent, false);
        return  ViewHolder(view);
    }


    override fun onBindViewHolder(holder: VaccineAdapter.ViewHolder, position: Int) {
        val sdf = SimpleDateFormat("dd/M/yyyy")


        holder.firstName.text = vaccines[position].name;
        holder.releaseDate.text = sdf.format(vaccines[position].releases?.get(0)?.date);
        holder.numberRecords.text = vaccines[position].releases!!.size.toString()  + " / " + vaccines[position].total.toString();

        holder.itemView.setOnClickListener {
            actionClick(vaccines[position])
        }
    }
}