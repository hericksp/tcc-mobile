package com.example.towersadmin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.towersadmin.R
import com.example.towersadmin.api.ApiClient
import com.example.towersadmin.data.LoginRequest
import com.example.towersadmin.data.LoginResponse
import com.example.towersadmin.data.LoginSindicoResponse
import com.example.towersadmin.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginSindicoActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_sindico)

        val dados = getSharedPreferences("TowersAdmin", MODE_PRIVATE)

        val lembrar = dados.getBoolean("lembrar", false)

        if (lembrar == true){
            abrirDashBoard()
        }

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        val et_email: EditText = findViewById(R.id.et_email)
        val et_senha: EditText = findViewById(R.id.et_senha)
        val btn_continuar: Button = findViewById(R.id.btn_continuar)
        val tv_erro: TextView = findViewById(R.id.tv_mensagem_de_erro)
        val tv_esqueceu_senha: TextView = findViewById(R.id.esqueceu_senha)
        val check_lembrar : CheckBox = findViewById(R.id.check_lembrar)

        btn_continuar.setOnClickListener {

            val remote = apiClient.retrofitService()

            remote.loginSindico(LoginRequest(et_email.text.toString(), et_senha.text.toString()))
                    .enqueue(object : Callback<LoginSindicoResponse> {

                        override fun onResponse(
                            call: Call<LoginSindicoResponse>, response: Response<LoginSindicoResponse>) {
                            val loginResponse = response.body()

                            if (loginResponse?.sindicoId != null && check_lembrar.isChecked) {
                                sessionManager.saveAuthToken(
                                    loginResponse.sindicoId, loginResponse.apartamento.id, loginResponse.apartamento.numero,
                                    loginResponse.bloco.id, loginResponse.bloco.nome,
                                    loginResponse.condominio.id, loginResponse.condominio.nome, loginResponse.condominio.cnpj, loginResponse.name,
                                    loginResponse.surname, loginResponse.cpf,
                                    loginResponse.birth, loginResponse.email,
                                    loginResponse.token, lembrar = true
                                )

                                abrirDashBoard()

                                Log.i("response", response.body().toString())

                            }
                            else if(loginResponse?.sindicoId != null) {
                                sessionManager.saveAuthToken(
                                    loginResponse.sindicoId,  loginResponse.apartamento.id, loginResponse.apartamento.numero,
                                    loginResponse.bloco.id, loginResponse.bloco.nome,
                                    loginResponse.condominio.id, loginResponse.condominio.nome, loginResponse.condominio.cnpj,
                                    loginResponse.name, loginResponse.surname, loginResponse.cpf,
                                    loginResponse.birth, loginResponse.email,
                                    loginResponse.token, lembrar = false
                                )

                                Log.i("response", loginResponse.toString())
                                abrirDashBoard()

                            } else {
                                Toast.makeText(this@LoginSindicoActivity, "Email ou senha incorretos!", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginSindicoResponse>, t: Throwable) {
                            Toast.makeText(this@LoginSindicoActivity, "Algo deu errado", Toast.LENGTH_SHORT).show()
                        }
                    })

        }

        tv_esqueceu_senha.setOnClickListener {
            Toast.makeText(applicationContext, "Contacte a Administração do seu condominio!", Toast.LENGTH_LONG).show()
        }

    }
    private fun abrirDashBoard() {
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
        finish()
    }

}
