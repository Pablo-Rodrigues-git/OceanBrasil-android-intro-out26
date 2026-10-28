package com.oceanbrasil_android_intro_out26

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oceanbrasil_android_intro_out26.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etNome.blockSymbols()
        binding.etCidade.blockSymbols()

        binding.btEnviar.setOnClickListener {
            val nome = binding.etNome.text.toString()
            val cidade = binding.etCidade.text.toString()

            if (nome.isBlank()) {
                binding.etNome.error = "Voce precisa digitar um nome"
            }
            if (cidade.isBlank()) {
                binding.etCidade.error = "Voce precisa digitar uma cidade"
            }
            if (nome.isNotBlank() && cidade.isNotBlank()) {
                binding.tvResultado.text = "$nome - $cidade"
            }
        }

        binding.btAbrirNovaTela.setOnClickListener {
            val nome = binding.etNome.text.toString()
            val cidade = binding.etCidade.text.toString()

            if (nome.isBlank()) {
                binding.etNome.error = "Voce precisa digitar um nome"
                Toast.makeText(requireContext(), "Voce precisa digitar um nome", Toast.LENGTH_SHORT).show()
            } else if (cidade.isBlank()) {
                binding.etCidade.error = "Voce precisa digitar uma cidade"
                Toast.makeText(requireContext(), "Voce precisa digitar uma cidade", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = Bundle().apply {
                    putString("NOME_DIGITADO", nome)
                    putString("CIDADE_DIGITADA", cidade)
                }
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/**
 * Extension function para bloquear a digitação de símbolos em um EditText.
 * Mantém apenas letras, números e espaços.
 */
fun EditText.blockSymbols() {
    filters += InputFilter { source, start, end, _, _, _ ->
        val input = source.subSequence(start, end)
        if (input.any { !it.isLetterOrDigit() && !it.isWhitespace() }) {
            input.filter { it.isLetterOrDigit() || it.isWhitespace() }
        } else {
            null
        }
    }
}
