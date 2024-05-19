package com.example.theweather

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    private var launcher:ActivityResultLauncher<Intent>? = null

    var currentCity = "Москва"
    public var APIKEY = "b828b2120a2a56980c3f3c83d23befea"
    public var site = "https://api.openweathermap.org/data/2.5/weather?q=${currentCity}&units=metric&appid=b828b2120a2a56980c3f3c83d23befea&lang=ru"
    lateinit var tempValue:TextView
    lateinit var humidValue:TextView
    lateinit var windValue:TextView
    lateinit var pressureValue:TextView
    lateinit var descriptionText:TextView
    lateinit var cityName:TextView
    lateinit var sPref:SharedPreferences
    var mRequestQueue: RequestQueue?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRequestQueue = Volley.newRequestQueue(this)
        setContentView(R.layout.activity_main)

        sPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        currentCity = sPref.getString("selectedCity", "Москва").toString()
        site = "https://api.openweathermap.org/data/2.5/weather?q=${currentCity}&units=metric&appid=b828b2120a2a56980c3f3c83d23befea&lang=ru"

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

            result:ActivityResult ->

            if(result.resultCode == RESULT_OK){
                val textCity = result.data?.getStringExtra("selectedCity")

                with(sPref.edit()){
                    putString("selectedCity", textCity)
                    apply()
                }

                currentCity = sPref.getString("selectedCity", "Москва").toString()


                site = "https://api.openweathermap.org/data/2.5/weather?q=${currentCity}&units=metric&appid=b828b2120a2a56980c3f3c83d23befea&lang=ru"
                tempValue = findViewById(R.id.tempText)
                humidValue = findViewById(R.id.humidText)
                windValue = findViewById(R.id.windText)
                pressureValue = findViewById(R.id.pressureText)
                descriptionText = findViewById(R.id.description)
                cityName = findViewById(R.id.cityName)
                getWeather(site)
            }

        }

        tempValue = findViewById(R.id.tempText)
        humidValue = findViewById(R.id.humidText)
        windValue = findViewById(R.id.windText)
        pressureValue = findViewById(R.id.pressureText)
        descriptionText = findViewById(R.id.description)
        cityName = findViewById(R.id.cityName)
        getWeather(site)
    }

    private fun getWeather(site:String) {
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, site, null,
            {response ->
                var main = response.getJSONObject("main")
                tempValue.text = "${main.getInt("temp")}°"
                humidValue.text = "Влажность ${main.getInt("humidity")}%"
                windValue.text = "Скорость ветра ${response.getJSONObject("wind").getInt("speed")} м/с"
                pressureValue.text = "Давление ${main.getInt("pressure")} гПа"
                descriptionText.text = "${response.getJSONArray("weather").getJSONObject(0)
                    .getString("description")}"
                cityName.text = currentCity
            },
            {error ->
                Toast.makeText(this, "Ошибка при загрузке!", Toast.LENGTH_LONG).show()

            })
        mRequestQueue!!.add(jsonObjectRequest)
    }


    fun onClickStart(view:View)
    {
        launcher?.launch(Intent(this, SearchActivity::class.java))
    }

}

