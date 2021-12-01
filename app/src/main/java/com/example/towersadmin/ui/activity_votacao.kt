package com.example.towersadmin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.towersadmin.R
import com.example.towersadmin.ui.dashboards.DashBoardActivity

class activity_votacao : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_votacao)

        val iv_voltar: Button = findViewById(R.id.iv_voltar)

        iv_voltar.setOnClickListener {
            abrirDashBoard()
        }
    }
    private fun abrirDashBoard() {
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
    }
}