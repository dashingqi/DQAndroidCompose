package com.example.androiddevchallenge.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
                Scaffold(topBar = {
                    TopAppBar(title = {
                        Text(
                            text = "dog detail",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    })
                }) {

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