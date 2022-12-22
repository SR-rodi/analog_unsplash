package com.example.analogunsplash.presentation.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.analogUnsplash.databinding.FragmentPhotoBinding
import com.example.analogunsplash.presentation.photo.adapter.PagingPhotoAdapter
import com.example.analogunsplash.tools.baseModel.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoFragment : BaseFragment<FragmentPhotoBinding>() {

    private val viewModel by viewModel<PhotoViewModel>()

    private val adapter by lazy { PagingPhotoAdapter() }

    override fun initBinding(inflater: LayoutInflater) = FragmentPhotoBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        binding.recyclerView.adapter = adapter
    }



    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.photo.collect {
                adapter.submitData(it)
            }
        }
    }
}