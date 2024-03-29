package com.food.recipe.cooking.video

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.food.recipe.cooking.video.ui.widget.ImageHolderView
import com.freegeek.android.materialbanner.MaterialBanner
import com.freegeek.android.materialbanner.holder.ViewHolderCreator
import com.transitionseverywhere.Fade
import com.transitionseverywhere.TransitionManager
import com.transitionseverywhere.TransitionSet
import com.transitionseverywhere.extra.Scale
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.view_search_bar.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
        DrawerLayout.DrawerListener, AppBarLayout.OnOffsetChangedListener {

    var transtionSet: TransitionSet? = null
    private val imagesBanner = intArrayOf(R.drawable.banner, R.drawable.banner, R.drawable.banner, R.drawable.banner, R.drawable.banner)
    private val dataBanners: MutableList<DataBanner> = ArrayList()

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        drawer_layout.addDrawerListener(this)
        nav_view.setNavigationItemSelectedListener(this)
        appbar.addOnOffsetChangedListener(this)
        transtionSet = TransitionSet()

        for (image in imagesBanner) {
            val dataBanner = DataBanner()
            dataBanner.url = image
            dataBanners.add(dataBanner)
        }
        val banner = findViewById<View>(R.id.bannerUrl) as MaterialBanner<DataBanner>

        banner.addPages(ViewHolderCreator { return@ViewHolderCreator ImageHolderView() }, dataBanners)
        bannerUrl.setIndicatorInside(true)
        bannerUrl.startTurning(3000)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onDrawerStateChanged(newState: Int) {

    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        containerMain?.x = slideOffset * drawerView.width
    }

    override fun onDrawerClosed(drawerView: View) {
    }

    override fun onDrawerOpened(drawerView: View) {
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        transtionSet?.addTransition(Scale(0.7f))
                ?.addTransition(Fade())?.interpolator = if (verticalOffset == 0)
            LinearOutSlowInInterpolator()
        else
            FastOutLinearInInterpolator()

        TransitionManager.beginDelayedTransition(search_container, transtionSet)
        search_container.visibility = if (verticalOffset == 0) View.VISIBLE else View.GONE
    }
}
