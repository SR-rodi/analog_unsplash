package com.example.analogunsplash.presentation.auth.start

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.analogUnsplash.R
import com.example.analogUnsplash.databinding.FragmentAuthorizationBinding
import com.example.analogunsplash.data.state.LoadState
import com.example.analogunsplash.tools.LINK_INTENT
import com.example.analogunsplash.tools.TOKEN_SHARED_NAME
import com.example.analogunsplash.tools.baseModel.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthorizationFragment : BaseFragment<FragmentAuthorizationBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentAuthorizationBinding.inflate(inflater)

    private val viewModel by viewModel<AuthorizationViewModel>()

    private val args by navArgs<AuthorizationFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startAuth()

        tokenObserve(createSharedPreference(TOKEN_SHARED_NAME))

        loadingObserve()

        viewModel.createToken(args.code)
    }

    private fun tokenObserve(preferences: SharedPreferences) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.token.collect { token ->
                preferences.edit().putString("Token", token).apply()
            }
        }
    }

    private fun loadingObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadState.collect { loadState ->
                when (loadState) {
                    LoadState.START ->
                        setLoadState(buttonIsEnabled = true, textIsVisible = false, progressIsVisible = false)
                    LoadState.LOADING -> setLoadState(buttonIsEnabled = false, textIsVisible = false, progressIsVisible = true)
                    LoadState.SUCCESS -> {
                        setLoadState(buttonIsEnabled = false, textIsVisible = true, progressIsVisible = false)
                        findNavController().navigate(R.id.action_authorizationFragment_to_photoFragment)
                    }
                    LoadState.ERROR -> {
                        setLoadState(buttonIsEnabled = true, textIsVisible = true, progressIsVisible = false)
                        binding.text.text = loadState.message
                    }
                }
            }
        }
    }

    private fun setLoadState(
        buttonIsEnabled: Boolean,
        textIsVisible: Boolean,
        progressIsVisible: Boolean
    ) {
        binding.buttonAuthorization.isEnabled = buttonIsEnabled
        binding.text.isVisible = textIsVisible
        binding.progressBar.isVisible = progressIsVisible
    }

    private fun startAuth() {
        binding.buttonAuthorization.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(LINK_INTENT)))
        }
    }

}