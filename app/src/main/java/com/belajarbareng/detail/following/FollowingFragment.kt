package com.belajarbareng.detail.following

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.belajarbareng.R

class FollowingFragment : Fragment() {

    companion object {
        fun newInstance() = FollowingFragment()
    }

    private lateinit var viewModel: FollowingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.following_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FollowingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}