package com.taidang.themoviedb.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJsonToObject(json: String?): T? {
    return json?.let {
        fromJson(it, T::class.java)
    }
}

inline fun <reified T> Gson.fromJsonToListOf(json: String?): List<T> {
    return json?.let {
        val type = object : TypeToken<List<T>>() {}.type
        fromJson(it, type) as List<T>
    } ?: emptyList()
}