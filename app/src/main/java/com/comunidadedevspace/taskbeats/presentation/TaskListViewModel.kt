package com.comunidadedevspace.taskbeats.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comunidadedevspace.taskbeats.data.TaskListApplication
import com.comunidadedevspace.taskbeats.data.Task
import com.comunidadedevspace.taskbeats.data.TaskDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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