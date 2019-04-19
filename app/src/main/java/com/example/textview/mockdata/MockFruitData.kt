package com.example.textview.mockdata

import com.example.textview.R
import com.example.textview.model.Fruit

fun getFruits(): ArrayList<Fruit> {
    return arrayListOf(
        Fruit(1, "Apple", R.drawable.apple),
        Fruit(2, "Banana", R.drawable.banana),
        Fruit(3, "Cherry", R.drawable.cherry),
        Fruit(4, "Coconut", R.drawable.coconut),
        Fruit(5, "Kiwi", R.drawable.kiwi),
        Fruit(6, "Lemon", R.drawable.lemon),
        Fruit(7, "Mango", R.drawable.mango),
        Fruit(8, "Orange", R.drawable.orange),
        Fruit(9, "Peach", R.drawable.peach)
    )
}
