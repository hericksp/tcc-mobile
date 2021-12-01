package com.example.towersadmin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.towersadmin.R
import com.example.towersadmin.ui.dashboards.DashBoardActivity

class activity_reclamacoes : AppCompatActivity() {

    lateinit var iv_voltar : Button
    lateinit var rv_reclamacoes : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reclamacoes)

        val iv_voltar: Button = findViewById(R.id.iv_voltar)

        fun abrirDashBoard() {
            val intent = Intent(this, DashBoardActivity::class.java)
            startActivity(intent)
        }

        iv_voltar.setOnClickListener {
            abrirDashBoard()
        }

    }
}