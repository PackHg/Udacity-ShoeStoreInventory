package com.example.shoestoreinventory.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestoreinventory.R
import com.example.shoestoreinventory.SharedViewModel
import com.example.shoestoreinventory.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragmentInstructionBinding>(inflater, R.layout.fragment_instruction,
            container, false)
        binding.sharedViewModel = sharedViewModel
        binding.setLifecycleOwner(this)

        // Navigate to List upon click on "Next" button
        sharedViewModel.eventInstructionNext.observe(viewLifecycleOwner, { eventInstructionNext ->
            if (eventInstructionNext) {
                findNavController().navigate(InstructionFragmentDirections.actionInstructionFragmentToListFragment())
                sharedViewModel.onInstructionNextComplete()
            }
        })

        return binding.root
    }
}