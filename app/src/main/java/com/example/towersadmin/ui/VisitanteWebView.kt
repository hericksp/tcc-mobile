package com.example.towersadmin.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.towersadmin.R

class VisitanteWebView : AppCompatActivity() {

    private lateinit var webViewVisitante: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitante_web_view)

        val dados = getSharedPreferences("TowersAdmin", MODE_PRIVATE)
        val id = dados.getInt("id", 0)

        webViewVisitante = findViewById(R.id.webview)
        setContentView(webViewVisitante)
        webViewVisitante.loadUrl("http://192.168.56.1:3333/visitantes/morador/$id")
        webViewVisitante.webViewClient = WebViewClient()
        webViewVisitante.settings.javaScriptEnabled = true

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && webViewVisitante.canGoBack()) {
            webViewVisitante.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }

}