package fr.isen.henry.erestaurant

import Panier
import PanierItem
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import java.io.File
import java.io.FileReader
import java.io.FileWriter

@SuppressLint("StaticFieldLeak")
object PanierSingleton{
    var weight = 0
        get() = content.sumOf { it.quantity }
    var panier : Panier = Panier(arrayListOf())
    var content = panier.content
    private var init = 0
    private lateinit var context: Context

    fun update(item : PanierItem){
        if(item.quantity == 0) return
        if(content.any{it.name_fr == item.name_fr}){
            val existElem = content.first{it.name_fr == item.name_fr}
            existElem.quantity += item.quantity
            if(existElem.quantity<=0)
                content.remove(existElem)
        }else{
            if(item.quantity>0)
            content.add(item)
        }
        savePanier()
    }
    private fun savePanier(){
        if(this::context.isInitialized) {
            try {
                val panierJson = Gson().toJson(panier)
                val writer = FileWriter(File(context.filesDir,"panier.json"))
                writer.write(panierJson.toString())
                writer.close()
            } catch (e: Exception) {
            }
        }
    }
    fun setContext(context: Context) {
        this.context = context

        if(init == 0){
            init = 1
            try {
                val reader = FileReader(File(PanierSingleton.context.filesDir, "panier.json"))
                val read = reader.readText()
                val panier = Gson().fromJson(read, Panier::class.java)
                this.panier.content.addAll(panier.content)
                reader.close()
            } catch (e: Exception) {
            }
        }
    }
}