package com.example.theweather

import android.content.Intent
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

        val cities = arrayOf("Абакан", "Ашхабад", "Брянск", "Владикавказ", "Владимир", "Волгоград",
            "Воронеж", "Грозный", "Екатеринбург", "Истра", "Казань", "Калининград", "Калуга", "Киров",
            "Кострома", "Краснодар", "Курган", "Липецк", "Махачкала", "Москва", "Мурманск", "Набережные челны",
            "Нижний Новгород", "Нижневартовск", "Новороссийск", "Пермь", "Ростов-на-Дону", "Санкт Петербург",
            "Севастополь", "Симферополь", "Сочи", "Сургут", "Тула", "Тюмень", "Тольятти", "Тверь",
            "Хабаровск", "Йошкар-Ола", "Зеленоград", "Омск")

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

        findViewById<ListView>(R.id.cityList).setOnItemClickListener { parent, view, position, id ->
            val i = Intent()
            val selectedCity = cities[position]
            i.putExtra("selectedCity", selectedCity)
            setResult(RESULT_OK, i)
            finish()
            //val intent = Intent(this@SearchActivity, MainActivity::class.java)
            //intent.putExtra("selectedCity", selectedCity)
            //startActivity(intent)
        }




        /* var cityIntent = findViewById<TextView>(R.id.cityIntent)

         var i = intent
         if(i != null) cityIntent.setText(i.getCharSequenceExtra("city"))*/
    }
}