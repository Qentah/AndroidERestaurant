package fr.isen.henry.erestaurant

import Item
import PanierItem
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.snackbar.Snackbar
import fr.isen.henry.erestaurant.databinding.ActivityItemDetailBinding
import kotlin.math.max

class ItemDetailActivity : MyAppActivity() {
    private lateinit var binding: ActivityItemDetailBinding
    private lateinit var item: Item
    private var qty : Int = 0
        set(value) {
            field = max(value,0)
        }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        item = intent.extras!!.getSerializable("data") as Item

        binding.titleDetail.text = item.name_fr
        binding.ingListDetail.text = "Ingrédients : "+item.ingredients.joinToString(", ") { it.name_fr }
        binding.pricesDetail.text = item.prices.joinToString ("\n"){ "${it.size} : ${it.price}€" }

        binding.textQty.text = qty.toString()
        binding.btTotal.text = "Selectionnez une quantité !"
        setupViewPager()

        binding.btLess.setOnClickListener {
            qty--
            updateQty()
        }
        binding.btMore.setOnClickListener {
            qty++
            updateQty()
        }
        binding.btTotal.setOnClickListener {
            val itemPanier  = PanierItem(item.name_fr,qty,item.prices[0].price.toFloat(),item.images[0])
            PanierSingleton.update(itemPanier)
            setBadgeCount(this)
            Snackbar.make(binding.root,"$qty ${item.name_fr} bien ajouté au panier", Snackbar.LENGTH_SHORT ).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateQty(){
        binding.textQty.text = qty.toString()
        binding.btTotal.text = "Ajouter au panier pour : "+(qty*item.prices[0].price.toFloat()).toString()+"€"
    }

    private fun setupViewPager() {
        val adapter = Adapter(supportFragmentManager,lifecycle)
        item.images.forEach { adapter.addFragment(CarouselFragment.newInstance(it)) }
        binding.vpDetail.adapter = adapter
    }

    private class Adapter(fragmentManager: FragmentManager,lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        val fragments = arrayListOf<Fragment>()
        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
        fun addFragment(fragment: Fragment) {
            fragments.add(fragment)
            createFragment(fragments.indexOf(fragment))
            Log.d("IDAA00", "1" )
        }
    }
}