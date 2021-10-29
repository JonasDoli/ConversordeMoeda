package com.example.conversordemoeda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun converter(view: android.view.View) {
        calcular()
    }

    fun calcular() {

        var selectedCurrence = rgGupo
        var checar = selectedCurrence.checkedRadioButtonId


        var resultado = when (checar) {
            R.id.rbUsd ->
                "USD-BRL"


            R.id.rbBtc ->
                "BTC-BRL"


            else -> "EUR-BRL"
        }

        var imput = etValor.text.toString()
        if (imput.isEmpty())
            return

        Thread {
            var url = URL(" https://economia.awesomeapi.com.br/json/ $resultado")
            var conn = url.openConnection() as HttpsURLConnection
            try {
                val data = conn.inputStream.bufferedReader().readText()


                runOnUiThread {


                    tvValoConvertido.text = data

                }

            } finally {
                conn.disconnect()
            }

        }.start()


    }
}