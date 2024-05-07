package ru.je_dog.effective_mobile.test.feature.placeholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.je_dog.effective_mobile.test.feature.placeholder.databinding.FragmentPlaceholderBinding

class PlaceholderFragment: Fragment() {

    lateinit var binding: FragmentPlaceholderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaceholderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            screenName.text = getScreenName()
        }
    }

    private fun getScreenName(): String {
        return arguments?.getString("screenName","Placeholder") ?: "Placeholder"
    }

    companion object {

        fun create(screenName: String): PlaceholderFragment {
            val fragment = PlaceholderFragment().apply {
                val bundle = Bundle().apply {
                    putString("screenName",screenName)
                }
                arguments = bundle
            }

            return fragment
        }
    }
}