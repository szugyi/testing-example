package io.supercharge.testingexample.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.supercharge.testingexample.R
import io.supercharge.testingexample.domain.model.Note
import kotlinx.android.synthetic.main.note_list_item.view.*

class NoteAdapter : RecyclerView.Adapter<ViewHolder>() {

    private lateinit var items : List<Note>

    fun setItems(itemList: List<Note>) {
        items = itemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.note_list_item, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

}

class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
    fun bind(note : Note) {
        itemView.noteTitle.text = note.title
        itemView.noteAmount.text = note.amount.toString().plus(" Ft")
    }
}