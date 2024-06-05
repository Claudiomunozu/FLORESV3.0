package com.example.floresv30.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.floresv30.R
import com.example.floresv30.databinding.FlowerlistBinding
import com.example.floresv30.model.local.entities.FlowerLocal

class AdapterFlower : RecyclerView.Adapter<AdapterFlower.viewholder>() {

    private var listFlowers = listOf<FlowerLocal>()
    private val selectedFlower = MutableLiveData<FlowerLocal>()
    fun selectedFlower(): LiveData<FlowerLocal> = selectedFlower

    inner class viewholder(private val binding: FlowerlistBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(list: FlowerLocal) {

            Glide.with(binding.imageRv).load(list.imagen).centerCrop().into(binding.imageRv)
            binding.textNameRV.text = list.nombre
            binding.textTipoRV.text = list.tipo
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View) {

            selectedFlower.value = listFlowers[bindingAdapterPosition]
            Log.d("ONCLICK", bindingAdapterPosition.toString())
        }

    }

    fun update(list: List<FlowerLocal>) {
        listFlowers = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(FlowerlistBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = listFlowers.size

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bind(listFlowers[position])
    }

}