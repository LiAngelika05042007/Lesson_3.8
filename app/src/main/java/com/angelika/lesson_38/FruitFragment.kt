package com.angelika.lesson_38

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.angelika.lesson_38.databinding.FragmentFruitBinding

class FruitFragment : Fragment() {

    private var _binding: FragmentFruitBinding? = null
    private val binding get() = _binding!!
    private val fruitAdapter = FruitAdapter(this::onItemClick)

    private fun onItemClick(fruit: Fruit) {
        findNavController().navigate(FruitFragmentDirections.actionFruitFragmentToBFragment2(fruit))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFruitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycle()
        click()
        getData()
    }

    private fun initRecycle() {
        binding.rvFruit.adapter = fruitAdapter
    }

    private fun click() {
        binding.btnAddFruit.setOnClickListener {
            findNavController().navigate(R.id.action_fruitFragment_to_addingFruitFragment)
        }
    }

    private fun getData() {
        findNavController().currentBackStackEntry?.savedStateHandle?.get<Fruit>(FRUIT_DATA_KEY)
            ?.let { imageUrl ->
                fruitAdapter.addImage(imageUrl)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRUIT_DATA_KEY = "fruit_data"
    }
}