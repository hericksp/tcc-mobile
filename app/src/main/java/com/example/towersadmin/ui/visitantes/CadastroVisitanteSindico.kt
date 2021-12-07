package com.example.towersadmin.ui.visitantes

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.loader.content.CursorLoader
import com.example.towersadmin.R
import com.example.towersadmin.api.ApiClient
import com.example.towersadmin.responses.VisitanteSindicoRes
import com.example.towersadmin.ui.dashboards.DashBoardMorador
import com.example.towersadmin.utils.Mask
import com.example.towersadmin.utils.SessionManager
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

class CadastroVisitanteSindico : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    lateinit var iv_image: ImageView
    lateinit var tv_foto: TextView
    lateinit var tvImage: TextView
    lateinit var tv_fotopath: TextView
    lateinit var imagePath: String


    var imageBitmap: Bitmap? = null
    val CODE_IMAGE = 100
    val EXTERNAL_CODE = 111


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_visitante_sindico)

        val dados = getSharedPreferences("TowersAdmin", MODE_PRIVATE)

        val iv_voltar: Button = findViewById(R.id.iv_voltar)
        iv_image = findViewById(R.id.iv_image)
        tv_foto = findViewById(R.id.tv_foto)
        val tv_foto: TextView = findViewById(R.id.tv_foto)
        val rg: EditText = findViewById(R.id.et_rg)
        val nome: EditText = findViewById(R.id.et_nome)
        val cpf: EditText = findViewById(R.id.et_cpf)
        val bnt_cadastrar: Button = findViewById(R.id.btn_salvar)

        cpf.addTextChangedListener(Mask.mask("##/##/####", cpf)).toString()
        rg.addTextChangedListener(Mask.mask("########-#", rg)).toString()

        bnt_cadastrar.setOnClickListener {

            val remote = ApiClient().retrofitService()

            val file = File(imagePath)
            val requestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
            val body = MultipartBody.Part.createFormData("image", file.name, requestBody)

            if (nome.text.isEmpty() || rg.text.isEmpty() || cpf.text.isEmpty() || imagePath.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            } else {

                remote.cadastroVisitanteSindico(
                    dados.getInt("user_id", 0),
                    nome.text.toString(),
                    rg.text.toString(),
                    cpf.text.toString(),
                    body,
                    dados.getInt("user_id", 0)).enqueue(object : Callback<VisitanteSindicoRes> {
                    override fun onResponse(call: Call<VisitanteSindicoRes>, response: Response<VisitanteSindicoRes>) {
                        if (response.isSuccessful) {
                            Log.i("visitanteRes", response.toString())
                            Toast.makeText(this@CadastroVisitanteSindico, "Dados salvos com sucesso!", Toast.LENGTH_LONG).show()
                            abrirDashBoardMorador()
                        } else {
                            Toast.makeText(this@CadastroVisitanteSindico, "Verfique todos os campos e tente novamente!", Toast.LENGTH_LONG).show()

                        }

                    }

                    override fun onFailure(call: Call<VisitanteSindicoRes>, t: Throwable) {
                        Toast.makeText(this@CadastroVisitanteSindico, "Algo deu errado! Erro: " + t.message, Toast.LENGTH_LONG).show()

                        Log.i("error", t.toString())
                    }
                })
            }

        }




        iv_voltar.setOnClickListener {
            finish()
        }

        tv_foto.setOnClickListener {
            abrirGaleria()
        }


    }

    private fun createImageFile(fileName: String = "temp_image"): File {
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("temp_image", ".jpg", storageDir)
    }

    private fun uriToFile(context: Context, uri: Uri, fileName: String): File? {
        context.contentResolver.openInputStream(uri)?.let { inputStream ->

            val tempFile: File = createImageFile(fileName)
            val fileOutputStream = FileOutputStream(tempFile)

            inputStream.copyTo(fileOutputStream)
            inputStream.close()
            fileOutputStream.close()

            return tempFile
        }

        return null
    }

    private fun abrirDashBoardMorador() {
        val intent = Intent(this, DashBoardMorador::class.java)
        startActivity(intent)
    }

    private fun abrirGaleria() {

        // Chamando a galeria de imagens

        val intent = Intent(Intent.ACTION_GET_CONTENT)

        // Definindo qual o tipo de conteúdo deverá ser obtido

        intent.type = "image/jpg"

        // Iniciar a Activity, mas nesse caso nós queremos que essa activity retorne algo pra gnt, a imagem

        startActivityForResult(
            Intent.createChooser(
                intent.setType("image/jpg"),
                "Escolha uma foto"
            ),
            CODE_IMAGE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMAGE && resultCode == -1) {

            val imageUri: Uri = data!!.data!!
            imagePath = getRealPathFromUri(imageUri)

            //recuperar a imagem no stream
            val stream = contentResolver.openInputStream(data!!.data!!)

            //Trnaformar Stream num BitMap


            imageBitmap = BitmapFactory.decodeStream(stream)

            //Colocar imagem no ImageView
            iv_image.setImageBitmap(imageBitmap)

        } else {
            Toast.makeText(this, "Selecione uma foto", Toast.LENGTH_LONG).show()
        }
    }

    private fun getRealPathFromUri(uri: Uri): String {
        val projection = MediaStore.Images.Media.DATA
        val loader = CursorLoader(this, uri, arrayOf(projection), null, null, null)
        val cursor = loader.loadInBackground()!!

        val column_idx: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result: String = cursor.getString(column_idx)
        cursor.close()

        return result
    }
}