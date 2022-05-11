package com.talkon.talkon.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(requireContext(), "sdds", Toast.LENGTH_SHORT).show()
    }

}