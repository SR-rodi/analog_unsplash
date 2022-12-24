package com.example.analogunsplash.presentation.ribbon

import android.os.Bundle
import android.view.*
import androidx.lifecycle.lifecycleScope
import com.example.analogUnsplash.databinding.FragmentRibbornBinding
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.presentation.ribbon.adapter.PagingPhotoAdapter
import com.example.analogunsplash.tools.baseModel.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RibbonFragment : BaseFragment<FragmentRibbornBinding>() {

    private val viewModel by viewModel<RibbonViewModel>()

    private val adapter by lazy { PagingPhotoAdapter() { onClick(it) } }

    private fun onClick(itemInStrip: TapeItem) {
        viewModel.setLick(itemInStrip)
    }

    override fun initBinding(inflater: LayoutInflater) = FragmentRibbornBinding.inflate(inflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator?.changeDuration = 0

        binding.refreshButton.setOnClickListener {
            adapter.refresh()
            //findNavController().navigate(RibbonFragmentDirections.actionPhotoFragmentToDetailFragment("7mBictB_urk"))
        }

        val searchView = binding.topAppBar.menu.getItem(0).actionView as android.widget.SearchView

        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
               viewModel.setQuery(newText){ adapter.refresh() }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })


/*
        adapter.addLoadStateListener { loadState ->
            binding.error.isVisible = loadState.mediator?.refresh is LoadState.Error
            binding.recyclerView.isVisible = loadState.source.refresh != LoadState.Loading
        }
*/


    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.test().collect {
                adapter.submitData(it)
            }
        }
    }


}