package com.example.androiddevchallenge.bean

import com.google.gson.annotations.SerializedName

/**
 * @author : zhangqi
 * @time : 2021/3/2
 * desc :
 */

class Dog(
    var name: String,
    @SerializedName("avatar_filename") var avatarFileName: String,
    var introduction: String
)