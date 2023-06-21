package com.comunidadedevspace.taskbeats.presentation

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isEmpty
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable


class MainActivity : AppCompatActivity() {

    private lateinit var ctn_estadovazio: LinearLayout
    val adapter: TaskListAdpter = TaskListAdpter(::onListItemClicked)

    private val viewModel  by lazy {  TaskListViewModel.creat(application)}

    val startForResult = registerForActivityResult(ActivityResultContracts
        .StartActivityForResult())
         { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
        //recuperando a ação
            val data = result.data
            val taskactyon = data?.getSerializableExtra(TASK_ACTYON_RESULT) as TaskActyon
          viewModel.execute(taskactyon)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.taskactivity_main)
        setSupportActionBar(findViewById(R.id.toobar))

        val recyclerview: RecyclerView = findViewById(R.id.rv_tasklist)
        recyclerview.adapter = adapter

        ctn_estadovazio = findViewById(R.id.ctn_estadovazio)

        val fab: View = findViewById<FloatingActionButton>(R.id.fab_add)
        fab.setOnClickListener{
            opemtaskdetail(null)
            showmessege(it,"btn")
        }
    }

    override fun onStart() {
        super.onStart()

        listFromDataBase()
    }
    private fun listFromDataBase() {

            val listObserver = Observer<List<Task>> {
                if(it.isEmpty()){
                    ctn_estadovazio.visibility = View.VISIBLE
                }else{
                    ctn_estadovazio.visibility = View.GONE
                }
                adapter.submitList(it)
            }
        viewModel.taskListViewModel.observe(this@MainActivity, listObserver)

        }

    private fun showmessege(view: View, mensagem:String){
        Snackbar.make(view, mensagem, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
    }

        fun onListItemClicked(task: Task){
        opemtaskdetail(task)
        }
    fun opemtaskdetail(task: Task?){
        val intent = taskdetail.start(this, task)
        startForResult.launch(intent)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_tasklistactivity, menu)

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_menu_deleteAll -> {
                viewModel.deleteAll()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }
}


enum class ActyonTipe{
     DELETE,
    DELETE_ALL,
     UPDAP,
     CREAT
    }

data class TaskActyon(val task: Task?, val actyontipe: ActyonTipe): Serializable

const val TASK_ACTYON_RESULT = "TASK_ACTYON_RESULT"