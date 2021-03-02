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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
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
import com.example.androiddevchallenge.utils.DogDataUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Dog Home") })
                    }
                ) {
                    var dogData = DogDataUtil.getDogData()
                    dogData?.let { list ->
                        Box(modifier = Modifier.fillMaxSize()) {
                            LazyColumn(Modifier.fillMaxSize()) {
                                itemsIndexed(list) { position, item ->
                                    DogItem(position, item)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun DogItem(position: Int, dog: Dog) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(240.dp)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            elevation = 4.dp,
        ) {
            CardItem(dog)
        }
    }

    /**
     * pic
     * name
     */
    @Composable
    fun CardItem(dog: Dog) {
        var imageResource = ProjectApp.mApp.resources.getIdentifier(
            dog.avatarFileName,
            "drawable",
            ProjectApp.mApp.packageName
        )
        var dogImg = painterResource(imageResource)
        Image(
            painter = dogImg,
            contentDescription = dog.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(240.dp)
                .clip(shape = RoundedCornerShape(6.dp))
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0x99000000)
            ) {
                Text(
                    text = dog.name,
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
