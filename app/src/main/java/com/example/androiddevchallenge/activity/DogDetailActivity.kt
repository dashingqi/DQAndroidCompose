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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.app.ProjectApp
import com.example.androiddevchallenge.bean.Dog
import com.example.androiddevchallenge.ui.theme.MyTheme

const val CURRENT_DOG = "current_dog"

class DogDetailActivity : AppCompatActivity() {

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
                                    text = "dog detail",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }
                        )
                    }
                ) {

                    Column(verticalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        handleDogData(dog)
                    }
                }
            }
        }
    }

    /**
     * 头像
     */
    @Composable
    private fun handleDogData(dog: Dog?) {
        dog?.let {

            var imageResource = ProjectApp.mApp.resources.getIdentifier(
                it.avatarFileName,
                "drawable",
                ProjectApp.mApp.packageName
            )
            var dogImg = painterResource(imageResource)

            val headerModifier = Modifier.requiredSize(150.dp)
                .clip(shape = CircleShape)

            Column {
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

                Spacer(modifier = Modifier.padding(top = 20.dp))

                Text(
                    text = it.name,
                    fontSize = 18.sp,
                    color = Color.Blue,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(
                    text = it.introduction,
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )
            }
        }
    }
}
