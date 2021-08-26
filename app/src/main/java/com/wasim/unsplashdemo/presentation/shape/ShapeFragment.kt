package com.wasim.unsplashdemo.presentation.shape

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wasim.unsplashdemo.R
import com.wasim.unsplashdemo.databinding.FragmentHomeBinding
import com.wasim.unsplashdemo.databinding.FragmentShapeBinding
import com.wasim.unsplashdemo.util.*

class ShapeFragment : Fragment(R.layout.fragment_shape) {

    private var _binding: FragmentShapeBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShapeBinding.bind(view)

        binding.apply { ivSquare.setOnClickListener { binding.paintView.takeAction(ACTION_ADD_SQUARE) } }

        binding.apply { ivCircle.setOnClickListener { binding.paintView.takeAction(ACTION_ADD_CIRCLE) } }

        binding.apply { ivUndo.setOnClickListener { binding.paintView.takeAction(ACTION_UNDO) } }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}