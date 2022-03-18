package fr.isen.henry.erestaurant

import Item
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
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

        binding.titleDetail.text = item.name_fr
        binding.ingListDetail.text = item.ingredients.joinToString(",") { it.name_fr }

        updateQty()
        setupViewPager()

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