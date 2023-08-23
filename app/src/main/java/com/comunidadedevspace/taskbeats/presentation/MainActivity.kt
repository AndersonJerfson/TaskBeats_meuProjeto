package com.comunidadedevspace.taskbeats.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.comunidadedevspace.taskbeats.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val taskFragment = TaskListFragment.newInstance()
        val newfragment = NewListFragment.newInstance()
        val bottonNavView = findViewById<BottomNavigationView>(R.id.botton_nav_view)
        val floatingAction = findViewById<FloatingActionButton>(R.id.floating_action_button)

        floatingAction.setOnClickListener{
            opemtaskdetail()
        }
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view,taskFragment)
            setReorderingAllowed(true)
        }

        bottonNavView.setOnItemSelectedListener{menuItem ->
            val fragment = when(menuItem.itemId){
                R.id.item_menu_tasks -> taskFragment
                R.id.item_menu_news -> newfragment
                else -> taskFragment
            }
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view,fragment)
                 setReorderingAllowed(true)
            }

            true
        }

    }
    fun opemtaskdetail(){
        val intent = taskdetail.start(this, null)
        startActivity(intent)
    }
}