package com.ubaya.anmpweek1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.anmpweek1.databinding.FragmentGameBinding
import com.ubaya.anmpweek1.databinding.FragmentMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private var num1: Int = 0
    private var num2: Int = 0
    private var score: Int = 0
    private val random = Random

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if(arguments != null){
            val name = GameFragmentArgs.fromBundle(requireArguments()).playerName
            binding.txtTurn.text = "$name's turn"
            DefaultNumbers()
        }

        binding.btnSubmit.setOnClickListener {
            val answer = binding.txtAnswer.text.toString().toIntOrNull()
            if(answer != null && answer == num1 + num2) {
                score++
                DefaultNumbers()
            } else {
                val finalScore = score.toString()
                val action = GameFragmentDirections.actionResultFragment(finalScore)
                Navigation.findNavController(it).navigate(action)
            }
        }

    }

    private fun DefaultNumbers() {
        num1 = random.nextInt(100)
        num2 = random.nextInt(100)
        binding.txtNum1.text = num1.toString()
        binding.txtNum2.text = num2.toString()
        binding.txtAnswer.text.clear()
    }
}