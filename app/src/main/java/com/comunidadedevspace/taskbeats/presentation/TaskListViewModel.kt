package com.comunidadedevspace.taskbeats.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.comunidadedevspace.taskbeats.TaskListApplication
import com.comunidadedevspace.taskbeats.data.Task
import com.comunidadedevspace.taskbeats.data.TaskDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskListViewModel(private val taskDao: TaskDao): ViewModel() {

    val taskListViewModel: LiveData<List<Task>> = taskDao.getAll()

    fun execute(taskActyon: TaskActyon){

        when (taskActyon.actyontipe) {
            ActyonTipe.DELETE -> deleteByid(taskActyon.task!!.id)
            ActyonTipe.CREAT -> insertInfoDataBase(taskActyon.task!!)
            ActyonTipe.UPDAP -> updatetInfoDataBase(taskActyon.task!!)
            ActyonTipe.DELETE_ALL -> deleteAll()
        }
    }
    private fun insertInfoDataBase(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.insert(task)
        }


    }
    private fun updatetInfoDataBase(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.update(task)
        }
    }
     fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.deleteAll()

        }
    }
    private fun deleteByid(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.deleteByid(id)

        }
    }

    companion object{
        fun creat (application: Application): TaskListViewModel{
            val getappdatabase = (application as TaskListApplication).getAppDataBase()
            val dao = getappdatabase.taskdao()
            return TaskListViewModel(dao)

        }
    }

}