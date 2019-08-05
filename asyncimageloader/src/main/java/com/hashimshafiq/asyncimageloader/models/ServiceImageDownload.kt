package com.hashimshafiq.asyncimageloader.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.hashimshafiq.asyncimageloader.callback.ContentServiceObserver


class ServiceImageDownload(url: String, contentServiceObserver: ContentServiceObserver) :
    ServiceContentTypeDownload(url, ContentDataType.IMAGE, contentServiceObserver) {

    val imageBitmap: Bitmap
        get() = BitmapFactory.decodeByteArray(data, 0, data!!.size)

    override fun getCopyOfMe(contentServiceObserver: ContentServiceObserver): ServiceContentTypeDownload {
        return ServiceImageDownload(this.url, contentServiceObserver)
    }
}
