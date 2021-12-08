package com.example.towersadmin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.towersadmin.R
import com.example.towersadmin.data.Visitas

class VisitasAdapter(var context: Context) : RecyclerView.Adapter<VisitasAdapter.VisitasViewHolder>(){

    private var listaVisitas = emptyList<Visitas>()

    fun updateListaVisita(lista: List<Visitas>){
        listaVisitas = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitasViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.visitantes_recycler_view_layout, parent,false)
        return VisitasViewHolder(view)
    }

    override fun onBindViewHolder(holder: VisitasViewHolder, position: Int) {

        val visita = listaVisitas[position]
        holder.nome_visitante.text = visita.nome
        holder.rg_visitante.text = visita.rg
        holder.data_visita.text = visita.data
    }
    override fun getItemCount(): Int {

        //Log.i("xptohoder", "getItemCount")

        return listaVisitas.size
    }

    class VisitasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val nome_visitante = itemView.findViewById<TextView>(R.id.tv_nome_visitante)
        val rg_visitante = itemView.findViewById<TextView>(R.id.tv_doc_visitante)
        val data_visita = itemView.findViewById<TextView>(R.id.tv_data_visita)

    }
}
