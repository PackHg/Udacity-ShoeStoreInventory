package com.example.shoestoreinventory.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.shoestoreinventory.R
import com.example.shoestoreinventory.SharedViewModel
import com.example.shoestoreinventory.databinding.FragmentDetailBinding
import com.example.shoestoreinventory.utils.Utils.hideKeyboard

class DetailFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater, R.layout.fragment_detail,
                container, false)
        binding.sharedViewModel = sharedViewModel
        binding.setLifecycleOwner(this)

        // Save the shoe data entered by the user and navigate back to the List screen upon click
        // on the "Save" button
        sharedViewModel.eventDetailSave.observe(viewLifecycleOwner, { eventDetailSaveShoe ->
            if (eventDetailSaveShoe) {
                val name = binding.detailShoeNameEditext.text.toString()
                val company = binding.detailCompanyEditext.text.toString()
                var size: Float
                try {
                    size = binding.detailShoeSizeEditext.text.toString().toFloat()
                } catch (e: NumberFormatException) {
                    size = 0.0f
                }
                val description = binding.detailDescriptionEditext.text.toString()
                sharedViewModel.addShoe(name, company, size, description)
                sharedViewModel.onDetailSaveComplete()
                findNavController().navigateUp()
                hideKeyboard()
            }
        })

        // Navigate back to the List screen upon click on the "Cancel" button
        sharedViewModel.eventDetailCancel.observe(viewLifecycleOwner, Observer { eventDetailCancel ->
            if (eventDetailCancel) {
                sharedViewModel.onDetailCancelComplete()
                findNavController().navigateUp()
                hideKeyboard()
            }
        })

        return binding.root
    }
}