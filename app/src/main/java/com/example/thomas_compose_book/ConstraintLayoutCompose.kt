package com.example.thomas_compose_book

import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun CheckboxWithLabelCons(
    label: String,
    state: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    //  ConstraintLayout
    /* (
         modifier = modifier.clickable {
             state.value = !state.value
         }
     )*/
    // {
    ConstraintLayout {
        val (checkbox, text) = createRefs()
        Checkbox(
            checked = state.value,
            onCheckedChange = {
                state.value = it
            }
            /*modifier = Modifier.constrainAs(checkbox) {
                start.linkTo(parent.start, margin = 24.dp)
            }*/
        )
        Text(
            text = label
            //  modifier = Modifier.constrainAs(text) {
            //  start.linkTo(checkbox.end)
            //     top.linkTo(checkbox.top)
            // bottom.linkTo(checkbox.bottom)
            // end.linkTo(parent.end, margin = 24.dp)
            //  }
        )
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        // Create references for the composables to constrain
        val (button, text) = createRefs()

        Button(
            onClick = { /* Do something */ },
            // Assign reference "button" to the Button composable
            // and constrain it to the top of the ConstraintLayout
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button")
        }

        // Assign reference "text" to the Text composable
        // and constrain it to the bottom of the Button composable
        Text(
            "Text",
            Modifier.constrainAs(text) {
                top.linkTo(button.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 22.dp)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCheckboxWithLabel() {
    val vats = remember {
        mutableStateOf(true)
    }
    CheckboxWithLabelCons(label = "Hello", state = vats)
}

@Preview
@Composable
fun PreviewButton() {
    ConstraintLayoutContent()
}
