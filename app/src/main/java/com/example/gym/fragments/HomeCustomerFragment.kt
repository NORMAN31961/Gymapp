package com.example.gym

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.gym.databinding.FragmentHomeCustomerBinding
import com.example.gym.fragments.ProfileFragment
import com.example.gym.fragments.RoutineFragment
import com.example.gym.fragments.SettingsFragment

class HomeCustomerFragment : Fragment() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    private var _binding: FragmentHomeCustomerBinding? = null
    private val binding get() = _binding!!

//    override fun onCreate(savedInstanceState: Bundle?) {
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeCustomerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       init()
    }

    private fun init(){
        initDrawer()
        buttons_Navigation()
        event_Routine()
    }

    fun initDrawer() {
        drawerLayout = binding.drawerLayoutHomeCustomer
        actionBarDrawerToggle =
            ActionBarDrawerToggle(
                context as MainActivity,
                drawerLayout,
                R.string.open,
                R.string.close
            )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        binding.menuIcon.setOnClickListener {
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

    private fun event_Routine(){
        binding.routineContainerHomeCustomer.setOnClickListener {
            launchRoutineFragment()
        }
    }

    private fun launchProfileFragment(){
        (activity as MainActivity).replaceFragment(ProfileFragment())
    }

    private fun launchSettingsFragment(){
        (activity as MainActivity).replaceFragment(SettingsFragment())
    }

    private fun launchRoutineFragment(){
        (activity as MainActivity).replaceFragment(RoutineFragment())
    }



}