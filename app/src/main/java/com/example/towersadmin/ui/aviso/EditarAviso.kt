package com.example.towersadmin.ui.aviso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.towersadmin.R

class EditarAviso : AppCompatActivity() {

    lateinit var titulo_aviso : TextView
    lateinit var data_hora : TextView
    lateinit var editar_aviso : TextView
    lateinit var main_aviso : TextView
    lateinit var status : TextView
    lateinit var link : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_aviso)
    }
}