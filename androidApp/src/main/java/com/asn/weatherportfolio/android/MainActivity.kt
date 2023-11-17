package com.asn.weatherportfolio.android

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.asn.weatherportfolio.model.CurrentWeather
import com.asn.weatherportfolio.model.DailyWeather
import com.asn.weatherportfolio.ui.UiState
import com.asn.weatherportfolio.ui.WeatherCommonViewModel
import com.google.android.gms.location.LocationServices
import io.ktor.util.date.WeekDay
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.math.roundToInt

class MainActivity : FragmentActivity() {

    private val viewModel = WeatherCommonViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewState by viewModel.weatherStateFlow.collectAsState()
            requestLocationPermission(this)
            MyApplicationTheme {
                when (viewState) {
                    is UiState.Success -> {
                        with(viewState as UiState.Success) {
                            MainContent(this.currentWeather, this.forecastWeather)
                        }
                    }

                    is UiState.Loading -> {}
                    else -> {}
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            99 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupLocationClient()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @SuppressLint("MissingPermission")
    private fun setupLocationClient() {
        val locationClient = LocationServices.getFusedLocationProviderClient(this)

        lifecycleScope.launch {
            val location = locationClient.lastLocation
            location.addOnCompleteListener {
                viewModel.updateLocation(location.result.latitude, location.result.longitude)
            }
        }
    }
}

private fun requestLocationPermission(context: Activity) {
    requestPermissions(context, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 99)
}

@Composable
fun MainContent(currentWeather: CurrentWeather?, forecastWeather: List<DailyWeather>?) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        DynamicBackground()

        Column(modifier = Modifier.padding(20.dp)) {
            WeatherNow(currentWeather)
            ForecastWeather(forecastWeather)
        }
    }
}

@Composable
fun DynamicBackground() {
    Image(
        painter = painterResource(id = R.drawable.cc453037a0e4830036d74baf41717fc9),
        contentDescription = "City background",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun WeatherNow(currentWeather: CurrentWeather?) {
    Column {

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

            Box {

                TransparentBackground()

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
                    text = "${currentWeather?.currentTemp?.roundToInt()}ºC",
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
                    text = "Sensação térmica: ${currentWeather?.feelsLikeTemp?.roundToInt()}ºC",
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
fun TransparentBackground() {
    Image(
        painter = ColorPainter(Color.Black),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.3f)
    )
}

@Composable
fun ForecastWeather(forecastWeather: List<DailyWeather>?) {
    Column {
        Text(
            text = "Próximas previsões",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.saira)),
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
            ),
            modifier = Modifier.padding(12.dp)
        )

        val calendar = Calendar.getInstance()
        val ordinalDayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1

        for (i in 0..3) {
            val forecastDayOfTheWeek = (ordinalDayOfTheWeek + i) % 7
            forecastWeather?.get(i)?.let { ForecastDay(it, forecastDayOfTheWeek) }
        }
    }
}

@Composable
fun ForecastDay(dailyWeather: DailyWeather, dayReference: Int) {
    Row(
        modifier = Modifier.padding(vertical = 5.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.sun_icon),
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier
                .width(54.dp)
                .height(54.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Card(
            border = BorderStroke(2.dp, Color.White),
            shape = RoundedCornerShape(size = 10.dp),
            backgroundColor = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        ) {
            TransparentBackground()

            Box(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                ForecastDay(dayReference, Modifier.align(Alignment.CenterStart))
                ForecastMaximum(dailyWeather.tempMax.roundToInt(), Modifier.align(Alignment.Center))
                ForecastMinimum(
                    dailyWeather.tempMin.roundToInt(),
                    Modifier.align(Alignment.CenterEnd)
                )
            }
        }
    }
}

@Composable
fun ForecastDay(dayReference: Int, modifier: Modifier) {
    val dayOfWeekName = WeekDay.from(dayReference).name.subSequence(0..2).toString()
    Text(
        text = dayOfWeekName,
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.saira)),
            fontWeight = FontWeight(400),
            color = Color(0xFFFFFFFF),
        ),
        modifier = modifier
    )
}

@Composable
fun ForecastMaximum(max: Int, modifier: Modifier) {
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_narrow_up_icon),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .height(24.dp)
                .align(Alignment.CenterVertically)
        )

        Text(
            text = "${max}º",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.saira)),
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
            )
        )
    }
}

@Composable
fun ForecastMinimum(min: Int, modifier: Modifier) {
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_narrow_down_icon),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .height(24.dp)
                .align(Alignment.CenterVertically)
        )

        Text(
            text = "${min}º",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.saira)),
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
            )
        )
    }
}

//@Preview
//@Composable
//fun DefaultPreview() {
//    MyApplicationTheme {
//        MainContent(viewState)
//    }
//}
