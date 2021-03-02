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
package com.example.androiddevchallenge.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.app.ProjectApp
import com.example.androiddevchallenge.bean.Dog
import com.example.androiddevchallenge.ui.theme.MyTheme

const val CURRENT_DOG = "current_dog"

class DogDetailActivity : AppCompatActivity() {

    /**
     * 控制显示dialog
     */
    var showConfirmDialog by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dog = intent.getParcelableExtra<Dog>(CURRENT_DOG)
        setContent {
            MyTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = dog?.let { it.name } ?: "dog detail",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }
                        )
                    }
                ) {

                    Column(verticalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        HandleDogData(dog)
                    }
                }
            }
        }
    }

    /**
     * 处理数据
     */
    @Composable
    private fun HandleDogData(dog: Dog?) {
        dog?.let {
            DogHeaderImg(imgName = it.avatarFileName)
            DogAdoptButton(adopted = it.adopted)
            DogIntroduction(introduction = it.introduction)

            if (showConfirmDialog) {
                DogAdoptDialog(it)
            }
        }
    }

    /**
     * 头像
     */
    @Composable
    private fun DogHeaderImg(imgName: String) {
        var imageResource = ProjectApp.mApp.resources.getIdentifier(
            imgName,
            "drawable",
            ProjectApp.mApp.packageName
        )
        var dogImg = painterResource(imageResource)

        val headerModifier = Modifier.requiredSize(150.dp)
            .clip(shape = CircleShape)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = dogImg,
                contentDescription = "dog",
                modifier = headerModifier,
                contentScale = ContentScale.Crop
            )
        }
    }

    /**
     * Adopt Button
     */
    @Composable
    private fun DogAdoptButton(adopted: Boolean) {
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(
                onClick = {
                    showConfirmDialog = true
                },
                enabled = !adopted
            ) {
                Text(text = if (adopted) "Adopted" else "Adopt")
            }
        }
    }

    /**
     * adopt dialog
     */
    @Composable
    private fun DogAdoptDialog(dog: Dog) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            text = { Text(text = "Do you want to adopt this dog ?") },
            confirmButton = {
                Button(
                    onClick = {
                        showConfirmDialog = false
                        dog.adopted = true
                    }
                ) {
                    Text(text = "adopt")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showConfirmDialog = false
                    }
                ) {
                    Text(text = "cancel")
                }
            }
        )
    }

    /**
     * 详情介绍
     */
    @Composable
    private fun DogIntroduction(introduction: String) {
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Text(
            text = introduction,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            fontStyle = FontStyle.Italic
        )
    }
}
