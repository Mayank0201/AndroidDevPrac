package com.example.dice

import android.os.Bundle
import androidx.compose.material3.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dice.ui.theme.DiceTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceTheme {
                DiceApp()

                }

        }
    }
}

@Composable
fun DiceApp(){

    ButtonImg(modifier=Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)//ensures component centers both v and h
    )
}

@Composable
fun ButtonImg(modifier: Modifier = Modifier){

    var result by remember { mutableIntStateOf(1) }
    val img=when(result){
        1->R.drawable.dice_1
        2->R.drawable.dice_2
        3->R.drawable.dice_3
        4->R.drawable.dice_4
        5->R.drawable.dice_5
        else->R.drawable.dice_6
    }
    Column(
            modifier = modifier,
            horizontalAlignment = CenterHorizontally
        )
    {
        Image(painter = painterResource(img),
            contentDescription = stringResource(R.string.app_name))

        Spacer(modifier = Modifier.height(12.dp))


        Button(
            onClick = { result = (1..6).random() }){
            Text(stringResource(R.string.num))
        }


    }

}


@Preview(showBackground = true)
@Composable

fun DiceRollerApp() {
}

