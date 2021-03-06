package fr.isen.henry.erestaurant

import Datum
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import fr.isen.henry.erestaurant.databinding.ActivityListBinding

class ListActivity : MyAppActivity() {
    private lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.itemRecycler.layoutManager =LinearLayoutManager(this)

        val data = intent.extras!!.getSerializable("data") as Datum

        binding.textView.text = data.name_fr
        binding.itemRecycler.adapter = ItemAdapter(data.items){
            val intent = Intent(this, ItemDetailActivity::class.java)
            intent.putExtra("data", it)
            Log.d("LMIA00","name => ${it.name_fr}")
            startActivity(intent)
        }
    }
}