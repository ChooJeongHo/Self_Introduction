package com.example.self_introduction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.self_introduction.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ivProfile = binding.ivProfile
        val profileId = binding.etProfileId
        val btnFinish = binding.btnFinish
        val idData = intent.getStringExtra("LoginId")

        if (idData != null) {
            profileId.setText(idData)
        }

        // HomeActivity가 열릴때 마다 프로필 이미지가 profile1, profile2, profile3, profile4, profile5 중에 하나로 랜덤하게 설정됨
        val profileList = listOf(
            R.drawable.profile1,
            R.drawable.profile2,
            R.drawable.profile3,
            R.drawable.profile4,
            R.drawable.profile5
        )
        ivProfile.setImageResource(profileList.random())

        btnFinish.setOnClickListener {
            finish()
        }
    }
}