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
    private val layoutResourceId: Int,
    private var arrayList: ArrayList<Fruit>
) : ArrayAdapter<Fruit>(
    context,
    layoutResourceId,
    arrayList
) {
    var mOriginalValues: ArrayList<Fruit>? = null

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                arrayList = results.values as ArrayList<Fruit>
                notifyDataSetChanged()
            }

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                val filterList = ArrayList<Fruit>()

                if (mOriginalValues == null) {
                    mOriginalValues = ArrayList(arrayList)
                }
                if (constraint == null || constraint.isEmpty()) {
                    results.count = mOriginalValues!!.size
                    results.values = mOriginalValues
                } else {
                    for (i in mOriginalValues!!.indices) {
                        val fruit = mOriginalValues!!.get(i)
                        if (fruit.name.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            filterList.add(fruit)
                        }
                    }
                    results.count = filterList.size
                    results.values = filterList
                }
                return results
            }
        }
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(layoutResourceId, parent, false)

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
        return arrayList[position]
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}
