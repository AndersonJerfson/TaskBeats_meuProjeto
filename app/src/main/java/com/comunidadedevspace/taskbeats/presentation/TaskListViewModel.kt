package com.comunidadedevspace.taskbeats.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.comunidadedevspace.taskbeats.data.local.local.TaskListApplication
import com.comunidadedevspace.taskbeats.data.local.local.Task
import com.comunidadedevspace.taskbeats.data.local.local.TaskDao

class TaskListViewModel(val taskDao: TaskDao): ViewModel() {

    val taskListViewModel: LiveData<List<Task>> = taskDao.getAll()

    companion object{
        fun creat (application: Application): TaskListViewModel{
            val getappdatabase = (application as TaskListApplication).getAppDataBase()
            val dao = getappdatabase.taskdao()
            return TaskListViewModel(dao)

        }
    }

}