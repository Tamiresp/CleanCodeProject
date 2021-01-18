package com.example.cleancodeproject.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.cleancodeproject.R
import com.example.cleancodeproject.framework.NoteViewModel
import com.example.core.data.Note
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment : Fragment() {
    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        check_btn.setOnClickListener {
            if (title_view.text.toString() != "" || content_view.text.toString() != "") {
                val time = System.currentTimeMillis()
                currentNote.title = title_view.text.toString()
                currentNote.content = content_view.text.toString()
                currentNote.updateTime = time
                if (currentNote.id == 0L) {
                    currentNote.creationTime = time
                }
                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.saved.observe(this, Observer {
            if (it) {
                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                hideKeyBoard()
                Navigation.findNavController(title_view).popBackStack()
            } else {
                Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun hideKeyBoard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as
                InputMethodManager
        imm.hideSoftInputFromWindow(title_view.windowToken, 0)
    }
}