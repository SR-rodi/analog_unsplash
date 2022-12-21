package com.example.analogunsplash.tools.baseModel

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.analogUnsplash.R
import com.example.analogunsplash.data.state.LoadState
import com.example.analogunsplash.tools.TOKEN_SHARED_NAME
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    protected var _binding: B? = null
    protected val binding get() = _binding!!

    abstract fun initBinding(inflater: LayoutInflater): B?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = initBinding(inflater)
        return binding.root
    }

    protected fun createSharedPreference(sharedName: String) =
        requireContext().getSharedPreferences(sharedName, Context.MODE_PRIVATE)

    protected fun loadStateObserve(
        stateFlow: StateFlow<LoadState>,
        progressBar: View,
        errorView:TextView
    ) {

        viewLifecycleOwner.lifecycleScope.launch {
            stateFlow.collect { state ->
                when (state) {
                    LoadState.LOADING -> progressBar.isVisible = true
                    LoadState.SUCCESS -> progressBar.isVisible = false
                    LoadState.ERROR -> errorView.text = getString(R.string.error_text)
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}