package com.taidang.themoviedb.repository.response

abstract class ListBasedResponse<out T>(val results: List<T>,
                                        val total_results: Int,
                                        val page: Int,
                                        val total_pages: Int)