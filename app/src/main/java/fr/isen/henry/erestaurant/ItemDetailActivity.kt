package fr.isen.henry.erestaurant

import Item
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.isen.henry.erestaurant.databinding.ActivityItemDetailBinding
import kotlin.math.max

class ItemDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemDetailBinding
    private lateinit var item: Item
    private var qty : Int = 0
        set(value) {
            field = max(value,0)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        item = intent.extras!!.getSerializable("data") as Item

        val firstimg  = item.images.firstOrNull{ s -> s!="" }
        Picasso.get().load(firstimg).placeholder(R.drawable.logo).into(binding.iDetail)

        binding.titleDetail.text = item.name_fr
        binding.ingListDetail.text = item.ingredients.joinToString(",") { it.name_fr }

        updateQty()

        binding.btLess.setOnClickListener {
            qty--
            updateQty()
        }
        binding.btMore.setOnClickListener {
            qty++
            updateQty()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateQty(){
        binding.textQty.text = qty.toString()
        binding.btTotal.text = "Ajouter au panier pour : "+(qty*item.prices[0].price.toFloat()).toString()+"€"
    }
}