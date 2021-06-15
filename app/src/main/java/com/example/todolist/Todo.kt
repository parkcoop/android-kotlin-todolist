package com.example.todolist

// Data class to populate list of views
data class Todo(
    val title: String,
    var isChecked: Boolean = false
)