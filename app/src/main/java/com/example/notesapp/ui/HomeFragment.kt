package com.example.notesapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.example.notesapp.R
import com.example.notesapp.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.coroutines.launch
import androidx.core.view.isVisible as isVisible1

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // adapter content cannot change the size of the RecyclerView itself, it's fixed - Optimize the performance
        recycler_view_notes.setHasFixedSize(true)
        recycler_view_notes.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        // Retrieve all notes from database to RecyclerView using Coroutines
        launch {
            /* here context is the getContext() from the Fragment, if the context
            * is not null, let's execute the block of code using let scope function
            * with the argument it's context object - Inline functions
            * similar like  if(context!=null){}*/
            context?.let {
                val notes = NoteDatabase(it).getNoteDao().getAllNotes()
                println(notes)
                recycler_view_notes.adapter = NotesAdapter(notes)
            }
        }
        button_add.setOnClickListener {
            // After Rebuild you will get HomeFragmentDirections automatically,
            // call the navigation action id given in the Navigation graph
            val action = HomeFragmentDirections.actionAddNote()
            // Navigate to the action by passing view and call navigate by passing action
            Navigation.findNavController(it).navigate(action)
        }

        toLoginButton.setOnClickListener {
            val action = HomeFragmentDirections.actionReturnLogin()
            Navigation.findNavController(it).navigate(action)
        }


    }
}
