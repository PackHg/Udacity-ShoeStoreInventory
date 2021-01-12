package com.example.shoestoreinventory.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestoreinventory.R
import com.example.shoestoreinventory.SharedViewModel
import com.example.shoestoreinventory.databinding.FragmentLoginBinding
import com.example.shoestoreinventory.utils.Utils.hideKeyboard

class LoginFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login,
                container, false)
        binding.sharedViewModel = sharedViewModel
        binding.setLifecycleOwner(this)

        // Upon click on the "Log in" button, if the login data are correct then the onboarding
        // screens are skipped and navigate to the List screen.
        sharedViewModel.eventLogin.observe(viewLifecycleOwner, { eventlogin ->
            if (eventlogin) {
                val email = binding.emailAddressEdittext.text.toString()
                val password = binding.passwordEdittext.text.toString()
                if (sharedViewModel.isAKnownUser(email, password)) {
                    Toast.makeText(context, getString(R.string.thanks_for_sign_in), Toast.LENGTH_SHORT).show()
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToListFragment())
                } else {
                    Toast.makeText(context, getString(R.string.email_or_password_incorrect), Toast.LENGTH_LONG).show()
                }
                sharedViewModel.onLoginComplete()
                hideKeyboard()
            }
        })

        // Upon click on the "Sign Up" button, check the signup data and navigate to the "Welcome"
        // screen if the user is new. If the user is already known, navigate to the List screen.
        sharedViewModel.eventSignup.observe(viewLifecycleOwner, { eventSignup ->
            if (eventSignup) {
                val email = binding.emailAddressEdittext.text.toString()
                val password = binding.passwordEdittext.text.toString()
                if (sharedViewModel.isAKnownUser(email, password)) {
                    Toast.makeText(context, getString(R.string.already_signed_up), Toast.LENGTH_SHORT).show()
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToListFragment())
                } else {
                    sharedViewModel.addUser(email, password)
                    Toast.makeText(context, getString(R.string.thanks_for_sign_up), Toast.LENGTH_SHORT).show()
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
                }
                sharedViewModel.onSignupComplete()
                hideKeyboard()
            }
        })

        return binding.root
    }
}