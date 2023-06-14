package com.comunidadedevspace.taskbeats.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.Task

//funçao adapter.
class TaskListAdpter(
    private val opemtaskdetail:(task: Task)-> Unit
) :ListAdapter<Task, TaskListviewholder>(TaskListAdpter) {



    //cria uma view para receber os dados.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TaskListviewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)

        return TaskListviewholder(view)
    }

//faz o bind liga os dados a view.
    override fun onBindViewHolder(holder: TaskListviewholder, position: Int) {
        val item: Task = getItem(position)
        holder.bind(item,opemtaskdetail)
    }
    companion object: DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
         return  oldItem.title == newItem.title &&
                 oldItem.descryption == newItem.descryption
        }

    }
}
//viewholder segura os items da view para serem inseridos os dados
class TaskListviewholder(private  val view:View) : RecyclerView.ViewHolder(view) {
    val tvtitle = view.findViewById<TextView>(R.id.tv_itemtask_title)
    val  tvdescription = view.findViewById<TextView>(R.id.tv_itemtask_description)

    fun bind(task: Task, opemtaskdetail:(task: Task)-> Unit){
        view.setOnClickListener(){
            opemtaskdetail.invoke(task)
        }
        tvtitle.text = task.title
        tvdescription.text = task.descryption
    }
}