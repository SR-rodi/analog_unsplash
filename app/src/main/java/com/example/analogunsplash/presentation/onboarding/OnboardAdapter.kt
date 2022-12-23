package com.example.analogunsplash.presentation.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.analogUnsplash.databinding.ItemOnboardingBinding

class OnboardAdapter(
    private val list: List<String>
) : RecyclerView.Adapter<OnboardAdapter.OnboardViewHolder>() {

    class OnboardViewHolder(private val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) { binding.text.text = item }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OnboardViewHolder(
        ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: OnboardViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}