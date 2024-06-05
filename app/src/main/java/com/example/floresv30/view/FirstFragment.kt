package com.example.floresv30.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.floresv30.R
import com.example.floresv30.databinding.FragmentFirstBinding
import com.example.floresv30.view.adapter.AdapterFlower
import com.example.floresv30.viewmodel.FlowerViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: FlowerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AdapterFlower()

        binding.RvFlower.adapter = adapter
        binding.RvFlower.layoutManager = GridLayoutManager(context, 2)

        viewModel.getFlowersList().observe(viewLifecycleOwner) {
            it.let {
                Log.d("listado", it.toString())
                adapter.update(it)
            }
        }
        adapter.selectedFlower().observe(viewLifecycleOwner) {
            it?.let {
                // válidar si capta la seleccion
                Log.d("FlowerChoose", it.id.toString())
                viewModel.getFlowersDetailsByIdFromInternet(it.id)

            }
            val bundle = Bundle().apply {
                putInt("FlowerId", it.id)
                putString("FlowerName", it.nombre)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }

    }
}