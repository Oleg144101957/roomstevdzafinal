package com.example.roomstevdzafinal.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomstevdzafinal.R
import com.example.roomstevdzafinal.databinding.FragmentStartBinding
import com.example.roomstevdzafinal.viewmodel.NoteViewModel


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private lateinit var adapter: AdapterNote
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //Swipe fun

        val swipeGesture = object : SwipeGesture() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                super.onSwiped(viewHolder, direction)
                when (direction){
                    ItemTouchHelper.LEFT -> {
                        Toast.makeText(requireContext(), "Swipe Left", Toast.LENGTH_LONG).show()
                    }

                    ItemTouchHelper.RIGHT -> {
                        Toast.makeText(requireContext(), "Swipe Right", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipeGesture)



        val mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        adapter = AdapterNote()
        recycler = binding.recyclerView
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        touchHelper.attachToRecyclerView(recycler)

        mViewModel.myLiveNotes.observe(viewLifecycleOwner, { listNotes ->
            listNotes.let { adapter.setList(listNotes) }
        })

        binding.addNote.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_addNoteFragment)
        }



        return binding.root
    }

}