package com.example.textview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import com.example.textview.R
import com.example.textview.model.Fruit

class FruitAdapter(
    context: Context,
    private val resourceId: Int,
    private val listFruits: ArrayList<Fruit>
) : ArrayAdapter<Fruit>(
    context,
    resourceId,
    listFruits
) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)

        //
        val fruit = getItem(position)
        val image = view.findViewById(R.id.item_fruit_image) as ImageView
        val name = view.findViewById(R.id.item_suggest_content) as TextView
        //
        image.setImageResource(fruit.image)
        name.text = fruit.name

        //
        return view
    }

    override fun getItem(position: Int): Fruit {
        return listFruits[position]
    }

    override fun getCount(): Int {
        return listFruits.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint != null) {
                    val res = ArrayList<Fruit>()
                    for (i in 0 until listFruits.size) {
                        if (listFruits[i].name.toUpperCase().contains(constraint.toString().toUpperCase())) {
                            res.add(listFruits[i])
                        }
                    }
                    filterResults.values = res
                    filterResults.count = res.size
                } else {

                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.let {
                    if (results.count > 0) {
                        listFruits.clear()
                        listFruits.addAll(results.values as ArrayList<Fruit>)
                        notifyDataSetChanged()
                    } else {
                        notifyDataSetInvalidated()
                    }
                }
            }

        }
    }
}
