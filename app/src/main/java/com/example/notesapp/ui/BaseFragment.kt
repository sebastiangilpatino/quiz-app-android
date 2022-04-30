package com.example.notesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : Fragment(),CoroutineScope{
    // Instance in the Co-routine Context.
    private lateinit var job: Job

    /*  public val coroutineContext: CoroutineContext declared in the CoroutineScope interface
        Need to override in the subclass to initialize the value */
    override val coroutineContext: CoroutineContext
        // To perform the Job, Dispatchers.Main is used for CoroutineContext to perform UI operations
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create an Instance for the Job()
        job = Job()
    }
    // Cancel the Job in onDestroy()
    override fun onDestroy() {
        super.onDestroy()
        // Cancel the Job
        job.cancel()
    }
}