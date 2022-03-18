package fr.isen.henry.erestaurant

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request.Method.POST
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
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
                Log.d("API00", "resultat => $response")
            },
            { error ->
                Log.d("API01", "error => $error")
            }
            )
        Volley.newRequestQueue(this).add(req)
    }
}