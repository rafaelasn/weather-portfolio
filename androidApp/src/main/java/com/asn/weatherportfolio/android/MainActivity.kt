package com.asn.weatherportfolio.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(12.dp)
                ) {
                    weatherNow()
                }
            }
        }
    }
}

@Composable
fun weatherNow() {
    Column {
        Text(
            text = "Fulanópolis, Nárnia",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.saira)),
                fontWeight = FontWeight(700),
                //color = Color(0xFFFFFFFF),
            )
        )
        Card(
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .height(280.dp)
                .fillMaxWidth()
        ) {
            ConstraintLayout {
                val (timerText, tempText, feelsText) = createRefs()

                Text(
                    text = "14:11",
                    modifier = Modifier.constrainAs(timerText) {
                        top.linkTo(parent.top, margin = 4.dp)
                        start.linkTo(parent.start, margin = 4.dp)
                    },
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.saira)),
                        fontWeight = FontWeight(400),
                        //color = Color(0xFFFFFFFF),
                        )
                )

                Text(text = "33ºC",
                    style = TextStyle(
                        fontSize = 100.sp,
                        fontFamily = FontFamily(Font(R.font.saira)),
                        fontWeight = FontWeight(600)
                        //color = Color(0xFFFFFFFF),
                    ),
                    modifier = Modifier.constrainAs(tempText) {
                        bottom.linkTo(feelsText.top)
                        start.linkTo(parent.start, margin = 4.dp)
                    })

                Text(text = "Sensação térmica: 47ºC",
                    modifier = Modifier.constrainAs(feelsText) {
                        bottom.linkTo(parent.bottom, margin = 4.dp)
                        start.linkTo(parent.start, margin = 4.dp)
                    },
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.saira)),
                        fontWeight = FontWeight(400),
                        //color = Color(0xFFFFFFFF),
                        )
                )
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        weatherNow()
    }
}
