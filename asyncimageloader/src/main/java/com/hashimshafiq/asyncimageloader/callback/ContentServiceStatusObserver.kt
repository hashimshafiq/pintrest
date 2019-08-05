package com.hashimshafiq.asyncimageloader.callback

import com.hashimshafiq.asyncimageloader.models.ServiceContentTypeDownload


interface ContentServiceStatusObserver {
    fun setDone(serviceContentTypeDownload: ServiceContentTypeDownload)

    fun setCancelled(serviceContentTypeDownload: ServiceContentTypeDownload)

    fun onFailure(serviceContentTypeDownload: ServiceContentTypeDownload)
}
