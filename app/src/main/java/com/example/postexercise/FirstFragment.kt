package com.example.postexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.postexercise.data.TranslationViewModel
import com.example.postexercise.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val translationViewModel: TranslationViewModel by viewModels()

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        translationViewModel.result.observe(viewLifecycleOwner) { translationData ->

            if (translationData != null) {
                val translatedText = translationData.textData.translatedText
                binding.textviewFirst.text = translatedText
            }

        }
        binding.buttonFirst.setOnClickListener {

            val inputText = binding.translationText.text.toString()
            translationViewModel.translateText(inputText)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}