package com.comunidadedevspace.taskbeats

import com.comunidadedevspace.taskbeats.data.Task
import com.comunidadedevspace.taskbeats.data.TaskDao
import com.comunidadedevspace.taskbeats.presentation.ActyonTipe
import com.comunidadedevspace.taskbeats.presentation.TaskActyon
import com.comunidadedevspace.taskbeats.presentation.TaskListViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class TaskListViewModelTest {

    private val taskdao: TaskDao = mock()
    private val task = Task(0, title = "titulo", descryption = "descrição")

    private val undertest: TaskListViewModel by lazy{
        TaskListViewModel(taskdao)
    }
    // Teste givem delete_all
    @Test
     fun deleteAll()= runTest{
        //givem
       val taskActionDeleteAll = TaskActyon(task = null, actyontipe = ActyonTipe.DELETE_ALL)

        //whem

        undertest.execute(taskActionDeleteAll)

        //them

        verify(taskdao).deleteAll()

    }

    // teste givem updap
    @Test
    fun updap()= runTest{

        //givem
        val taskactionUpdap = TaskActyon(task, actyontipe = ActyonTipe.UPDAP)

        //whem
        undertest.execute(taskactionUpdap)

        //them
        verify(taskdao).update(task)
    }

    // test givem delete
    @Test
    fun delete() = runTest {

        //givem
        val taskactyondelete = TaskActyon(task, actyontipe = ActyonTipe.DELETE)

        //whem

        undertest.execute(taskactyondelete)

        //tem

        verify(taskdao).deleteByid(0)
    }

        // creat
    @Test
    fun creat()= runTest {

        //Givem
        val taskactyoncreat = TaskActyon(task,ActyonTipe.CREAT)

            //whem
            undertest.execute(taskactyoncreat)

            //them

            verify(taskdao).insert(task)

        }
}