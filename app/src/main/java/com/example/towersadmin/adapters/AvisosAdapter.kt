package com.example.towersadmin.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.towersadmin.R
import com.example.towersadmin.data.Avisos

class AvisosAdapter (var context: Context) : RecyclerView.Adapter<AvisosAdapter.AvisoViewHolder>() {

    private var listaAvisos = emptyList<Avisos>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateListaAviso(lista: List<Avisos>){
        listaAvisos = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvisoViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.avisos_recycler_view_layout, parent,
        false)

        return AvisoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AvisoViewHolder, position: Int) {

        //Log.i("xptoholder", "getBindViewHolder")

        val aviso = listaAvisos[position]

        holder.titulo_aviso.text = aviso.titulo
        holder.data_hora.text = aviso.data
        holder.aviso.text = aviso.mensagem
        holder.status.text = aviso.status
        holder.link.text = aviso.link
    }

    override fun getItemCount(): Int {

        //Log.i("xptohoder", "getItemCount")

        return listaAvisos.size
    }

    class  AvisoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val titulo_aviso = itemView.findViewById<TextView>(R.id.titulo_aviso)
        val data_hora = itemView.findViewById<TextView>(R.id.data_hora_aviso)
        val aviso = itemView.findViewById<TextView>(R.id.tv_main_aviso)
        val status = itemView.findViewById<TextView>(R.id.tv_status_aviso)
        val link = itemView.findViewById<TextView>(R.id.tv_link)
    }


}