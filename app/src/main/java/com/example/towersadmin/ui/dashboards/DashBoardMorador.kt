package com.example.towersadmin.ui.dashboards

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View
import android.widget.TextView
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.towersadmin.R
import com.example.towersadmin.chat.EntranceActivity
import com.example.towersadmin.ui.*
import com.example.towersadmin.ui.agendamento.AgendamentoEspaco
import com.example.towersadmin.ui.agendamento.Agendamentos
import com.example.towersadmin.ui.aviso.activity_aviso_morador
import com.example.towersadmin.ui.visitantes.CadastroVisitanteActivity
import com.google.android.material.navigation.NavigationView;


class DashBoardMorador : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board_morador)

        val dados = getSharedPreferences("TowersAdmin", MODE_PRIVATE)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.title = null

        drawerLayout = findViewById(R.id.drawer_layout_morador)
        navView = findViewById(R.id.nav_view_morador)

        val header: View = navView.getHeaderView(0)
        val header_nome: TextView = header.findViewById(R.id.header_nome)
        val header_email: TextView = header.findViewById(R.id.header_email)

        val nome_header = dados.getString("user_name", "")
        val email_header = dados.getString("user_email", "")

        header_nome.text = nome_header
        header_email.text = email_header

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home_morador -> {
                drawerLayout.close()
            }
            R.id.nav_agendamento -> {
                abrirAgendamentoEspaco()
            }
            R.id.nav_conversas -> {
                chat()
            }
            R.id.nav_novo_agendamento -> {
                novoAgendamento()
            }
            R.id.nav_pagamento_condominal -> {
                Toast.makeText(this, "Tá Deveno Hein!", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_assembleias -> {
                Toast.makeText(this, "Assembleias Clicado", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_votacao -> {
                abrirVotacao()
            }
            R.id.nav_aviso -> {
                abrirMuralDeAvsiso()
            }
            R.id.nav_cadastro_visitantes -> {
                abrirCadastroVisitantesMorador()
            }
            R.id.nav_sair -> {
                abrirCaixaDialogo()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true


    }

    private fun abrirCaixaDialogo() {

        val caixaDeDialogo = AlertDialog.Builder(this)

        caixaDeDialogo.setTitle("Tem certeza que deseja sair?")
        caixaDeDialogo.setMessage("Você será redirecionado para a tela de início!")
        caixaDeDialogo.setPositiveButton("Sim") { dialogInterface: DialogInterface, i: Int ->
            logout()
            abrirMain()
        }
        caixaDeDialogo.setNegativeButton("Não") { dialogInterface: DialogInterface, i: Int ->
            closeContextMenu()
            drawerLayout.open()
        }
        caixaDeDialogo.show()
    }

    private fun chat(){
        val intent = Intent(this, EntranceActivity::class.java)
        startActivity(intent)
    }

    private fun logout(){
        val dados = getSharedPreferences("TowersAdmin", Context.MODE_PRIVATE)
        val editor = dados.edit()
        editor.putBoolean("lembrar", false)
        editor.apply()
    }

    private fun abrirCadastroVisitantesMorador() {
        val intent = Intent(this, CadastroVisitanteActivity::class.java)
        startActivity(intent)
    }

    private fun abrirMuralDeAvsiso() {
        val intent = Intent(this, activity_aviso_morador::class.java)
        startActivity(intent)
    }

    private fun abrirReclamacoes() {
        val intent = Intent(this, activity_reclamacoes::class.java)
        startActivity(intent)
    }

    private fun abrirVotacao() {
        val intent = Intent(this, activity_votacao::class.java)
        startActivity(intent)
    }

    private fun abrirAgendamentoEspaco() {
        val intent = Intent(this, Agendamentos::class.java)
        startActivity(intent)
    }

    private fun abrirMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun novoAgendamento(){
        val intent = Intent(this, AgendamentoEspaco::class.java)
        startActivity(intent)
    }

}


