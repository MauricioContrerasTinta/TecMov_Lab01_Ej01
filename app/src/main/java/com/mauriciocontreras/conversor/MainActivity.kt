package com.mauriciocontreras.conversor

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spOrigen: Spinner
    private lateinit var spDestino: Spinner
    private lateinit var etMonto: EditText
    private lateinit var tvResultado: TextView
    private lateinit var btnConvertir: Button

    private val tasas = mapOf(
        "USD" to 1.0,
        "PEN" to 3.5,
        "EUR" to 0.92,
        "GBP" to 0.78,
        "INR" to 83.0,
        "BRL" to 5.0,
        "MXN" to 17.0,
        "CNY" to 7.2,
        "JPY" to 150.0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spOrigen = findViewById(R.id.spOrigen)
        spDestino = findViewById(R.id.spDestino)
        etMonto = findViewById(R.id.etMonto)
        tvResultado = findViewById(R.id.tvResultado)
        btnConvertir = findViewById(R.id.btnConvertir)

        val monedas = arrayOf(
            "USD - Dólar",
            "PEN - Sol",
            "EUR - Euro",
            "GBP - Libra",
            "INR - Rupia",
            "BRL - Real",
            "MXN - Peso",
            "CNY - Yuan",
            "JPY - Yen"
        )

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, monedas)

        spOrigen.adapter = adapter
        spDestino.adapter = adapter

        btnConvertir.setOnClickListener {

            val textoMonto = etMonto.text.toString()

            if (textoMonto.isEmpty()) {
                Toast.makeText(this, "Ingrese un monto", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val monto = textoMonto.toDouble()

            val origen = spOrigen.selectedItem.toString().substring(0, 3)
            val destino = spDestino.selectedItem.toString().substring(0, 3)

            val montoUSD = monto / tasas[origen]!!
            val resultado = montoUSD * tasas[destino]!!

            tvResultado.text = "Resultado: %.2f".format(resultado)
        }
    }
}