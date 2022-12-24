package com.example.analogunsplash.presentation.detail

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.analogunsplash.data.dto.photo.PhotoItemDto
import com.example.analogunsplash.data.model.TapeItem
import com.example.analogunsplash.di.viewModelModel
import com.example.analogunsplash.domine.repository.DetailUseCase
import com.example.analogunsplash.domine.repository.PhotoRepository
import com.example.analogunsplash.tools.baseModel.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.File

class DetailViewModel(
    private val detailUseCase: DetailUseCase,
    private val photoRepository: PhotoRepository,
) : BaseViewModel() {

    private val _item = MutableSharedFlow<PhotoItemDto>()
    val item = _item.asSharedFlow()

    fun getPhoto(id: String) {
        viewModelScope.launch {
            _item.emit(detailUseCase.getPhotoByID(id))
        }
    }

    fun startDownLoad(url: String, downloadManager: DownloadManager) {
        val downloadRequest = DownloadManager.Request(Uri.parse(url))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)//качать только по войфай или нет
            .setTitle("загрузка")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                File.separator + "test.jpg")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED) // показать сообщение о скачивании

        downloadManager.enqueue(downloadRequest)
    }

}