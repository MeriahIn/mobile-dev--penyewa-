package com.horehoreteam.sewain_hackathon2023.services

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.horehoreteam.sewain_hackathon2023.R
import com.horehoreteam.sewain_hackathon2023.databinding.ActivityServicePageOwner1Binding
import com.horehoreteam.sewain_hackathon2023.databinding.ActivityServicePageOwnerBinding

class ServicePageOwnerActivity : AppCompatActivity() {

    private lateinit var binding:ActivityServicePageOwnerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServicePageOwnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.acIvBack.setOnClickListener {
            finish()
        }
    }
}