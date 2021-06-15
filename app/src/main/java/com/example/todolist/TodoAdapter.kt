package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

// Acts as a bridge between view and data
class TodoAdapter(
    private val allTodoItems: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    // Allows recyclerview to have performance scroll visibility on elements
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        // Takes reference to repeating view and inflates to list
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = allTodoItems[position]
        holder.itemView.apply {
            tvTodoTitle.text = currentTodo.title
            cbDone.isChecked = currentTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, currentTodo.isChecked)
            // When checkbox is clicked:
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }

        }
    }

    override fun getItemCount(): Int {
        return allTodoItems.size
    }

    fun addTodo(todo: Todo) {
        allTodoItems.add(todo)
        notifyItemInserted(allTodoItems.size - 1)
    }

    fun deleteTodoItem() {
        allTodoItems.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}