package com.example.analogunsplash.presentation.auth.finish

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.analogUnsplash.databinding.FragmentFinishAuthorizationBinding
import com.example.analogunsplash.presentation.auth.finish.FinishAuthorizationViewModel.Companion.PLUG
import com.example.analogunsplash.presentation.auth.finish.FinishAuthorizationViewModel.Companion.START_REQUEST
import com.example.analogunsplash.presentation.auth.finish.adapter.PagingPhotoAdapter
import com.example.analogunsplash.tools.baseModel.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FinishAuthorizationFragment : BaseFragment<FragmentFinishAuthorizationBinding>() {

    private val navArgs by navArgs<FinishAuthorizationFragmentArgs>()

    override fun initBinding(inflater: LayoutInflater) =
        FragmentFinishAuthorizationBinding.inflate(inflater)

    private val adapter = PagingPhotoAdapter()

    private val viewModel by viewModel<FinishAuthorizationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startFragment(viewModel.getToken())

/*        tokenObserver(createTokenSharedPreference())

        photoObserve()*/
    }

    private fun startFragment(token: String) {
        when (token) {
            PLUG -> viewModel.createToken(navArgs.code)
            START_REQUEST -> {}
            else -> viewModel.getPhoto()
        }
    }

/*    private fun photoObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            if (createTokenSharedPreference().getString("Token", "") != PLUG
                && createTokenSharedPreference().getString("Token", "") != START_REQUEST
            )
                viewModel.listPhoto.collect { responsePhoto ->
                    Log.e("Kart", responsePhoto.toString())
                    adapter.submitData(responsePhoto)
                    binding.recyclerView.adapter = adapter
                }
        }
    }*/

    private fun tokenObserver(pref: SharedPreferences) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.token.collect { token ->
                pref.edit().putString("Token", token).apply()
            }
        }
    }
}