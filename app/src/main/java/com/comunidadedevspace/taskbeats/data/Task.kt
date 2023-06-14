package com.comunidadedevspace.taskbeats.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
 class
Task(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
     val title: String,
     val descryption: String): Serializable
