package com.example.tripintokyo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tripintokyo.data.DataSource
import com.example.tripintokyo.data.DataSource.tripJapan
import com.example.tripintokyo.model.JapanPicture
import com.example.tripintokyo.ui.theme.TripInTokyoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TripInTokyoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TripInTokyoApp()
                }
            }
        }
    }
}

@Composable
fun TripInTokyoApp() {
    Scaffold(
        topBar = {
            TripInTokyoTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(tripJapan.size) { tripJapan ->
                TripInTokyoCard(
                    tripJapan = DataSource.tripJapan[tripJapan],
                    modifier = Modifier
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripInTokyoTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 25.sp,
                modifier = modifier.padding(top = 10.dp)
            )
        }
    )
}

@Composable
fun TripInTokyoCard(
    tripJapan: JapanPicture,
    modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        Modifier.padding(16.dp)
            .clickable { expanded = !expanded }
    ) {
        Column(
            Modifier.padding(top = 16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DayCard(tripJapan.day)
            Spacer(Modifier.height(8.dp))
            TripImage(tripJapan.imageResourceId)
        }
        Spacer(Modifier.height(8.dp))
        if (expanded) {
            TripDescription(tripJapan.stringResources)
        }
    }
}


@Composable
fun DayCard(
    @StringRes day: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(day),
        modifier = modifier
    )
}

@Composable
fun TripImage(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentScale = ContentScale.FillWidth
    )
}



@Composable
fun TripDescription(
    @StringRes description: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(description),
        modifier = modifier.padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun TripInTokyoAppPreview() {
    TripInTokyoTheme {
        TripInTokyoApp()
    }
}