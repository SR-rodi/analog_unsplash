package com.example.analogunsplash.presentation.ribbon

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import com.example.analogUnsplash.databinding.FragmentRibbornBinding
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.presentation.ribbon.adapter.PagingPhotoAdapter
import com.example.analogunsplash.tools.baseModel.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.FieldPosition

class RibbonFragment : BaseFragment<FragmentRibbornBinding>() {

    private val viewModel by viewModel<RibbonViewModel>()

    private val adapter by lazy { PagingPhotoAdapter(){onClick(it)} }

    private fun onClick(itemInStrip: TapeItem) {
        viewModel.setLick(itemInStrip)
    }

    override fun initBinding(inflater: LayoutInflater) = FragmentRibbornBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator?.changeDuration = 0
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getItems().collect {
                adapter.submitData(it)
            }
        }
    }


}