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
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.comunidadedevspace.taskbeats.data.AppDataBase
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.Serializable


class MainActivity : AppCompatActivity() {

    private lateinit var ctn_estadovazio: LinearLayout
    val adapter: TaskListAdpter = TaskListAdpter(::onListItemClicked)

   private val database by lazy {
       Room.databaseBuilder(
       applicationContext,
       AppDataBase::class.java, "taskbeats-database"
   ).build()  }
    private val dao by lazy { database.taskdao() }

    val startForResult = registerForActivityResult(ActivityResultContracts
        .StartActivityForResult())
         { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
        //recuperando a ação
            val data = result.data
            val taskactyon = data?.getSerializableExtra(TASK_ACTYON_RESULT) as TaskActyon
            val task: Task = taskactyon.task
            when (taskactyon.actyontipe) {
                ActyonTipe.DELETE -> deleteByid(task.id)
                ActyonTipe.CREAT -> insertInfoDataBase(task)
                ActyonTipe.UPDAP -> updatetInfoDataBase(task)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toobar))

           listFromDataBase()


        val recyclerview: RecyclerView = findViewById(R.id.rv_tasklist)
        recyclerview.adapter = adapter

        ctn_estadovazio = findViewById(R.id.ctn_estadovazio)

        val fab: View = findViewById<FloatingActionButton>(R.id.fab_add)
        fab.setOnClickListener{
            opemtaskdetail(null)
            showmessege(it,"btn")
        }
    }
    fun listFromDataBase(){
        CoroutineScope(IO).launch{val  mydabase: List<Task> = dao.getAll()
        adapter.submitList(mydabase)}
    }
    private fun insertInfoDataBase(task: Task){
        CoroutineScope(IO).launch{ dao.insert(task)
        listFromDataBase()}
    }
    private fun updatetInfoDataBase(task: Task){
        CoroutineScope(IO).launch{ dao.update(task)
        listFromDataBase()}
    }
    private fun deleteAll(){
        CoroutineScope(IO).launch{
            dao.deleteAll()
            listFromDataBase()
        }
    }
    private fun deleteByid(id: Int){
        CoroutineScope(IO).launch{
            dao.deleteByid(id)
            listFromDataBase()
        }
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
                deleteAll()


                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }
}

    enum class ActyonTipe{
     DELETE,
     UPDAP,
     CREAT
    }

data class TaskActyon(val task: Task, val actyontipe: ActyonTipe): Serializable

const val TASK_ACTYON_RESULT = "TASK_ACTYON_RESULT"