package fr.isen.henry.erestaurant

import android.content.Context
import android.graphics.drawable.LayerDrawable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


abstract class MyAppActivity : AppCompatActivity() {
    private lateinit var defaultMenu : Menu


    override fun onPrepareOptionsMenu(menu: Menu): Boolean{
        if(this::defaultMenu.isInitialized) {
            setBadgeCount(this, CountSingleton.value)
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
            Toast.makeText(this,"Action", Toast.LENGTH_LONG).show()
        return true
    }

    override fun onResume(){
        super.onResume()
        if(this::defaultMenu.isInitialized){
        setBadgeCount(this,CountSingleton.value)
        }
    }

    protected fun setBadgeCount(context:Context, value :Int) {
        val menuItem: MenuItem = defaultMenu.findItem(R.id.action_cart)
        val icon = menuItem.icon as LayerDrawable

        val badge = CountDrawable(context)

        CountSingleton.value = value
        badge.setCount(CountSingleton.value.toString())
        icon.mutate()
        icon.setDrawableByLayerId(R.id.ic_group_count, badge)

        defaultMenu.findItem(R.id.action_cart).isVisible = false
        defaultMenu.findItem(R.id.action_cart).isVisible = true
    }
}