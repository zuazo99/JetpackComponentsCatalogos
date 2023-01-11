@file:OptIn(ExperimentalMaterialApi::class)

package com.gzuazo.jetpackcomponentscatalogos

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.gzuazo.jetpackcomponentscatalogos.ui.theme.JetpackComponentsCatalogosTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SliderPreview() {
    JetpackComponentsCatalogosTheme {
        MyRangeSlider()
    }
}

//@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyRangeSlider() {
    var currentRange by remember {
        mutableStateOf(0f..10f)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        RangeSlider(
            values = currentRange,
            onValueChange = { currentRange = it },
            valueRange = 0f..10f,
            steps = 9
        )
        Text(text = "Valor inferior ${currentRange.start}")
        Text(text = "Valor superior ${currentRange.endInclusive}")
    }
}

@Composable
fun AdvanceSlider() {
    var sliderPosition by rememberSaveable {
        mutableStateOf(0f)
    }
    var completeValue by rememberSaveable {
        mutableStateOf("")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            onValueChangeFinished = { completeValue = sliderPosition.toInt().toString() },
            valueRange = 0f..10f,
            steps = 9
        )
        Text(text = completeValue)
    }
}


@Composable
fun BasicSlider() {
    var sliderPosition by rememberSaveable {
        mutableStateOf(0f)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Slider(value = sliderPosition, onValueChange = { sliderPosition = it })
        Text(text = sliderPosition.toString())
    }

}