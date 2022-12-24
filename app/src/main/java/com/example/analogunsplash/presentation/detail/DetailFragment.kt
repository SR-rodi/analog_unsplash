package com.example.analogunsplash.presentation.detail

import android.app.DownloadManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.analogUnsplash.databinding.FragmentDetailBinding
import com.example.analogunsplash.tools.baseModel.BaseFragment
import com.example.analogunsplash.tools.loadImage
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentDetailBinding.inflate(inflater)

    private val args by navArgs<DetailFragmentArgs>()

    private val viewModel by viewModel<DetailViewModel>()

    private var urlDownload = ""

    private val downloadManager by lazy {
        requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  viewModel.getPhoto("7mBictB_urk"/*args.id*/)
        itemObserve()

        onClickDownload(urlDownload, downloadManager)

    }

    private fun onClickDownload(url: String, downloadManager: DownloadManager){
        binding.poster.root.setOnClickListener {
            viewModel.startDownLoad(urlDownload, downloadManager)

        }
    }
    private fun itemObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.item.collect {
                binding.poster.image.loadImage(it.urls.full)
                urlDownload = it.urls.raw
            }
        }
    }
}