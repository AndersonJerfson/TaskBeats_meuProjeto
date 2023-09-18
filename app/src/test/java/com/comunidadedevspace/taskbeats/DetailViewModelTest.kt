package com.comunidadedevspace.taskbeats

import com.comunidadedevspace.taskbeats.data.local.local.Task
import com.comunidadedevspace.taskbeats.data.local.local.TaskDao
import com.comunidadedevspace.taskbeats.presentation.ActyonTipe
import com.comunidadedevspace.taskbeats.presentation.DetailViewModel
import com.comunidadedevspace.taskbeats.presentation.TaskActyon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class DetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val taskdao: TaskDao = mock()
    private val task = Task(0, title = "titulo", descryption = "descrição")

    private val undertest: DetailViewModel by lazy{
        DetailViewModel(taskdao)
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
        val taskactyoncreat = TaskActyon(task, ActyonTipe.CREAT)

        //whem
        undertest.execute(taskactyoncreat)

        //them

        verify(taskdao).insert(task)

    }
}