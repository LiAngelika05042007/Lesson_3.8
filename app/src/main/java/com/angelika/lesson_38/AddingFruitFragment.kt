package com.angelika.lesson_38

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.angelika.lesson_38.databinding.FragmentAddingFruitBinding

class AddingFruitFragment : Fragment() {

    private var _binding: FragmentAddingFruitBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddingFruitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAddClick()
        onBackPressed()
    }

    private fun btnAddClick() = with(binding) {
        btnAdd.setOnClickListener {
            val url = etLinkToPhoto.text.toString().trim()
            val name = etName.text.toString().trim()
            val fruit = Fruit(name, url)
            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                FruitFragment.FRUIT_DATA_KEY,
                fruit
            )
            findNavController().navigateUp()
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                FruitFragment.FRUIT_DATA_KEY,
                null
            )
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}