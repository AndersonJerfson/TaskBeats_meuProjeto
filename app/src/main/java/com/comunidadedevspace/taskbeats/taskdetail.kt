package com.comunidadedevspace.taskbeats

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class taskdetail : AppCompatActivity() {
     var task: Task ?= null
   lateinit var btn_comc: Button
    companion object {
        private const val task_detail_extra = "task.detail.title.extra"

        fun start(context: Context, task: Task?): Intent{
            val intent = Intent(context, taskdetail::class.java)
            .apply {
                putExtra(task_detail_extra, task)
            }

       return intent
    }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taskdetail)
        setSupportActionBar(findViewById(R.id.toobar))
        //tv_title = findViewById<TextView>(R.id.tv_title_taskdetail)
        // tv_title.text = task?.title ?:"Adicionar uma tarefa"
         task = intent?.getSerializableExtra(task_detail_extra) as Task?
       val edt_title = findViewById<EditText>(R.id.edt_title)
        val edt_description = findViewById<EditText>(R.id.edt_description)
        btn_comc = findViewById<Button>(R.id.btn_comc)
        if(task != null){
            edt_title.setText(task!!.title)
            edt_description.setText(task!!.descryption)
        }
            btn_comc.setOnClickListener{
            val title  = edt_title.text.toString()
            val description = edt_description.text.toString()
            if(title.isNotEmpty() && description.isNotEmpty()){
                if(task == null) {
                    addOrUpdateTask(0,title, description, ActyonTipe.CREAT)
                }else{
                    addOrUpdateTask(task!!.id,title,description,ActyonTipe.UPDAP)

                }
            }else{
                showmessege(it,"digite seu titulo e descrição")
            }

        }

    }
    fun addOrUpdateTask(id:Int, title:String, description:String, actyontipe: ActyonTipe){
        val newtask = Task(id,title,description)
        returnAction(newtask, actyontipe)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_tasdetail, menu)

      return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_menu_delete -> {
               //retornar opção selecionada
                if(task != null){
                 returnAction(task,ActyonTipe.DELETE)
                } else{
                    showmessege(btn_comc,"Não ha tarefefa para deletar")
                 }


                true
            }
            
            else -> super.onOptionsItemSelected(item)
        }

    }
    fun returnAction(task: Task?, actyonTipe: ActyonTipe){
        val intent = Intent()
            .apply {
                val taskActyon = TaskActyon(task!!,actyonTipe)
                putExtra(TASK_ACTYON_RESULT,taskActyon)
            }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
    private fun showmessege(view: View, mensagem:String){
        Snackbar.make(view, mensagem, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
    }


}