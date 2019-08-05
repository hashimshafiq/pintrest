package com.hashimshafiq.asyncimageloader.models

import com.google.gson.Gson
import com.hashimshafiq.asyncimageloader.callback.ContentServiceObserver


import java.lang.reflect.Type

class ServiceJsonDownload(url: String, contentServiceObserver: ContentServiceObserver) : ServiceContentTypeDownload(url, ContentDataType.JSON, contentServiceObserver) {

    private val jsonAsString: String
        get() {
            try {
                return String(data!!, Charsets.UTF_8)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }

    override fun getCopyOfMe(contentServiceObserver: ContentServiceObserver): ServiceContentTypeDownload {
        return ServiceJsonDownload(this.url, contentServiceObserver)
    }

    fun getJson(type: Type): Any {
        val gson = Gson()
        return gson.fromJson(jsonAsString, type)
    }
}
