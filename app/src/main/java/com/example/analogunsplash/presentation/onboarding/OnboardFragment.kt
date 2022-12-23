package com.example.analogunsplash.presentation.onboarding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.analogUnsplash.databinding.FragmentOnboardingBinding
import com.example.analogunsplash.tools.baseModel.BaseFragment

class OnboardFragment : BaseFragment<FragmentOnboardingBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentOnboardingBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = listOf("один", "два", "три")

        val adapter = OnboardAdapter(list)

        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                    Log.d("Kart","${(positionOffset+position+1)*10}")

                    binding.Wave.setHeightWave(((positionOffset+position+1)*10)/1000)
                }
            }
        )
    }

}