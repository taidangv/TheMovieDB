package com.taidang.themoviedb.presentation.manager

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.taidang.themoviedb.domain.model.Country
import com.taidang.themoviedb.domain.model.ImagesConfig
import com.taidang.themoviedb.extension.fromJsonToListOf
import com.taidang.themoviedb.extension.fromJsonToObject

class SharedPrefAppConfigManager(private val mPref: SharedPreferences, private val mGson: Gson)
    : AppConfigManager {
    companion object {

        const val PREF_IMAGES_CONFIG = "images-config"
        const val PREF_COUNTRIES = "countries"
        const val PREF_CURRENT_COUNTRY_CODE = "current-country-code"
        const val PREF_FIRST_TIME_LAUNCH = "first-time-launch"
    }

    override fun getImagesConfig(): ImagesConfig? {
        val json = mPref.getString(PREF_IMAGES_CONFIG, null)
        return mGson.fromJsonToObject(json)
    }

    override fun saveImagesConfig(imagesConfig: ImagesConfig?) {
        imagesConfig?.let {
            mPref.edit { putString(PREF_IMAGES_CONFIG, mGson.toJson(imagesConfig)) }
        }
    }

    override fun getCountries(): List<Country> {
        val json = mPref.getString(PREF_COUNTRIES, null)
        return mGson.fromJsonToListOf(json)
    }

    override fun saveCountries(countries: List<Country>) {
        mPref.edit { putString(PREF_COUNTRIES, mGson.toJson(countries)) }
    }

    override fun getCurrentCountryCode(): String {
        return mPref.getString(PREF_CURRENT_COUNTRY_CODE, "US")
    }

    override fun saveCurrentCountryCode(countryCode: String) {
        mPref.edit { putString(PREF_CURRENT_COUNTRY_CODE, countryCode) }
    }

    override fun setLaunchAppAlready() {
        mPref.edit { putBoolean(PREF_FIRST_TIME_LAUNCH, false) }
    }

    override fun isFirstTimeLaunchApp(): Boolean {
        return mPref.getBoolean(PREF_FIRST_TIME_LAUNCH, true)
    }
}
