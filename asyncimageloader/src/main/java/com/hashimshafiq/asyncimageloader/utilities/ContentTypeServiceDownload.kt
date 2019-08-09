package com.hashimshafiq.asyncimageloader.utilities


import com.hashimshafiq.asyncimageloader.callback.ContentServiceObserver
import com.hashimshafiq.asyncimageloader.callback.ContentServiceStatusObserver
import com.hashimshafiq.asyncimageloader.models.ServiceContentTypeDownload
import com.loopj.android.http.AsyncHttpClient
import java.util.*

class ContentTypeServiceDownload private constructor() {
    private val allRequestsByKey = HashMap<String, LinkedList<ServiceContentTypeDownload>>()
    private val allRequestsClient = HashMap<String, AsyncHttpClient>()
    private val contentServiceCachingManager: ContentServiceCachingManager

    val isQueueEmpty: Boolean
        get() = allRequestsByKey.size == 0

    init {
        contentServiceCachingManager = ContentServiceCachingManager.getInstance()
    }



    fun getRequest(serviceContentTypeDownload: ServiceContentTypeDownload) {
        val mKey = serviceContentTypeDownload.keyMD5
        // Check if exist in the cache

        val serviceContentTypeDownloadFromCache : ServiceContentTypeDownload? = contentServiceCachingManager.getMDownloadDataType(mKey)

        if (serviceContentTypeDownloadFromCache != null) {
            serviceContentTypeDownloadFromCache.comeFrom = "Cache"
            // call interface
            val contentServiceObserver = serviceContentTypeDownload.contentServiceObserver
            contentServiceObserver.onStart(serviceContentTypeDownloadFromCache)
            contentServiceObserver.onSuccess(serviceContentTypeDownloadFromCache)
            return
        }
        // This will run if two request come for same url
        if (allRequestsByKey.containsKey(mKey)) {
            serviceContentTypeDownload.comeFrom = "Cache"
            allRequestsByKey[mKey]!!.add(serviceContentTypeDownload)
            return
        }
        // Put it in the allRequestsByKey to manage it after done or cancel
        if (allRequestsByKey.containsKey(mKey)) {
            allRequestsByKey[mKey]!!.add(serviceContentTypeDownload)
        } else {
            val lstMDDataType = LinkedList<ServiceContentTypeDownload>()
            lstMDDataType.add(serviceContentTypeDownload)
            allRequestsByKey[mKey] = lstMDDataType
        }
        // Create new WebCallDataTypeDownload by clone it from the parameter
        val contentServiceObserver = object : ContentServiceObserver{

                override fun onStart(serviceContentTypeDownload: ServiceContentTypeDownload) {
                    for (m in allRequestsByKey[serviceContentTypeDownload.keyMD5]!!) {
                        m.data = serviceContentTypeDownload.data
                        m.contentServiceObserver.onStart(m)
                    }
                }

                override fun onSuccess(serviceContentTypeDownload: ServiceContentTypeDownload) {
                    for (m in allRequestsByKey[serviceContentTypeDownload.keyMD5]!!) {
                        m.data = serviceContentTypeDownload.data
                        m.contentServiceObserver.onSuccess(m)
                    }
                    allRequestsByKey.remove(serviceContentTypeDownload.keyMD5)
                }

                override fun onFailure(
                    serviceContentTypeDownload: ServiceContentTypeDownload,
                    statusCode: Int,
                    errorResponse: ByteArray,
                    e: Throwable
                ) {
                    for (m in allRequestsByKey[serviceContentTypeDownload.keyMD5]!!) {
                        m.data = serviceContentTypeDownload.data
                        m.contentServiceObserver.onFailure(m, statusCode, errorResponse, e)
                    }
                    allRequestsByKey.remove(serviceContentTypeDownload.keyMD5)
                }

                override fun onRetry(serviceContentTypeDownload: ServiceContentTypeDownload, retryNo: Int) {
                    for (m in allRequestsByKey[serviceContentTypeDownload.keyMD5]!!) {
                        m.data = serviceContentTypeDownload.data
                        m.contentServiceObserver.onRetry(m, retryNo)
                    }
                }

        }
        val newWebCallDataTypeDownload = serviceContentTypeDownload.getCopyOfMe(contentServiceObserver)
        // Get from download manager
        val contentServiceSyncTaskManager = ContentServiceSyncTaskManager()
        val contentServiceStatusObserver = object : ContentServiceStatusObserver {
            override fun setDone(serviceContentTypeDownload: ServiceContentTypeDownload) {
                    // put in the cache when mark as done
                    contentServiceCachingManager.putMDownloadDataType(serviceContentTypeDownload.keyMD5, serviceContentTypeDownload)
                    allRequestsClient.remove(serviceContentTypeDownload.keyMD5)
                }

                override fun onFailure(serviceContentTypeDownload: ServiceContentTypeDownload) {
                    allRequestsClient.remove(serviceContentTypeDownload.keyMD5)
                }

                override fun setCancelled(serviceContentTypeDownload: ServiceContentTypeDownload) {
                    allRequestsClient.remove(serviceContentTypeDownload.url)
                }

        }
        val client = contentServiceSyncTaskManager.get(serviceContentTypeDownload,contentServiceStatusObserver)
        allRequestsClient["abc"] = client
    }

    fun cancelRequest(serviceContentTypeDownload: ServiceContentTypeDownload) {
        if (allRequestsByKey.containsKey(serviceContentTypeDownload.keyMD5)) {
            allRequestsByKey[serviceContentTypeDownload.keyMD5]!!.remove(serviceContentTypeDownload)
            serviceContentTypeDownload.comeFrom = "cancelRequest"
            serviceContentTypeDownload.contentServiceObserver.onFailure(serviceContentTypeDownload, 0, null!!, null!!)
        }
    }

    fun clearTheCash() {
        contentServiceCachingManager.clearTheCash()
    }

    companion object {
        private var instance: ContentTypeServiceDownload? = null

        fun getInstance(): ContentTypeServiceDownload {
            if (instance == null) {
                instance = ContentTypeServiceDownload()
            }
            return instance as ContentTypeServiceDownload
        }
    }
}
