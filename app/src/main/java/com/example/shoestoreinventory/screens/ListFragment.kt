package com.example.shoestoreinventory.screens

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestoreinventory.R
import com.example.shoestoreinventory.SharedViewModel
import com.example.shoestoreinventory.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var binding: FragmentListBinding
    lateinit var linearLayout: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list,
                container, false)
        binding.sharedViewModel = sharedViewModel
        binding.setLifecycleOwner(this)

        // List the shoes on the screen
        sharedViewModel.shoes.observe(viewLifecycleOwner, { shoes ->
            linearLayout = binding.listContainer
            if (shoes != null) {
                for (shoe in shoes) {
                    val textView = TextView(context)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        textView.setTextAppearance(R.style.ShoeItemTextViewSyle)
                    } else {
                        textView.setTextAppearance(context, R.style.ShoeItemTextViewSyle)
                    }
                    textView.text = getString(R.string.shoe_data_format, shoe.name, shoe.company, shoe.size,
                        shoe.description)
                    linearLayout.addView(textView)
                }
            }
        })

        // Navigate to Detail upon click on "+" fab
        sharedViewModel.eventListPlus.observe(viewLifecycleOwner, { eventListPlus ->
            if (eventListPlus) {
                findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment())
                sharedViewModel.onListPLusComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.list_logout -> {
                findNavController().navigate(ListFragmentDirections.actionListFragmentToLoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}