package fr.isen.henry.erestaurant

import ResponseData
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request.Method.POST
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.henry.erestaurant.databinding.ActivityHomeBinding
import org.json.JSONObject

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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
        for (data in response.data){
            Log.d("HDP02", "data => ${data}")
        }
    }
}