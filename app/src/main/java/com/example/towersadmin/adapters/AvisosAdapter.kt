package com.example.towersadmin.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.towersadmin.R
import com.example.towersadmin.data.Aviso
import com.example.towersadmin.data.Avisos

class AvisosAdapter(var context: Context) : RecyclerView.Adapter<AvisosAdapter.AvisoViewHolder>() {

    private var listaAvisos = emptyList<Aviso>()

    fun updateListaAviso(lista: List<Aviso>){
        listaAvisos = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvisoViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.avisos_recycler_view_layout, parent,
        false)

        return AvisoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AvisoViewHolder, position: Int) {

        Log.i("xptoholder", "getBindViewHolder")

        val aviso = listaAvisos[position]

        holder.titulo_aviso.text = aviso.avisoRes.titulo_aviso
        holder.data_hora.text = aviso.avisoRes.data_hora
        holder.aviso.text = aviso.avisoRes.titulo
        holder.status.text = aviso.avisoRes.status_aviso
        holder.link.text = aviso.avisoRes.link
    }

    override fun getItemCount(): Int {

        Log.i("xptohoder", "getItemCount")

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