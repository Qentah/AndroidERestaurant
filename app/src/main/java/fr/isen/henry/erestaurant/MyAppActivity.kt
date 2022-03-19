package fr.isen.henry.erestaurant

import android.content.Context
import android.content.Intent
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity


abstract class MyAppActivity : AppCompatActivity() {
    private lateinit var defaultMenu : Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PanierSingleton.setContext(this)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean{
        if(this::defaultMenu.isInitialized) {
            setBadgeCount(this, PanierSingleton.weight)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        defaultMenu = menu
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if(item.itemId == R.id.action_cart)
            startActivity(Intent(this, PanierActivity::class.java))
        return true
    }

    override fun onResume(){
        super.onResume()
        if(this::defaultMenu.isInitialized){
            setBadgeCount(this)
        }
    }

    protected fun setBadgeCount(context:Context){
        setBadgeCount(context, PanierSingleton.weight)
    }

    @Deprecated("Délegation de la gestion du stock au PanierSingleton")
    private fun setBadgeCount(context:Context, value :Int) {
        val menuItem: MenuItem = defaultMenu.findItem(R.id.action_cart)
        val icon = menuItem.icon as LayerDrawable

        val badge = CountDrawable(context)

        PanierSingleton.weight = value
        badge.setCount(PanierSingleton.weight.toString())
        icon.mutate()
        icon.setDrawableByLayerId(R.id.ic_group_count, badge)

        defaultMenu.findItem(R.id.action_cart).isVisible = false
        defaultMenu.findItem(R.id.action_cart).isVisible = true
    }
}