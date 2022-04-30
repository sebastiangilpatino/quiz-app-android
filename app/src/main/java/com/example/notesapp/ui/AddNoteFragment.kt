package com.example.notesapp.ui
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.notesapp.R
import com.example.notesapp.db.Note
import com.example.notesapp.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class AddNoteFragment :BaseFragment() {
    private var note: Note? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Receive the note arguments
        arguments?.let {
            // get the note value from the HomeFragment using Bundle instance
            note = AddNoteFragmentArgs.fromBundle(it).note
            title.setText(note?.title)
            editNote.setText(note?.note)
            editNote2.setText(note?.note2)
            editNote3.setText(note?.note3)
            editNote4.setText(note?.note4)
        }
        // deletion logic
        button_delete.setOnClickListener {
            if (note != null) deleteNote() else context?.toast("Cannot Delete")
        }
        // Set the listener for the FAB
        button_save.setOnClickListener {view ->
            // Retrieve the values from the EditText fields
            val noteTitle = title.text.toString()
            val noteBody = editNote.text.toString()
            val noteBody2 = editNote2.text.toString()
            val noteBody3 = editNote3.text.toString()
            val noteBody4 = editNote4.text.toString()
            // Check the input values are empty, then set the error message and give the focus
            if(noteTitle.isEmpty()){
                title.error = "Title Required"
                title.requestFocus()
                return@setOnClickListener // stop further execution ie returning at the end of the setOnClickListener
            }

            if(noteBody.isEmpty()){
                editNote.error = "Body Required"
                editNote.requestFocus()
               return@setOnClickListener // stop further execution ie returning at the end of the setOnClickListener
            }
            launch {
                context?.let {
                    val mNote = Note(noteTitle, noteBody , noteBody2 , noteBody3 , noteBody4)
                    // note == null means Inserting a new Note
                    if (note == null) {
                        NoteDatabase(it).getNoteDao().addNote(mNote)
                        it.toast("Note Saved")
                    } else {
                        // Update the note
                        mNote.id = note!!.id
                        NoteDatabase(it).getNoteDao().updateNote(mNote)
                        it.toast("Note Updated")
                    }
                // after adding a note need to return to Home_Fragment as per the navigation directions
                    val action = AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view).navigate(action)

                }
            }

        }
        }

    private fun deleteNote() {
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("You cannot undo this operation")
            setPositiveButton("Yes") {dialog, which ->
                launch {
                    NoteDatabase(context).getNoteDao().deleteNote(note!!)
                    val action = AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view!!).navigate(action)
                }
            }
            setNegativeButton("No") {dialog, which->

            }
        }.create().show()
    }
}


