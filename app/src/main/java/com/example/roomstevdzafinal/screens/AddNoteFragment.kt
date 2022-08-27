package com.example.roomstevdzafinal.screens

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomstevdzafinal.R
import com.example.roomstevdzafinal.databinding.FragmentAddNoteBinding
import com.example.roomstevdzafinal.models.Note
import com.example.roomstevdzafinal.viewmodel.NoteViewModel


class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)

        binding.save.setOnClickListener {
            val title = binding.noteTitle.text.toString()
            val description = binding.description.text.toString()

            if (checkFields(title, description)){
                val noteToAdd = Note(title = title, description = description)
                mViewModel.addNote(noteToAdd)
                Toast.makeText(requireContext(), "Added", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_addNoteFragment_to_startFragment)

            } else {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    private fun checkFields(title: String, description: String) : Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }

}