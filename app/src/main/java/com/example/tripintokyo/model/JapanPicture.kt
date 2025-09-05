package com.example.tripintokyo.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class JapanPicture(
    @StringRes val day: Int,
    @StringRes val stringResources: Int,
    @DrawableRes val imageResourceId: Int
)
