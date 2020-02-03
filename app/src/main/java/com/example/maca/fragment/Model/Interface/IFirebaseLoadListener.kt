package com.example.maca.fragment.Model.Interface

import com.example.maca.fragment.Model.ItemGroup

interface IFirebaseLoadListener {
    fun onFirebaseLoadSuccess(itemGroupList:List<ItemGroup>)
    fun onFirebaseLoadFailed(message:String)
}