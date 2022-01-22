package com.example.nuntium.fragments.start

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.nuntium.R
import com.example.nuntium.databinding.FragmentSelectedScreenBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SelectedScreen : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentSelectedScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSelectedScreenBinding.inflate(inflater, container, false)
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*val list = mutableListOf<String>()
        var category1 = false
        var category2 = false
        var category3 = false
        var category4 = false
        var category5 = false
        var category6 = false
        var category7 = false
        var category8 = false
        var category9 = false
        var category10 = false
        binding.category1.setOnClickListener {
            if (category1 == false) {
                category1 = true
                binding.category1.backgroundTintList = ColorStateList.valueOf(R.color.status)
            } else {
                binding.category1.backgroundTintList = ColorStateList.valueOf(R.color.black)
                category1 = false
            }
        }*/

        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_selectedScreen_to_homepage)
        }
    }
}