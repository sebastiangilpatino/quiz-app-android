package com.example.notesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.R
import com.example.notesapp.db.Note
import com.example.notesapp.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_question.*
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
//        view.adminButton.setOnClickListener {
//            val action = LoginFragmentDirections.actionGoToLogin()
//            Navigation.findNavController(view).navigate(action)
//        }
//        view.userButton.setOnClickListener {
//            val action = LoginFragmentDirections.actionQuestionFragment()
//            Navigation.findNavController(view).navigate(action)
//        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var notes: Note? = null
        launch {
            context?.let {
                notes = NoteDatabase(it).getNoteDao().getOneNote()
            }
        }
        adminButton.setOnClickListener {
            val action = LoginFragmentDirections.actionGoToLogin()
            Navigation.findNavController(it).navigate(action)
        }

        userButton.setOnClickListener {
            if (notes != null) {
                val action = LoginFragmentDirections.actionQuestionFragment(0, 0, 0)
                Navigation.findNavController(it).navigate(action)
            } else {
                context?.toast("No Questions on DB")
            }
        }

    }


}