package fr.isen.henry.erestaurant

import Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.isen.henry.erestaurant.databinding.ActivityItemDetailBinding

class ItemDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.extras!!.getSerializable("data") as Item

        val firstimg  = item.images.firstOrNull{ s -> s!="" }
        Picasso.get().load(firstimg).placeholder(R.drawable.logo).into(binding.iDetail)

        binding.titleDetail.text = item.name_fr
        binding.ingListDetail.text = item.ingredients.joinToString(",") { it.name_fr }
    }
}