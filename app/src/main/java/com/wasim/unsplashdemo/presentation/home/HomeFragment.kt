package com.wasim.unsplashdemo.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wasim.unsplashdemo.R
import com.wasim.unsplashdemo.databinding.FragmentGalleryBinding
import com.wasim.unsplashdemo.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.buttonShape.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToShapeFragment())
        }
        binding.buttonUnsplash.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGalleryFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}