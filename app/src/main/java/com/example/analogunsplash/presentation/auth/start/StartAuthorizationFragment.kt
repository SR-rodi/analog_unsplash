package com.example.analogunsplash.presentation.auth.start

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.analogUnsplash.databinding.FragmentStartAuthorizationBinding
import com.example.analogunsplash.tools.LINK_INTENT
import com.example.analogunsplash.tools.TOKEN_SHARED_NAME
import com.example.analogunsplash.tools.baseModel.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartAuthorizationFragment : BaseFragment<FragmentStartAuthorizationBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentStartAuthorizationBinding.inflate(inflater)

    private val viewModel by viewModel<StartAuthorizationViewModel>()

    private val args by navArgs<StartAuthorizationFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startAuth()

        tokenObserve(createSharedPreference(TOKEN_SHARED_NAME))

        viewModel.createToken(args.code)
    }

    private fun tokenObserve(preferences: SharedPreferences) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.token.collect { token ->
                preferences.edit().putString("Token", token).apply()
            }
        }
    }

    private fun startAuth() {
        binding.buttonAuthorization.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(LINK_INTENT)))
        }
    }

}