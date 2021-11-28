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
import com.example.towersadmin.responses.VisitanteMoradorRes
import com.example.towersadmin.utils.Mask
import com.example.towersadmin.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File

class CadastroVisitanteActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    lateinit var iv_image : ImageView
    lateinit var tv_foto : TextView
    lateinit var tvImage : TextView
    lateinit var tv_fotopath: TextView


    var imageBitmap: Bitmap? = null
    val CODE_IMAGE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_visitante)

        val dados = getSharedPreferences("TowersAdmin", MODE_PRIVATE)

        val iv_voltar: Button = findViewById(R.id.iv_voltar)
        iv_image  = findViewById(R.id.iv_image)
        tv_foto = findViewById(R.id.tv_foto)
        tv_fotopath = findViewById(R.id.path_foto)
        val tv_foto: TextView = findViewById(R.id.tv_foto)
        val rg: EditText = findViewById(R.id.et_rg)
        val nome: EditText = findViewById(R.id.et_nome)
        val cpf: EditText = findViewById(R.id.et_cpf)
        val bnt_cadastrar: Button = findViewById(R.id.btn_salvar)

        cpf.addTextChangedListener(Mask.mask("##/##/####", cpf)).toString()
        rg.addTextChangedListener(Mask.mask("##.###.###-#", rg)).toString()



            bnt_cadastrar.setOnClickListener {

                val remote = ApiClient().retrofitService()

                if (nome.text.isEmpty() || rg.text.isEmpty() || cpf.text.isEmpty()) {
                    Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
                }

                else{

                val path = applicationContext.filesDir.absolutePath
                val file = File("$path/filename").toString()
                    tv_foto.text = file

                remote.cadastroVisitante(
                    dados.getInt("id", 0),
                    nome.text.toString(),
                    rg.text.toString(),
                    cpf.text.toString(),
                    file,
                    dados.getInt("id", 0)
                )
                    .enqueue(object : Callback<VisitanteMoradorRes> {
                        override fun onResponse(call: Call<VisitanteMoradorRes>, response: Response<VisitanteMoradorRes>) {
                            val response = response.body()
                            Log.i("visitanteRes", response.toString())
                            Toast.makeText(this@CadastroVisitanteActivity, "Dados salvos com sucesso!", Toast.LENGTH_LONG).show()
                            abrirDashBoardMorador()

                        }

                        override fun onFailure(call: Call<VisitanteMoradorRes>, t: Throwable) {
                            Toast.makeText(this@CadastroVisitanteActivity, "Algo deu errado!", Toast.LENGTH_LONG).show()

                            Log.i("error", t.toString())
                        }
                    })
            }

        }



        iv_voltar.setOnClickListener {
            abrirDashBoardMorador()
        }

        tv_foto.setOnClickListener {
            abrirGaleria()
        }


    }

    private fun abrirDashBoardMorador() {
        val intent = Intent(this, DashBoardMorador::class.java)
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
            val file = File("$path")
            tv_fotopath.text = file.toString()

            imageBitmap = BitmapFactory.decodeStream(stream)

            //Colocar imagem no ImageView
            iv_image.setImageBitmap(imageBitmap)

        }
        else {
            Toast.makeText(this, "Selecione uma foto", Toast.LENGTH_LONG).show()
        }
    }
}