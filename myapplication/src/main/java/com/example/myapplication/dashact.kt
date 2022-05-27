package com.example.myapplication


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class dashact : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var coordinator: CoordinatorLayout
    lateinit var navigationdrawer: NavigationView
    lateinit var drawerLayout: DrawerLayout
var nameofuser :String?="jdghjg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.dashact)

            toolbar = findViewById(R.id.toolbar)
            coordinator = findViewById(R.id.coordinator)
            navigationdrawer = findViewById(R.id.navigationview)
            drawerLayout = findViewById(R.id.drawerlayout)

            actionbar()

            var toggle = ActionBarDrawerToggle(
                this@dashact,
                drawerLayout,
                R.string.open,
                R.string.close
            )
            toggle.syncState()
            drawerLayout.addDrawerListener(toggle)



        navigtiontitle("home")
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, frag1())
            .commit()
        drawerLayout.closeDrawers()


            navigationdrawer.setNavigationItemSelectedListener {


                if (it.isChecked==true){
                    it.isCheckable=true
                }else{

                    it.isCheckable=false
                }



                when (it.itemId) {
                    R.id.home -> {

                        navigtiontitle("home")
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame, frag1())
                            .commit()
                        drawerLayout.closeDrawers()


                    }
                    R.id.fav ->{
                        navigtiontitle("Favourites")
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame,Viewpager2())
                            .commit()
                        drawerLayout.closeDrawers()

                    }
                    R.id.privacy ->{
                        navigtiontitle("Souce")

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame,Source())
                            .commit()
                        drawerLayout.closeDrawers()

                    }
                    R.id.details ->{
                        navigtiontitle("My Details")

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame,MyFrag())
                            .commit()
                        drawerLayout.closeDrawers()

                    }

                }


                return@setNavigationItemSelectedListener true


            }


    }

        fun navigtiontitle(title: String) {

            supportActionBar?.title = title
        }



        fun actionbar() {
            super.setSupportActionBar(toolbar)
            supportActionBar?.title = "Bookhub"
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when (item.itemId) {


                android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
            }
            return super.onOptionsItemSelected(item)

        }


    }


