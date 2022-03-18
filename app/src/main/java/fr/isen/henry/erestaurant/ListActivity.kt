package fr.isen.henry.erestaurant

import Datum
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.henry.erestaurant.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.itemRecycler.layoutManager =LinearLayoutManager(this)

        val data = intent.extras!!.getSerializable("data") as Datum

        binding.textView.text = data.name_fr
        binding.itemRecycler.adapter = ItemAdapter(data.items){}
    }
}