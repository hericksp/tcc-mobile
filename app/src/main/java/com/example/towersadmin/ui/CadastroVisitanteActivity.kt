package com.example.towersadmin.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.towersadmin.R


class CadastroVisitanteActivity : AppCompatActivity() {

    lateinit var iv_image : ImageView
    lateinit var tv_foto : TextView

    var imageBitmap: Bitmap? = null
    val CODE_IMAGE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_visitante)

        val iv_voltar: Button = findViewById(R.id.iv_voltar)
        iv_image  = findViewById(R.id.iv_image)
        val tv_foto: TextView = findViewById(R.id.tv_foto)

        fun abrirDashBoard() {
            val intent = Intent(this, DashBoardActivity::class.java)
            startActivity(intent)
        }

        iv_voltar.setOnClickListener {
            abrirDashBoard()
        }

        tv_foto.setOnClickListener {
            abrirGaleria()
        }


    }

    private fun abrirGaleria() {

        // Chamando a galeria de imagens

        val intent = Intent(Intent.ACTION_GET_CONTENT)

        // Definindo qual o tipo de conteúdo deverá ser obtido

        intent.type = "image/*"

        // Iniciar a Activity, mas nesse caso nós queremos que essa activity retorne algo pra gnt, a imagem

        startActivityForResult(
            Intent.createChooser(
                intent,
                "Escolha uma foto"
            ),
            CODE_IMAGE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMAGE && resultCode == -1){

            //recuperar a imagem no stream
            val stream = contentResolver.openInputStream(data!!.data!!)

            //Trnaformar Stream num BitMap

            imageBitmap = BitmapFactory.decodeStream(stream)

            //Colocar imagem no ImageView
            iv_image.setImageBitmap(imageBitmap)

        }
        else {
            Toast.makeText(this, "Selecione uma foto", Toast.LENGTH_LONG).show()
        }
    }
}