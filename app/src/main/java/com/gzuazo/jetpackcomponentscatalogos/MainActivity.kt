package com.gzuazo.jetpackcomponentscatalogos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gzuazo.jetpackcomponentscatalogos.ui.theme.CheckInfo
import com.gzuazo.jetpackcomponentscatalogos.ui.theme.JetpackComponentsCatalogosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComponentsCatalogosTheme {
                // A surface container using the 'background' color from the theme
                var selected by rememberSaveable {
                    mutableStateOf("Gaizka")
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    val myOptions = getOptions(listOf("Aris", "Gaizka", "Maialen"))
//                    Column {
//                        MyTriStatusCheckBox()
//                        myOptions.forEach {
//                            MyCheckBoxWithTextCompleted(it)
//                        }
//
//                    }

                        //MyRadioButtonList(selected) { selected = it }
                        //MyCard()
                    Column() {
                        MyRangeSlider()

                    }
                }
            }
        }
    }
}


@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable {
            mutableStateOf(false)
        }
        CheckInfo(
            title = it,
            selected = status,
            onCheckedChange = { myNewStatus -> status = myNewStatus }
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JetpackComponentsCatalogosTheme {
        MyDropDownMenu()
    }
}

@Composable
fun MyDropDownMenu() {
    var selectedText by rememberSaveable {
        mutableStateOf("")
    }

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }
    val desserts = listOf("Helado", "Cafe", "Natillas", "Platanó", "Fruta")
    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach { dessert ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    selectedText = dessert
                }) {
                    Text(text = dessert)
                }
            }
        }
    }
}

@Composable
fun MyDivider() {
    Divider(Modifier.fillMaxWidth(), color = Color.Red)
}

@Composable
fun MyBadgeBox() {
    BadgedBox(
        badge = {
            Badge {
                Text(text = "8")
            }
        }, modifier = Modifier
            .padding(16.dp)
            .background(Color.Blue)
    ) {
        Icon(imageVector = Icons.Default.Star, contentDescription = "Star")
    }
}

@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 12.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color.Gray,
        contentColor = Color.Green,
        border = BorderStroke(5.dp, Color.Yellow)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = "Ejemplo 1")
            Text(text = "Ejemplo 1")
            Text(text = "Ejemplo 1")
        }
    }
}

@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {
//    var selected by rememberSaveable {
//        mutableStateOf("Gaizka")
//    }

    Column(Modifier.fillMaxWidth()) {
        Row {
            RadioButton(selected = name == "Gaizka", onClick = { onItemSelected("Gaizka") })
            Text(
                text = "Gaizka",
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
        }

        Row {
            RadioButton(selected = name == "Aris", onClick = { onItemSelected("Aris") })
            Text(
                text = "Aris",
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
        }

        Row {
            RadioButton(selected = name == "Jorge", onClick = { onItemSelected("Jorge") })
            Text(
                text = "Jorge",
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
        }

        Row {
            RadioButton(selected = name == "Galder", onClick = { onItemSelected("Galder") })
            Text(
                text = "Galder",
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun MyRadioButton() {
    Row(Modifier.fillMaxWidth()) {
        RadioButton(
            selected = false, onClick = { }, enabled = false, colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Yellow,
                disabledColor = Color.Green
            )
        )
        Text(text = "Ejemplo 1", modifier = Modifier.align(alignment = Alignment.CenterVertically))
    }
}

@Composable
fun MyTriStatusCheckBox() {
    var status by rememberSaveable {
        mutableStateOf(ToggleableState.Off)
    }

    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
}

@Composable
fun MyCheckBoxWithTextCompleted(checkInfo: CheckInfo) {
    Row(Modifier.padding(8.dp)) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = checkInfo.title,
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
    }
}

@Composable
fun MyCheckBoxWithText() {
    var state by rememberSaveable {
        mutableStateOf(false)
    }
    Row(Modifier.padding(8.dp)) {
        Checkbox(checked = state, onCheckedChange = { state = !state })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Ejemplo 1")
    }
}

@Composable
fun MyCheckBox() {
    var state by rememberSaveable {
        mutableStateOf(true)
    }

    Checkbox(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,
            uncheckedColor = Color.Yellow,
            checkmarkColor = Color.Blue
        )
    )
}

@Composable
fun MySwitch() {
    var state by rememberSaveable {
        mutableStateOf(true)
    }

    Switch(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = false,
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            uncheckedTrackColor = Color.Magenta,
            checkedThumbColor = Color.Green,
            checkedTrackColor = Color.Cyan,
            checkedTrackAlpha = 0.5f,
            uncheckedTrackAlpha = 0.3f,
            disabledCheckedTrackColor = Color.Yellow,
            disabledCheckedThumbColor = Color.Yellow,
            disabledUncheckedTrackColor = Color.Yellow,
            disabledUncheckedThumbColor = Color.Yellow
        )
    )
}

@Composable
fun MyProgressAdvance() {
    var progressStatus by rememberSaveable() {
        mutableStateOf(0f)
    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(progress = progressStatus)

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = { progressStatus += 0.1f }) {
                Text(text = "Incrementar")
            }

            Button(onClick = { progressStatus -= 0.1f }, Modifier.padding(start = 8.dp)) {
                Text(text = "Reducir")
            }
        }
    }
}

@Composable
fun MyProgress() {
    var showLoading by rememberSaveable() {
        mutableStateOf(false)
    }
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator(color = Color.Yellow)
            LinearProgressIndicator(
                Modifier.padding(top = 32.dp),
                color = Color.Red,
                backgroundColor = Color.Blue
            )
        }
        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Cargar Perfil")
        }
    }
}

