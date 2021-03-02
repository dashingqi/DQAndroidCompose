/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
