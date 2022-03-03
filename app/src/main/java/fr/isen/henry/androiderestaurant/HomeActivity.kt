package fr.isen.henry.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val button = findViewById<Button>(R.id.btn1)
        val button2 = findViewById<Button>(R.id.btn2)
        val button3 = findViewById<Button>(R.id.btn3)

        button.setOnClickListener {
            displayToast(button.text)
        }
        button2.setOnClickListener {
            displayToast(button2.text)
        }
        button3.setOnClickListener {
            displayToast(button3.text)
        }

    }

    private fun displayToast(text: CharSequence){
        Toast.makeText(this@HomeActivity, text, Toast.LENGTH_SHORT).show()
    }
}
