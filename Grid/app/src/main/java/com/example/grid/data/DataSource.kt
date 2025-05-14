package com.example.grid.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.grid.R

data class DataSource(
    @StringRes val stringId:Int,
    val id:Int,
    @DrawableRes val imgId: Int
)


    val topics = listOf(
        DataSource(R.string.architecture, 58, R.drawable.architecture),
        DataSource(R.string.crafts, 121, R.drawable.crafts),
        DataSource(R.string.business, 78, R.drawable.business),
        DataSource(R.string.culinary, 118, R.drawable.culinary),
        DataSource(R.string.design, 423, R.drawable.design),
        DataSource(R.string.fashion, 92, R.drawable.fashion),
        DataSource(R.string.film, 165, R.drawable.film),
        DataSource(R.string.gaming, 164, R.drawable.gaming),
        DataSource(R.string.drawing, 326, R.drawable.drawing),
        DataSource(R.string.lifestyle, 305, R.drawable.lifestyle)
    )

    val topics2=listOf(
        DataSource(R.string.music, 212, R.drawable.music),
        DataSource(R.string.painting, 172, R.drawable.painting),
        DataSource(R.string.photography, 321, R.drawable.photography),
        DataSource(R.string.tech, 118, R.drawable.tech),
        DataSource(R.string.ecology, 326, R.drawable.ecology),
        DataSource(R.string.biology, 305, R.drawable.biology),
        DataSource(R.string.physics, 212, R.drawable.physics),
        DataSource(R.string.law, 172, R.drawable.law),
        DataSource(R.string.automotive, 321, R.drawable.automotive),
        DataSource(R.string.geology, 118, R.drawable.geology)
    )
