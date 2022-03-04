package fr.isen.henry.androiderestaurant

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.isen.henry.androiderestaurant.R.id

class CatSelectDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_select_detail)

        val cat = intent.extras!!.getString("category")
        findViewById<TextView>(R.id.CategoryText).text = cat
    }
}