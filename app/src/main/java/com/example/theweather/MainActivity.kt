package com.example.theweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    var currentCity = "Moscow"
    public var APIKEY = "b828b2120a2a56980c3f3c83d23befea"
    public var site = "https://api.openweathermap.org/data/2.5/weather?q=${currentCity}&units=metric&appid=b828b2120a2a56980c3f3c83d23befea&lang=ru"
    lateinit var tempValue:TextView
    lateinit var humidValue:TextView
    lateinit var windValue:TextView
    lateinit var pressureValue:TextView
    var mRequestQueue: RequestQueue?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRequestQueue = Volley.newRequestQueue(this)
        setContentView(R.layout.activity_main)
        tempValue = findViewById(R.id.tempText)
        humidValue = findViewById(R.id.humidText)
        windValue = findViewById(R.id.windText)
        pressureValue = findViewById(R.id.pressureText)
        getWeather()
    }

    private fun getWeather() {
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, site, null,
            {response ->
                var main = response.getJSONObject("main")
                tempValue.text = "${main.getInt("temp")}°"
                humidValue.text = "${main.getInt("humidity")}%"
                windValue.text = "${response.getJSONObject("wind").getInt("speed")} м/с"
                pressureValue.text = "${main.getInt("pressure")} гПа"
            },
            {error ->
                Toast.makeText(this, "Ошибка при загрузке!", Toast.LENGTH_LONG).show()

            })
        mRequestQueue!!.add(jsonObjectRequest)
    }

    fun onClickStart(view: View)
    {
        val intent = Intent(this, SearchActivity::class.java).apply {
            putExtra("city", currentCity)
        }
        startActivity(intent)
    }

}