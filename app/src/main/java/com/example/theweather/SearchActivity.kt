package com.example.theweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_search)

        val cities = arrayOf("Москва", "Санкт Петербург", "Нижний Новгород", "Волгоград", "Тула",
            "Хабаровск", "Зеленоград", "Истра", "Воронеж", "Йошкар-Ола", "Казань", "Ашхабад",
            "Пермь", "Калининград", "Набережные челны","Краснодар", "Мурманск", "Ростов-на-Дону")

        val citiesAdapter: ArrayAdapter<String> = ArrayAdapter(
        this, R.layout.custom_simple_list_item_1, cities)

        findViewById<ListView>(R.id.cityList).adapter = citiesAdapter

        findViewById<SearchView>(R.id.searchView).isIconified = false



        findViewById<SearchView>(R.id.searchView).setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                findViewById<SearchView>(R.id.searchView).isIconified = false
                //findViewById<SearchView>(R.id.searchView).clearFocus()

                if(cities.contains(query)){
                    citiesAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                citiesAdapter.filter.filter(newText)
                return false
            }
        })

       /* var cityIntent = findViewById<TextView>(R.id.cityIntent)

        var i = intent
        if(i != null) cityIntent.setText(i.getCharSequenceExtra("city"))*/
    }
}