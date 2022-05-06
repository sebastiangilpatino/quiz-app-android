package com.example.notesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.notesapp.R
import com.example.notesapp.db.Note
import com.example.notesapp.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_question.*
import kotlinx.android.synthetic.main.fragment_question.view.*
import kotlinx.coroutines.launch

class QuestionFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Retrieve all notes from database to RecyclerView using Coroutines
        launch {
            /* here context is the getContext() from the Fragment, if the context
            * is not null, let's execute the block of code using let scope function
            * with the argument it's context object - Inline functions
            * similar like  if(context!=null){}*/
            context?.let {
                val notes: Note = NoteDatabase(it).getNoteDao().getOneNote()
                println(notes)
                question.text = notes.title
                answer1.text = notes.note
                answer2.text = notes.note2
                answer3.text = notes.note3
                answer4.text = notes.note4
                isCorrect1.isChecked = notes.noteIsTheCorrect
                isCorrect2.isChecked = notes.note2IsTheCorrect
                isCorrect3.isChecked = notes.note3IsTheCorrect
                isCorrect4.isChecked = notes.note4IsTheCorrect
            }
        }
        homeButton.setOnClickListener {
            val action = QuestionFragmentDirections.actionGoToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}