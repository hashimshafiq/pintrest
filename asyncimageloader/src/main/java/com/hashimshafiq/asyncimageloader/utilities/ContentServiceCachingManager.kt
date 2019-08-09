package com.hashimshafiq.asyncimageloader.utilities

import android.util.LruCache
import com.hashimshafiq.asyncimageloader.models.ServiceContentTypeDownload


class ContentServiceCachingManager private constructor() {
    private val maxCacheSize: Int
    private val mDownloadDataTypeCache: LruCache<String, ServiceContentTypeDownload>

    init {

        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        maxCacheSize = maxMemory / 8 // 4 * 1024 * 1024; // 4MiB
        mDownloadDataTypeCache = LruCache(maxCacheSize)
    }

    fun getMDownloadDataType(key: String): ServiceContentTypeDownload? {

            return mDownloadDataTypeCache.get(key)

    }

    fun putMDownloadDataType(key: String, serviceContentTypeDownload: ServiceContentTypeDownload): Boolean {
        return mDownloadDataTypeCache.put(key, serviceContentTypeDownload) != null
    }

    fun clearTheCash() {
        mDownloadDataTypeCache.evictAll()
    }

    companion object {
        private var instance: ContentServiceCachingManager? = null

        fun getInstance(): ContentServiceCachingManager {
            if (instance == null) {
                instance = ContentServiceCachingManager()
            }
            return instance as ContentServiceCachingManager
        }
    }
}
