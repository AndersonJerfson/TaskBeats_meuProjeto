package com.comunidadedevspace.taskbeats

import com.comunidadedevspace.taskbeats.data.local.local.Task
import com.comunidadedevspace.taskbeats.data.local.local.TaskDao
import com.comunidadedevspace.taskbeats.presentation.ActyonTipe
import com.comunidadedevspace.taskbeats.presentation.DetailViewModel
import com.comunidadedevspace.taskbeats.presentation.TaskActyon
import com.comunidadedevspace.taskbeats.presentation.TaskListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
@OptIn(ExperimentalCoroutinesApi::class)
class TaskListViewModelTest {

    private val taskdao: TaskDao = mock()
    private val task = Task(0, title = "titulo", descryption = "descrição")

    private val undertest: TaskListViewModel by lazy{
        TaskListViewModel(taskdao)
    }


}