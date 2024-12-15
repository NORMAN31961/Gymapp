package com.example.gym.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.gym.CoachActivity
import com.example.gym.R
import com.example.gym.databinding.FragmentHomeCoachBinding


class HomeCoachFragment : Fragment() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    private var _binding: FragmentHomeCoachBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initDrawer()
        buttons_Navigation()
        event_AddRoutine()
        event_SeeRoutines()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeCoachBinding.inflate(inflater, container, false)
        return binding.root
    }


    fun initDrawer() {
        drawerLayout = binding.drawerLayoutHomeCustomer
        actionBarDrawerToggle =
            ActionBarDrawerToggle(
                context as CoachActivity,
                drawerLayout,
                R.string.open,
                R.string.close
            )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        binding.menuHomeCoach.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    fun buttons_Navigation() {
        val navigationView = binding.navigationViewHomeCustomer

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_profile -> launchProfileFragment()

                R.id.menu_settings -> launchSettingsFragment()

                R.id.menu_home -> Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    private fun launchProfileFragment() {
        (activity as CoachActivity).replaceFragment(ProfileFragment())
    }

    private fun launchSettingsFragment() {
        (activity as CoachActivity).replaceFragment(SettingsFragment())
    }

    private fun event_AddRoutine() {
        binding.addRoutineHomeCoach.setOnClickListener {
            launchAddRoutineFragment()
        }
    }

    private fun event_SeeRoutines() {
        binding.seeRoutines.setOnClickListener {
            launchSeeRoutinesFragment()
        }
    }

    private fun launchAddRoutineFragment() {
        (activity as CoachActivity).replaceFragment(Add_Excercise_to_Routine_Fragment())
    }

    private fun launchSeeRoutinesFragment() {
        (activity as CoachActivity).replaceFragment(CoachSeeRoutinesFragment())
    }

}