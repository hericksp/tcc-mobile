package com.example.towersadmin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.towersadmin.R
import com.example.towersadmin.ui.dashboards.DashBoardActivity
import com.example.towersadmin.ui.dashboards.DashBoardMorador
import com.example.towersadmin.ui.logins.LoginActivity
import com.example.towersadmin.ui.logins.LoginSindicoActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dados = getSharedPreferences("TowersAdmin", MODE_PRIVATE)

        val lembrar = dados.getBoolean("lembrar", false)

        val acesso_morador : Button = findViewById(R.id.acesso_morador)
        val acesso_sindico : Button = findViewById(R.id.acesso_sindico)

        acesso_morador.setOnClickListener {
            val abrirLoginMorador = Intent(this, LoginActivity::class.java)
            startActivity(abrirLoginMorador)
        }

        acesso_sindico.setOnClickListener {
            val abrirLoginSindico =  Intent(this, LoginSindicoActivity::class.java)
            startActivity(abrirLoginSindico)
        }
    }
    private fun abrirDashBoard() {
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun abrirDashBoardMorador() {
        val intent = Intent(this, DashBoardMorador::class.java)
        startActivity(intent)
        finish()
    }
}