package com.example.floresv30.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.floresv30.R
import com.example.floresv30.databinding.FragmentSecondBinding
import com.example.floresv30.viewmodel.FlowerViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: FlowerViewModel by activityViewModels()
    private var flowerId: Int = 0
    private var flowerNombre: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            flowerId = bundle.getInt("FlowerId")
            flowerNombre = bundle.getString("FlowerName").toString()
            Log.d("bundle", "Bundle recibido: flowerid=$flowerId, flowerNombre=$flowerNombre")
        }
        flowerId.let { id ->
            viewModel.getFlowersDetailsByIdFromInternet(id)
        }

        viewModel.getFlowersDetail().observe(viewLifecycleOwner) { flowerDetails ->
            if (flowerDetails != null) {
                Glide.with(binding.imageD).load(flowerDetails.imagen).into(binding.imageD)
                binding.tvNombre.text = flowerDetails.nombre
                binding.tvTipo.text = flowerDetails.tipo
                binding.textDescripcionD.text = flowerDetails.descripcion
                binding.fab.setOnClickListener {
                    enviarCorreo(flowerNombre)
                    Log.d("Button", "Contactar")
                }
            }
        }

    }

    fun enviarCorreo(nombre: String) {
        Log.d("fun", "funcion enviar correo")
        val recipientEmail = "luci@plantapp.cl"
        val subject = "Consulta por Producto $nombre"
        val message = "Hola,\n\n" +
                "Vi el producto $nombre y me gustaría que me contactaran a este correo o al\n" +
                "siguiente número _________\n" + "Quedo atento."

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientEmail))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(Intent.createChooser(intent, "Enviar correo"))
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error al enviar el correo", Toast.LENGTH_SHORT)
                .show()
        }
    }
}