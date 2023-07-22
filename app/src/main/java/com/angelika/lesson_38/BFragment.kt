package com.angelika.lesson_38

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.angelika.lesson_38.databinding.FragmentBBinding
import com.bumptech.glide.Glide

class BFragment : Fragment() {

    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<BFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() {
        Glide.with(binding.image.context)
            .load(args.model.image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.image)
        binding.text.text = args.model.name
        onBackPressed()
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
}