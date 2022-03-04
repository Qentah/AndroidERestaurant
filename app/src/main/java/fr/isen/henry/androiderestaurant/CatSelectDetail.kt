package fr.isen.henry.androiderestaurant

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CatSelectDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_select_detail)

        val cat = intent.extras!!.getString("category")
        findViewById<TextView>(R.id.CategoryText).text = cat

        val  lines = generateLines()

        var monRecycler = findViewById<RecyclerView>(R.id.RecycleView)

        monRecycler.layoutManager = LinearLayoutManager(this)
        monRecycler.adapter = CustomAdapter(lines.toTypedArray())
        {
            Toast.makeText(this,"Vous avez sélectionné ${it.name}",Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateLines() :ArrayList<Product>
    {
        val arrayList = ArrayList<Product>()
        arrayList.add(Product(1,"Loup","Carnivore"))
        arrayList.add(Product(2,"Gorille","Folivore et Frugivore"))
        arrayList.add(Product(3,"Eléphant","Herbivore "))
        arrayList.add(Product(4,"Tigre","Carnivore "))
        arrayList.add(Product(5,"Dauphin","Carnivore "))
        arrayList.add(Product(6,"Lion","Carnivore "))
        arrayList.add(Product(7,"Panthère noire","Carnivore "))
        arrayList.add(Product(8,"Requin Blanc","Carnivore "))
        arrayList.add(Product(9,"Pygargue à tête blanche","Carnivore "))
        return arrayList
    }
}