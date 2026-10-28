package com.oceanbrasil_android_intro_out26

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.oceanbrasil_android_intro_out26.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nomeDigitado = arguments?.getString("NOME_DIGITADO")

        val cidadeDigitada = arguments?.getString("CIDADE_DIGITADA")

        binding.textviewSecond.text = nomeDigitado ?: "Nenhum nome digitado"

        binding.textviewThird.text = cidadeDigitada ?: "Nenhuma cidade digitada"
        
        Glide.with(this)
            .load("https://p2.trrsf.com/image/fget/cf/1200/630/middle/images.terra.com/2026/10/26/784465032-b292cff7-e829-4eba-bb30-724c62cd49d2.jpg")
            .into(binding.imageviewBackground)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}