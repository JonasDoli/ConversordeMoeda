package com.example.conversordemoeda

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.math.floor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun converter(view: android.view.View) {
        imputvalore()

    }


    fun imputvalore() {

        var selectedCurrence = rgGupo
        var checar = selectedCurrence.checkedRadioButtonId


        var resultado = when (checar) {
            R.id.rbUsd ->
                "USD-BRL"


            R.id.rbBtc ->
                "BTC-BRL"


            else -> "EUR-BRL"


        }
        when (checar) {
            R.id.rbUsd -> tvModelo.text = "1 Dolar"
            R.id.rbBtc -> tvModelo.text = "1 Bitcoin"
            R.id.rbEur -> tvModelo.text = "1 Euro"
        }


        var imput = etValor.text.toString()
        if (imput.isEmpty())
            return


        Thread {
            var url = URL(" https://economia.awesomeapi.com.br/last/$resultado")

            var conn = url.openConnection() as HttpsURLConnection
            try {
                val data = conn.inputStream.bufferedReader().readText()
                val obj = JSONObject(data)



                runOnUiThread {
                    if (checar == R.id.rbUsd) {

                        var usd = obj.getJSONObject("USDBRL")
                        var alta = usd.getString("high")
                        var baixa = usd.getString("low")
                        var venda = usd.getString("ask")
                        var compra = usd.getString("bid")
                        var digitado = etValor.text.toString()
                        var calc = venda.toFloat() * digitado.toFloat()

                        tvValorUp.text = alta.toString()
                        tvValorDow.text = baixa.toString()
                        tvVenda.text = venda.toString()
                        tvCompra.text = compra.toString()
                        resultado = digitado
                        tvValoConvertido.text = calc.toString()
                    }
                    if (checar == R.id.rbEur) {

                        var usd = obj.getJSONObject("EURBRL")
                        var alta = usd.getString("high")
                        var baixa = usd.getString("low")
                        var venda = usd.getString("ask")
                        var compra = usd.getString("bid")
                        var digitado = etValor.text.toString()
                        var calc = venda.toFloat() * digitado.toFloat()

                        tvValorUp.text = alta.toString()
                        tvValorDow.text = baixa.toString()
                        tvVenda.text = venda.toString()
                        tvCompra.text = compra.toString()
                        resultado = digitado
                        tvValoConvertido.text = calc.toString()
                    }
                    if (checar == R.id.rbBtc) {

                        var usd = obj.getJSONObject("BTCBRL")
                        var alta = usd.getString("high")
                        var baixa = usd.getString("low")
                        var venda = usd.getString("ask")
                        var compra = usd.getString("bid")
                        var digitado = etValor.text.toString()
                        var calc = venda.toFloat() * digitado.toFloat()

                        tvValorUp.text = alta.toString()
                        tvValorDow.text = baixa.toString()
                        tvVenda.text = venda.toString()
                        tvCompra.text = compra.toString()
                        resultado = digitado
                        tvValoConvertido.text = calc.toString()
                    }


                }

            } finally {
                conn.disconnect()
            }

        }.start()


    }
}