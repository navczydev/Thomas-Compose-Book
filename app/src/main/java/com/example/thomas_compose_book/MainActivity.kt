package com.example.thomas_compose_book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thomas_compose_book.ui.theme.ThomasComposeBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThomasComposeBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ThomasComposeBookTheme {
        Greeting(
            "Android",
            modifier = Modifier.drawYellowCross(
                InspectorInfo().apply {
                    name = "Draw behind"
                    value = "background line"
                }
            )
        )
    }
}

fun Modifier.drawYellowCross(inspectorInfo: InspectorInfo) = then(
    object : DrawModifier {
        override fun ContentDrawScope.draw() {
            drawLine(
                color = Color.Yellow,
                start = Offset(0F, 0F),
                end = Offset(size.width - 1, size.height - 1),
                strokeWidth = 10F
            )
            drawLine(
                color = Color.Yellow,
                start = Offset(0F, size.height - 1),
                end = Offset(size.width - 1, 0F),
                strokeWidth = 10F
            )
            drawContent()
        }
    }
)

@Composable
fun OrderDemo() {
    var color by remember { mutableStateOf(Color.Blue) }
    Box(
        modifier = Modifier
            // .fillMaxSize()
            .padding(32.dp)
            .border(BorderStroke(width = 2.dp, color = color))
            .background(Color.LightGray)
            .clickable {
                color = if (color == Color.Blue) {
                    Color.Red
                } else {
                    Color.Blue
                }
            }
    )
}

@Preview(group = "sample-group")
@Composable
fun PreviewOrderBox() {
    MaterialTheme {
        OrderDemo()
    }
}

@Composable
fun ColorPicker(color: MutableState<Color>) {
    val red = color.value.red
    val green = color.value.green
    val blue = color.value.blue
    Column {
        Slider(
            value = red,
            onValueChange = {
                color.value = Color(
                    it,
                    green,
                    blue
                )
            }
        )
        Slider(
            value = green,
            onValueChange = { color.value = Color(red, it, blue) }
        )
        Slider(
            value = blue,
            onValueChange = { color.value = Color(red, green, it) }
        )
    }
}

@Preview(group = "sample-group")
@Composable
fun PreviewColorPicker() {
    MaterialTheme {
        ColorPicker(color = mutableStateOf(Color.Red))
    }
}

@Composable
@Preview
fun PredefinedLayoutsDemo() {
    val red = remember { mutableStateOf(true) }
    val green = remember { mutableStateOf(true) }
    val blue = remember { mutableStateOf(true) }
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            CheckboxWithLabel(
                label = "Red",
                state = red
            )
            CheckboxWithLabel(
                label = "Green",
                state = green
            )
            CheckboxWithLabel(
                label = "Blue",
                state = blue
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                if (red.value) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red)
                    )
                }
                if (green.value) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp)
                            .background(Color.Green)
                    )
                }
                if (blue.value) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(64.dp)
                            .background(Color.Blue)
                    )
                }
            }
        }
    }
}

@Composable
fun CheckboxWithLabel(label: String, state: MutableState<Boolean>) {
    MaterialTheme {
        Row(
            modifier = Modifier.clickable {
                state.value = !state.value
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = state.value,
                onCheckedChange = {
                    state.value = it
                }
            )
            Text(
                text = label,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
