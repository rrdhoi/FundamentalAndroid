package com.belajarbareng

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.belajarbareng.MainActivity.Companion.DATA_USER
import com.belajarbareng.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dataUser = intent.getParcelableExtra<User>(DATA_USER) as User
        binding.tvName.text = dataUser.name
        binding.tvLocation.text = dataUser.userLocation
        binding.ivProfile.setImageResource(dataUser.photo)
    }
}