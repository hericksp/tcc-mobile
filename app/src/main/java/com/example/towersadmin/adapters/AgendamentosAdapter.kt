package com.example.towersadmin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.towersadmin.R
import com.example.towersadmin.data.Agendamentos
import org.w3c.dom.Text

class AgendamentosAdapter (var context: Context) : RecyclerView.Adapter<AgendamentosAdapter.AgendamentosViewHolder>(){

    private var listaAgendamentos = emptyList<Agendamentos>()

    fun updateListaAgendamentos(lista: List<Agendamentos>){
        listaAgendamentos = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendamentosViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.agendamento_recylcer_view_layout, parent, false)
        return  AgendamentosViewHolder(view)
    }

    override fun onBindViewHolder(holder: AgendamentosViewHolder, position: Int) {

        val agendamento = listaAgendamentos[position]

        holder.nome_responsavel.text = agendamento.nome
        holder.data.text = agendamento.data
        holder.hora_inicio.text = agendamento.horaInicio
        holder.hora_termino.text = agendamento.horaTermino
    }

    override fun getItemCount(): Int {

        return listaAgendamentos.size
    }

    class AgendamentosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nome_responsavel = itemView.findViewById<TextView>(R.id.nome_responsavel)
        val data = itemView.findViewById<TextView>(R.id.data_agend)
        val hora_inicio = itemView.findViewById<TextView>(R.id.hora_inicio)
        val hora_termino = itemView.findViewById<TextView>(R.id.et_hora_termino)
    }

}