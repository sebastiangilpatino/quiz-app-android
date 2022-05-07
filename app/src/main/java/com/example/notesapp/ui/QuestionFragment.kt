package com.example.notesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.notesapp.R
import com.example.notesapp.db.Note
import com.example.notesapp.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_question.*
import kotlinx.coroutines.launch

class QuestionFragment : BaseFragment() {
    private lateinit var noteList: List<Note>
    private var notes: Note? = null
    var index: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var score: Int = 0
        arguments?.let {
            // get the note value from the HomeFragment using Bundle instance
            index = QuestionFragmentArgs.fromBundle(it).index
            score = QuestionFragmentArgs.fromBundle(it).score
        }
        launch {
            /* here context is the getContext() from the Fragment, if the context
            * is not null, let's execute the block of code using let scope function
            * with the argument it's context object - Inline functions
            * similar like  if(context!=null){}*/
            context?.let {
                noteList = NoteDatabase(it).getNoteDao().getAllNotes()
                if (index > noteList.size - 1) {
                    val action =
                        QuestionFragmentDirections.actionFromQuestionToTryAgain(index, score)
                    Navigation.findNavController(view!!).navigate(action)
                    index = 0
                }
                notes = noteList[index]
                println(notes)
                question.text = notes!!.title
                answer1.text = notes!!.note
                answer2.text = notes!!.note2
                answer3.text = notes!!.note3
                answer4.text = notes!!.note4
            }
        }

        homeButton.setOnClickListener {
            val action = QuestionFragmentDirections.actionGoToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }

        nextButton.setOnClickListener { view ->
            if (isCorrect1.isChecked == notes?.noteIsTheCorrect &&
                isCorrect2.isChecked == notes?.note2IsTheCorrect &&
                isCorrect3.isChecked == notes?.note3IsTheCorrect &&
                isCorrect4.isChecked == notes?.note4IsTheCorrect
            ) {
                score++;
            }
            //save db w/ new score
            launch {
                context?.let {
                    index++
                    val mNote = Note(
                        notes!!.title, notes!!.note,
                        notes?.noteIsTheCorrect!!, notes!!.note2, notes?.note2IsTheCorrect!!,
                        notes!!.note3, notes?.note3IsTheCorrect!!, notes!!.note4,
                        notes?.note4IsTheCorrect!!
                    )
                    mNote.id = notes!!.id
                    NoteDatabase(it).getNoteDao().updateNote(mNote)
                    val action = QuestionFragmentDirections.actionQuestionFragmentSelf(index, score)
                    Navigation.findNavController(view).navigate(action)
                }
            }
        }
    }
}