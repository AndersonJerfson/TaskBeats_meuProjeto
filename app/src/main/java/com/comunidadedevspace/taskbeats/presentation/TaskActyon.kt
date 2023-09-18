package com.comunidadedevspace.taskbeats.presentation

import com.comunidadedevspace.taskbeats.data.local.local.Task
import java.io.Serializable

enum class ActyonTipe{
    DELETE,
    UPDAP,
    CREAT
}

data class TaskActyon(val task: Task?, val actyontipe: ActyonTipe): Serializable