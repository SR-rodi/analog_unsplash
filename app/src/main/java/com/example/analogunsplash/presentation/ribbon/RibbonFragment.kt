package com.example.analogunsplash.presentation.ribbon

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.analogunsplash.data.state.LoadState
import com.example.analogUnsplash.databinding.FragmentRibbornBinding
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.data.state.ItemButton
import com.example.analogunsplash.presentation.ribbon.adapter.PagingPhotoAdapter
import com.example.analogunsplash.tools.baseModel.BaseFragment
import com.example.analogunsplash.tools.setChangeTextListener
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RibbonFragment : BaseFragment<FragmentRibbornBinding>() {

    private val viewModel by viewModel<RibbonViewModel>()

    private val adapter by lazy { PagingPhotoAdapter() { item, button -> onClick(item, button) } }

    override fun initBinding(inflater: LayoutInflater) = FragmentRibbornBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsObserve()
        loadStateItemsObserve()
        loadStateLick()
        settingAdapter()
        setSearchView()
    }

    private fun onClick(item: TapeItem, button: ItemButton) {
        when (button) {
            ItemButton.IMAGE -> findNavController()
                .navigate(RibbonFragmentDirections.actionPhotoFragmentToDetailFragment(item.photoId))
            ItemButton.LICK -> viewModel.setLick(item)
        }
    }

    private fun setSearchView() {

        val searchView = binding.topAppBar.menu.getItem(0).actionView as SearchView
        searchView.setChangeTextListener { query -> viewModel.setQuery(query) { adapter.refresh() } }
    }

    private fun settingAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator?.changeDuration = 0
    }

    private fun loadStateItemsObserve() {
        adapter.addLoadStateListener { loadState ->
            binding.error.isVisible = loadState.mediator?.refresh is androidx.paging.LoadState.Error
        }
    }

    private fun loadStateLick() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadState.collect { loadStateLick ->
                binding.error.isVisible =
                    loadStateLick == LoadState.ERROR
            }
        }
    }

    private fun itemsObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getItems().collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}