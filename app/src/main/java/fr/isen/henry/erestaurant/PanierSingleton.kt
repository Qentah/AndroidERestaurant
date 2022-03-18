package fr.isen.henry.erestaurant

import PanierItem
import android.util.Log
import com.google.gson.JsonArray

object PanierSingleton{
    var value = 0
    private var content :MutableList<PanierItem> = mutableListOf()
    fun add(item : PanierItem){
        if(item.quantity == 0) return
        if(content.any{it.name_fr == item.name_fr}){
            val existElem = content.first{it.name_fr == item.name_fr}
            existElem.quantity += item.quantity
            if(existElem.quantity<=0)
                content.remove(existElem)
        }else{
            content.add(item)
        }
        Log.d("PSA00", content.toString())
    }
}