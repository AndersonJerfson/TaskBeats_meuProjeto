package com.comunidadedevspace.taskbeats.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.comunidadedevspace.taskbeats.data.local.local.Task
import com.comunidadedevspace.taskbeats.data.local.local.TaskDao
import com.comunidadedevspace.taskbeats.data.local.local.TaskListApplication
import kotlinx.coroutines.launch

class DetailViewModel(
    private val taskDao: TaskDao
): ViewModel() {

    fun execute(taskActyon: TaskActyon){

        when (taskActyon.actyontipe) {
            ActyonTipe.DELETE -> deleteByid(taskActyon.task!!.id)
            ActyonTipe.CREAT -> insertInfoDataBase(taskActyon.task!!)
            ActyonTipe.UPDAP -> updatetInfoDataBase(taskActyon.task!!)
        }
    }
    private fun insertInfoDataBase(task: Task){
        viewModelScope.launch {
            taskDao.insert(task)
        }


    }
    private fun updatetInfoDataBase(task: Task){
        viewModelScope.launch {
            taskDao.update(task)
        }
    }

    private fun deleteByid(id: Int){
        viewModelScope.launch {
            taskDao.deleteByid(id)

        }
    }

    companion object{
        fun getVMfactory(application: Application): ViewModelProvider.Factory {
            val databaseinstance = (application as TaskListApplication).getAppDataBase()
            val dao = databaseinstance.taskdao()

            val factory = object: ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T{
                    return DetailViewModel(dao) as T
                }
            }
            return factory
        }
    }
}