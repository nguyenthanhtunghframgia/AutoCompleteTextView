package com.example.textview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.Toast
import com.example.textview.adapter.FruitAdapter
import com.example.textview.mockdata.getFruits
import com.example.textview.model.Fruit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var listFruits: ArrayList<Fruit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initAutoComplete()
    }

    private fun initAutoComplete() {
        val adapter = FruitAdapter(
            context = this,
            layoutResourceId = R.layout.item_suggest,
            arrayList = listFruits
        )

        tv_autocomplete.setAdapter(adapter)

        tv_autocomplete.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                adapter.filter.filter(s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        tv_autocomplete.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id -> Toast.makeText(this@MainActivity, listFruits[position].name, Toast.LENGTH_LONG).show() }
    }

    private fun initData() {
        listFruits = getFruits()
    }
}
