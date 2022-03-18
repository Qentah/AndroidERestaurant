package fr.isen.henry.erestaurant

import Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.henry.erestaurant.databinding.RowCatBinding
import fr.isen.henry.erestaurant.databinding.RowItemBinding

class ItemAdapter(private val datas : List<Item>, private val listener: (Item) -> Unit):RecyclerView.Adapter<ItemAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =RowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding,listener)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(datas[position])
    override fun getItemCount(): Int = datas.size

    class ViewHolder(private val binding:RowItemBinding ,private val listener: (Item) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Item){
            binding.btItem.text = item.name_fr
            val firstimg  = item.images.firstOrNull{ s -> s!="" }
            Picasso.get().load(firstimg).placeholder(R.drawable.logo).into(binding.iItem)

            binding.iItem.setOnClickListener { listener(item) }
            binding.btItem.setOnClickListener { listener(item) }
        }
    }

}