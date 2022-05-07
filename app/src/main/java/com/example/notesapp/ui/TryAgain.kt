package com.example.notesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.notesapp.R
import kotlinx.android.synthetic.main.fragment_question.*
import kotlinx.android.synthetic.main.fragment_try_again.*


class TryAgain : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_try_again, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var score = 0;
        arguments?.let {
            score = QuestionFragmentArgs.fromBundle(it).score
            lastScore.text = score.toString()
        }

        tryAgain.setOnClickListener {
            val action = TryAgainDirections.actionFromTryAgainToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}