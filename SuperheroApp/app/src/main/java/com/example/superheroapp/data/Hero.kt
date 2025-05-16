package com.example.superheroapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.superheroapp.R

data class Hero(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @DrawableRes val img:Int)

