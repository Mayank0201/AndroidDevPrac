package com.example.shoppinglist.ui

data class Items(
    val id:Int,
    var name:String,
    var quantity:Int,
    var isEditing:Boolean=false
)