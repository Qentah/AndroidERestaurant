package fr.isen.henry.erestaurant

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.henry.erestaurant.databinding.ActivityPanierBinding

class PanierActivity : MyAppActivity() {
    private lateinit var binding: ActivityPanierBinding

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPanierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.panierRecycler.layoutManager = LinearLayoutManager(this)

        binding.panierRecycler.adapter = PanierAdapter(PanierSingleton.content){
            val item  = it.copy()
            item.quantity = -1
            PanierSingleton.update(item)
            setBadgeCount(this)
            updateTotal()
            binding.panierRecycler.adapter?.notifyDataSetChanged()
        }
        updateTotal()
    }

    @SuppressLint("SetTextI18n")
    private fun updateTotal(){
        binding.totalPanier.text = "Payer ${PanierSingleton.content.sumOf { (it.quantity*it.unit_price).toDouble() }} €"
    }
}