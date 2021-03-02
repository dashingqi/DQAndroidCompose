package com.example.androiddevchallenge.utils

import com.example.androiddevchallenge.app.ProjectApp
import com.example.androiddevchallenge.bean.Dog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * @author : zhangqi
 * @time : 2021/3/2
 * desc :
 */
object DogDataUtil {

    /**
     * 从assets中读取json文件
     */
    fun getDogData(): List<Dog> {
        val assetsManager = ProjectApp.mApp.assets
        val inputStreamReader = InputStreamReader(assetsManager.open("dogs.json"))
        val beanStr = BufferedReader(inputStreamReader).readText()
        val typeOf = object : TypeToken<List<Dog>>() {}.type
        return Gson().fromJson(beanStr, typeOf)
    }
}