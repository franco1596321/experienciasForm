package com.example.male_impact

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorServicios (private val listarservicios:List<EntidadServicios>):
    RecyclerView.Adapter<AdaptadorServicios.CursoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_servicios,parent,false)
        return CursoViewHolder(itemView)

    }

    override fun getItemCount(): Int {

        return listarservicios.size
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        val Serviciosactuales=listarservicios[position]
        holder.img_servi.setImageResource(Serviciosactuales.imagenservi)
        holder.t_servi.text=Serviciosactuales.nombreservi
        holder.t_descripcion.text=Serviciosactuales.descripservi
    }

    class CursoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val img_servi: ImageView=itemView.findViewById(R.id.img_servi)
        val t_servi: TextView=itemView.findViewById(R.id.t_servi)
        val t_descripcion: TextView=itemView.findViewById(R.id.t_descripservi)
    }
}