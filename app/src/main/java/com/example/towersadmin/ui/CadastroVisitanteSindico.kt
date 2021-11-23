package com.example.towersadmin.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.towersadmin.R
import com.example.towersadmin.api.ApiClient
import com.example.towersadmin.data.VisitanteMoradorRes
import com.example.towersadmin.data.VisitanteSindicoRes
import com.example.towersadmin.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File

class CadastroVisitanteSindico : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager


    lateinit var iv_image : ImageView
    lateinit var tv_foto : TextView

    var imageBitmap: Bitmap? = null
    val CODE_IMAGE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_visitante_sindico)

        val dados = getSharedPreferences("TowersAdmin", MODE_PRIVATE)

        val iv_voltar: Button = findViewById(R.id.iv_voltar)
        iv_image  = findViewById(R.id.iv_image)
        val tv_foto: TextView = findViewById(R.id.tv_foto)
        val rg: EditText = findViewById(R.id.et_rg)
        val nome: EditText = findViewById(R.id.et_nome)
        val cpf: EditText = findViewById(R.id.et_cpf)
        val bnt_cadastrar: Button = findViewById(R.id.btn_salvar)



            bnt_cadastrar.setOnClickListener {

                val remote = ApiClient().retrofitService()

                if (nome.text.isEmpty() || rg.text.isEmpty() || cpf.text.isEmpty()) {
                    Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
                }

                else{



                val path = applicationContext.filesDir.absolutePath
                val file = File("$path/filename")


                remote.cadastroVisitanteSindico(
                    dados.getInt("id", 0),
                    nome.text.toString(),
                    rg.text.toString(),
                    cpf.text.toString(),
                    file.toString(),
                    dados.getInt("id", 0)
                )
                    .enqueue(object : Callback<VisitanteSindicoRes> {
                        override fun onResponse(call: Call<VisitanteSindicoRes>, response: Response<VisitanteSindicoRes>) {
                            val response = response.body()
                            Log.i("response", response.toString())
                            Toast.makeText(this@CadastroVisitanteSindico, "Dados salvos com sucesso!", Toast.LENGTH_LONG).show()
                            abrirDashBoard()

                        }

                        override fun onFailure(call: Call<VisitanteSindicoRes>, t: Throwable) {
                            Toast.makeText(this@CadastroVisitanteSindico, "Algo deu errado!", Toast.LENGTH_LONG).show()

                            Log.i("error", t.toString())
                        }
                    })
            }

        }



        iv_voltar.setOnClickListener {
            abrirDashBoard()
        }

        tv_foto.setOnClickListener {
            abrirGaleria()
        }


    }

    private fun abrirDashBoard() {
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
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

            val path = applicationContext.filesDir.absolutePath
            val file = File("$path/filename")

            imageBitmap = BitmapFactory.decodeStream(stream)

            //Colocar imagem no ImageView
            iv_image.setImageBitmap(imageBitmap)

        }
        else {
            Toast.makeText(this, "Selecione uma foto", Toast.LENGTH_LONG).show()
        }
    }

    private fun converterBitmapParaByteArray(imagem: Bitmap?): ByteArray? {

        val stream = ByteArrayOutputStream()

        if (imagem != null){

            val imageArray = imagem.compress(Bitmap.CompressFormat.PNG, 0, stream)
            return stream.toByteArray()
        }

        return null
    }
}