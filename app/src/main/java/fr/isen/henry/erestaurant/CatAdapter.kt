package fr.isen.henry.erestaurant

import Datum
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.henry.erestaurant.databinding.RowCatBinding

class CatAdapter(private val datas : List<Datum>,private val listener: (Datum) -> Unit):RecyclerView.Adapter<CatAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =RowCatBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding,listener)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(datas[position])
    override fun getItemCount(): Int = datas.size

    class ViewHolder(private val binding:RowCatBinding ,private val listener: (Datum) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Datum){
            binding.iCat.clipToOutline = true

            binding.btCat.text = data.name_fr
            val firstimg  = data.items[0].images.firstOrNull{ s -> s!="" }
            Picasso.get().load(firstimg).placeholder(R.drawable.logo).into(binding.iCat)

            binding.iCat.setOnClickListener { listener(data) }
            binding.btCat.setOnClickListener { listener(data) }
        }
    }

}