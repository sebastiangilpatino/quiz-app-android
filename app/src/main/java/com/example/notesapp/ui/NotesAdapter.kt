package com.example.notesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.db.Note
import kotlinx.android.synthetic.main.note_layout.view.*

class NotesAdapter(private val notes: List<Note>) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.note_layout, parent, false)
        )
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NotesAdapter.NoteViewHolder, position: Int) {
        holder.view.text_view_title.text = notes[position].title
        holder.view.text_view_note.text = notes[position].note
        holder.view.text_view_note_correct1.isChecked = notes[position].noteIsTheCorrect
        holder.view.text_view_note2.text = notes[position].note2
        holder.view.text_view_note_correct2.isChecked = notes[position].note2IsTheCorrect
        holder.view.text_view_note3.text = notes[position].note3
        holder.view.text_view_note_correct3.isChecked = notes[position].note3IsTheCorrect
        holder.view.text_view_note4.text = notes[position].note4
        holder.view.text_view_note_correct4.isChecked = notes[position].note4IsTheCorrect

        holder.view.setOnClickListener {
            // Set the Navigation action
            val action = HomeFragmentDirections.actionAddNote()
            // add the selected Note
            action.note = notes[position]
            Navigation.findNavController(it).navigate(action)
        }
    }

    class NoteViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}