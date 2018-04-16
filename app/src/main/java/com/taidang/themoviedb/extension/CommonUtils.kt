package com.taidang.themoviedb.extension

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

fun <T> createType(): Type {
    return object : TypeToken<List<T>>() {}.type
}