@Composable
fun MyIcon() {
    Icon(imageVector = Icons.Rounded.Star, contentDescription = "Icono", tint = Color.Red)
}

@Composable
fun MyImageAdvance() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo",
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, Color.Red, CircleShape)
    )
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo",
        alpha = 0.5f
    )
}

@Composable
fun MyButtonExample() {
    var enabled by rememberSaveable {
        mutableStateOf(true)
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = { enabled = false },
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Magenta,
                contentColor = Color.Blue
            ),
            border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Hola")
        }

        OutlinedButton(
            onClick = { enabled = false },
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Magenta,
                contentColor = Color.Blue,
                disabledBackgroundColor = Color.Blue,
                disabledContentColor = Color.Cyan
            )
        ) {
            Text(text = "Hola")
        }

        TextButton(onClick = {}) {
            Text(text = "HOLA")
        }
    }
}

@Composable
fun MyTextFieldOutlined() {
    var myText by rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = myText, onValueChange = { myText = it },
        modifier = Modifier.padding(24.dp),
        label = { Text(text = "Escribe...") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Yellow,
            unfocusedBorderColor = Color.Green
        )
    )
}

@Composable
fun MyTextFieldAdvance() {
    var myText by rememberSaveable {
        mutableStateOf("")
    }
    TextField(value = myText,
        onValueChange = {
            myText = if (it.contains("a")) {
                it.replace("a", "b")
            } else {
                it
            }
        },
        label = {
            Text(text = "Introduce tu nombre")
        })
}

@Composable
fun MyTextField() {
    var myText by rememberSaveable {
        mutableStateOf("Gaizka")
    }
    Column(Modifier.fillMaxSize()) {
        TextField(value = myText, onValueChange = { myText = it })
        Text(text = "La palabra es: $myText")
    }

}

@Composable
fun MyText() {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Esto es un ejemplo")
        Text(text = "Esto es un ejemplo", color = Color.Blue)
        Text(text = "Esto es un ejemplo", color = Color.Cyan, fontWeight = FontWeight.ExtraBold)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.Light)
        Text(
            text = "Esto es un ejemplo",
            color = Color.Black,
            style = TextStyle(fontFamily = FontFamily.Cursive)
        )
        Text(
            text = "Esto es un ejemplo",
            color = Color.Black,
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )

        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.Underline,
                        TextDecoration.LineThrough
                    )
                )
            )
        )
        Text(text = "Esto es un ejemplo", fontSize = 30.sp)
    }
}




