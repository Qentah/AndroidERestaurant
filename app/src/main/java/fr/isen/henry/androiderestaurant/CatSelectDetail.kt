package fr.isen.henry.androiderestaurant

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonObject
import fr.isen.henry.androiderestaurant.databinding.ActivityCatSelectDetailBinding
import org.json.JSONObject


class CatSelectDetail : AppCompatActivity() {

    private lateinit var binding: ActivityCatSelectDetailBinding
    private lateinit var categorie: String
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatSelectDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        categorie = intent.extras!!.getString("category").toString()
        binding.CategoryText.text = categorie

        //Log.d("API", data.toString())
        recycler = binding.RecycleView
        recycler.layoutManager = LinearLayoutManager(this)

        postVolley()
    }

    private fun postVolley() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"

        val requestBody = JSONObject("{\"id_shop\":1}")
        val req =
            object : JsonObjectRequest(Method.POST, url,requestBody,
                Response.Listener { response ->
                    callGenerator(response)
                },
                Response.ErrorListener { error ->
                    Log.d("API", "error => $error")
                }
            ){
            }
        queue.add(req)
    }


    private fun callGenerator(strResp: JSONObject) {

        //Log.d("API", strResp.toString())
        val js = Gson().fromJson(strResp.toString(), JsonObject::class.java)

        val data = js.get("data").asJsonArray

        val  lines:ArrayList<Product> = ArrayList()
        for(type in data){
            val cat = type.asJsonObject
            if (categorie == cat.get("name_fr").asString){
                Log.d("API", categorie)
                for (elem in cat.get("items").asJsonArray){
                    Log.d("API", elem.toString())
                    val name = elem.asJsonObject.get("name_fr").asString
                    val size = elem.asJsonObject.get("images")!!.asJsonArray[0].asString
                    lines.add(Product(1,name,size))
                }
            }
        }
        recycler.adapter = CustomAdapter(lines.toTypedArray())
        {
            Toast.makeText(this,"Vous avez sélectionné ${it.name}",Toast.LENGTH_SHORT).show()
        }
    }

}