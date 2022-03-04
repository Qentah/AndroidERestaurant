package fr.isen.henry.androiderestaurant

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val products : Array<Product>, private val listener: (Product) -> Unit)
    : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val product_line = LayoutInflater.from(parent.context).inflate(R.layout.product_line,parent, false)
        return ViewHolder(product_line)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position],listener)
    }

    class ViewHolder(private val elem : View) : RecyclerView.ViewHolder(elem)
    {
        fun bind(product: Product, listener: (Product) -> Unit) = with(itemView)
        {
            findViewById<TextView>(R.id.product_name).text = product.name
            findViewById<TextView>(R.id.type).text = product.type
            setOnClickListener { listener(product) }
        }
    }

}