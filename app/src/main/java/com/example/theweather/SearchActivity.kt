package com.example.theweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        var cityIntent = findViewById<TextView>(R.id.cityIntent)

        var i = intent
        if(i != null) cityIntent.setText(i.getCharSequenceExtra("city"))
    }
}