package fr.isen.henry.erestaurant

import ResponseData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request.Method.POST
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.henry.erestaurant.databinding.ActivityHomeBinding
import org.json.JSONObject

class HomeActivity : MyAppActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.catRecycler.layoutManager =LinearLayoutManager(this)
        binding.catRecycler.adapter = CatAdapter(arrayListOf()){}

        dataRequest()
    }


    private fun dataRequest(){
        val requestBody = JSONObject("{\"id_shop\":1}")
        val req = JsonObjectRequest(
            POST,"http://test.api.catering.bluecodegames.com/menu",requestBody,
            { response ->
                Log.d("HDR00", "resultat => $response")
                dataParse(response)
            },
            { error ->
                Log.d("HDR01", "error => $error")
            }
            )
        Volley.newRequestQueue(this).add(req)
    }

    private  fun dataParse(jsonResponse: JSONObject) {
        val response = Gson().fromJson(jsonResponse.toString(),ResponseData::class.java)
        Log.d("HDP00", "response => ${jsonResponse.toString(1)}")
        Log.d("HDP01", "response => $response")
        binding.catRecycler.adapter =CatAdapter(response.data){
            val intent = Intent(this, ListActivity::class.java)
            intent.putExtra("data", it)
            Log.d("HDPCA00","name => ${it.name_fr}")
            startActivity(intent)
        }
    }
}

