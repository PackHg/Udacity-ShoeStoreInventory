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
import com.example.shoestoreinventory.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragmentWelcomeBinding>(inflater, R.layout.fragment_welcome,
                container, false)
        binding.sharedViewModel = sharedViewModel
        binding.setLifecycleOwner(this)

        // Navigate to the "Instruction" screen upon click on the "Next" button
        sharedViewModel.eventWelcomeNext.observe(viewLifecycleOwner, { eventWelcomeNext ->
            if (eventWelcomeNext) {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment())
                sharedViewModel.onWelcomeNextComplete()
            }
        })

        return binding.root
    }
}