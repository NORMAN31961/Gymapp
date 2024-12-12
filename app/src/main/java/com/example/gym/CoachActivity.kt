package com.example.gym

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.gym.databinding.ActivityCoachBinding
import com.example.gym.fragments.HomeCoachFragment

class CoachActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(savedInstanceState == null){
           replaceFragment(HomeCoachFragment())
        }

    }

    fun replaceFragment(frag: Fragment) {
        val fgTransaction = supportFragmentManager.beginTransaction()
        fgTransaction.replace(R.id.coach_activity, frag)
        fgTransaction.addToBackStack(null)
        fgTransaction.commit()
    }
}