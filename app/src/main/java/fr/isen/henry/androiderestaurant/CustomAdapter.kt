package fr.isen.henry.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CustomAdapter(private val products : Array<Product>, private val listener: (Product) -> Unit)
    : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val productLine = LayoutInflater.from(parent.context).inflate(R.layout.product_line,parent, false)
        return ViewHolder(productLine)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position],listener)
    }

    class ViewHolder(elem : View) : RecyclerView.ViewHolder(elem)
    {
        fun bind(product: Product, listener: (Product) -> Unit) = with(itemView)
        {
            findViewById<TextView>(R.id.product_name).text = product.name
            findViewById<TextView>(R.id.type).text = product.type

            val ivBasicImage = findViewById<ImageView>(R.id.image);

            val url = product.type.ifEmpty { null }
            Picasso.get().load(url).into(ivBasicImage)

            setOnClickListener { listener(product) }
        }
    }

}