package com.example.gym.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gym.CoachActivity
import com.example.gym.databinding.AlertDialogCreateRoutineBinding
import com.example.gym.databinding.FragmentAsiggnNameRoutineBinding

class AssignNameRoutineFragment : Fragment() {

    private var _binding: FragmentAsiggnNameRoutineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAsiggnNameRoutineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {
        event_Back_Button()
        event_Next_Button()
    }

    private fun event_Back_Button() {
        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun event_Next_Button() {
        binding.btnCompleteCreateRoutine.setOnClickListener {
            showMessageRoutineAdded()
        }
    }

    private fun showMessageRoutineAdded() {
        val dialogBinding = AlertDialogCreateRoutineBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .setPositiveButton("Crear Rutina") { _, _ ->
                Toast.makeText(requireContext(), "Rutina creada", Toast.LENGTH_SHORT).show()
                (activity as CoachActivity).replaceFragment(HomeCoachFragment())
            }
            .setNegativeButton("Cancelar"){ dialog, _ ->
                Toast.makeText(requireContext(), "Cancelado", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .create()

        dialog.show()

    }

}