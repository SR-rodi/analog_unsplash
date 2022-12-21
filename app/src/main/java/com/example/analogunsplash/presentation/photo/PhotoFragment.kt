package com.example.analogunsplash.presentation.photo

import android.view.LayoutInflater
import com.example.analogUnsplash.databinding.FragmentPhotoBinding
import com.example.analogunsplash.tools.baseModel.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoFragment : BaseFragment<FragmentPhotoBinding>() {

    private val viewModel by viewModel<PhotoViewModel>()

    override fun initBinding(inflater: LayoutInflater) = FragmentPhotoBinding.inflate(inflater)


}