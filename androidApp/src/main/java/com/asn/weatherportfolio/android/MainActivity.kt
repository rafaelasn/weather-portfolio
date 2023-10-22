package com.asn.weatherportfolio.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        DinamicBackground()

        Column {
            WeatherNow()
            ForecastWeather()
        }
    }
}

@Composable
fun DinamicBackground() {
    Image(
        painter = painterResource(id = R.drawable.cc453037a0e4830036d74baf41717fc9),
        contentDescription = "City background",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun WeatherNow() {
    Column(
        modifier = Modifier.padding(12.dp)
    ) {

        Row(Modifier.padding(8.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.place_white_icon_24),
                tint = Color.White,
                contentDescription = "Place Icon"
            )

            Text(
                text = "Fulanópolis, Nárnia",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.saira)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                )
            )
        }

        Card(
            border = BorderStroke(2.dp, Color.White),
            shape = RoundedCornerShape(size = 10.dp),
            backgroundColor = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .height(281.dp)
        ) {
            val tempCardMarginStart = 16.dp

            Box(
                Modifier
                    .drawWithContent {
                        drawContent()
                    }
            ) {

                Image(
                    painter = ColorPainter(Color.Gray),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(10.dp)
                        .alpha(0.5f)
                )

                Text(
                    text = "14:11",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(tempCardMarginStart, 8.dp),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.saira)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                    )
                )

                Text(
                    text = "33ºC",
                    style = TextStyle(
                        fontSize = 100.sp,
                        fontFamily = FontFamily(Font(R.font.saira)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF)
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(tempCardMarginStart, 12.dp)
                )

                Text(
                    text = "Sensação térmica: 47ºC",
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(tempCardMarginStart, 24.dp),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.saira)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF)
                    )
                )
            }
        }
    }
}

@Composable
fun ForecastWeather() {
    Column {
        Text(
            text = "Próximas previsões",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.saira)),
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
            )
        )

        for (i in 1..4) {
            ForecastDay()
        }
    }

}

@Composable
fun ForecastDay() {
    Icon(
        painter = painterResource(id = R.drawable.sun_icon),
        tint = Color.White,
        contentDescription = null,
        modifier = Modifier
            .width(54.dp)
            .height(54.dp)
    )
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MainContent()
    }
}
