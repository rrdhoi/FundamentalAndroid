package com.belajarbareng.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.belajarbareng.MainActivity.Companion.DATA_USER
import com.belajarbareng.R
import com.belajarbareng.User
import com.belajarbareng.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

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

        setUpViewPager()
    }

    private fun setUpViewPager() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val tabs : TabLayout = binding.tabs
        val viewPager :ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(tabs, viewPager) { tab, position -> tab.text = resources.getString(
            TAB_TITTLES[position])
        }.attach()
    }

    companion object {
        private val TAB_TITTLES = intArrayOf(
            R.string.tab_followers,
            R.string.tab_following,
        )
    }
}