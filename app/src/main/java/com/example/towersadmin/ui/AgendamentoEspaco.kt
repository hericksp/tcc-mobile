package com.example.towersadmin.ui

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.towersadmin.R

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
class AgendamentoEspaco : AppCompatActivity(), CalendarView.OnDateChangeListener {

    lateinit var dataFinal : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agendamento_espaco)

        val selecionarData : CalendarView = findViewById(R.id.calendarView)
        val iv_voltar: Button = findViewById(R.id.iv_voltar)
        dataFinal = findViewById(R.id.dataFinal)
        selecionarData.setOnDateChangeListener(this)

        iv_voltar.setOnClickListener {
            abrirDashBoard()
        }


    }
    private fun abrirDashBoard() {
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
    }

    override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {

        var mesSelecionado = (month + 1)
        var dataF = String.format("%02d", dayOfMonth) +"/"+ String.format("%02d", mesSelecionado) +"/"+ year

        dataFinal.text = dataF

    Toast.makeText(this, "Data Selecionada: "+dataF, Toast.LENGTH_SHORT).show()

    }

}