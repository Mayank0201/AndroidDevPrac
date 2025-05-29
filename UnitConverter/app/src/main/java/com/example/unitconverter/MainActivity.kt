package com.example.unitconverter


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainApp(modifier: Modifier = Modifier) {

    var expanded1 by remember{ mutableStateOf(false) }
    var expanded2 by remember{ mutableStateOf(false) }

    var inputAmount by remember {mutableStateOf(" ")}
    var outputAmount by remember {mutableStateOf(" ")}

    var inputUnit by remember {mutableStateOf("Meters")}
    var outputUnit by remember {mutableStateOf("Meters")}
    var conversionFactorI by remember{mutableStateOf(1.00)}
    var conversionFactorO by remember{mutableStateOf(1.00)}

    fun convert(){
        val inputValueDouble=inputAmount.toDoubleOrNull() ?:0.0
        val result=((inputValueDouble*conversionFactorI*100.0)/conversionFactorO).roundToInt()/100.0
        outputAmount=result.toString()
    }


    Column(modifier=modifier
        .fillMaxSize()
        .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text(
            text = stringResource(R.string.app_name),
            style=MaterialTheme.typography.titleLarge
        )
        
        Spacer(modifier=modifier.height(6.dp))

        OutlinedTextField(
            value = inputAmount,
            onValueChange = { inputAmount = it
                convert()
            },
            label = { Text("Enter value") }
        )

        Row(modifier = modifier.padding(8.dp)) {

            Box {
                Button(
                    onClick = {
                        expanded1 = true
                    },
                    modifier = modifier
                ) {
                    Text(
                        text = inputUnit
                    )
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }

            DropdownMenu(
                expanded = expanded1,
                onDismissRequest = { expanded1 = false }) {
                DropdownMenuItem(
                    text = { Text("Meters") },
                    onClick = {
                        expanded1 = false
                        inputUnit = "Meters"
                        conversionFactorI = 1.0
                        convert()
                    })
                DropdownMenuItem(
                    text = { Text("Centimeters") },
                    onClick = {
                        expanded1 = false
                        inputUnit = "Centimeters"
                        conversionFactorI = 0.01
                        convert()
                    })

                DropdownMenuItem(
                    text = { Text("Feet") },
                    onClick = {
                        expanded1 = false
                        inputUnit = "Feet"
                        conversionFactorI = 0.3048
                        convert()
                    })
                DropdownMenuItem(
                    text = { Text("Millimeters") },
                    onClick = {
                        expanded1 = false
                        inputUnit = "Millimeters"
                        conversionFactorI = 0.0001
                        convert()
                    })
            }
                Box {

                    Button(
                        onClick = {
                            expanded2 = true
                        },
                        modifier = modifier
                    ) {
                        Text(
                            text = outputUnit
                        )
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = null
                        )
                    }
                    DropdownMenu(
                        expanded = expanded2,
                        onDismissRequest = { expanded2 = false }) {
                        DropdownMenuItem(
                            text = { Text("Meters") },
                            onClick = {
                                expanded2 = false
                                outputUnit = "Meters"
                                conversionFactorO=1.00
                                convert()
                            })

                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {
                                expanded2 = false
                                outputUnit = "Feet"
                                conversionFactorO=0.3048
                                convert()
                            })
                        DropdownMenuItem(
                            text = { Text("Centimeters") },
                            onClick = {
                                expanded2 = false
                                outputUnit = "Centimeters"
                                conversionFactorO=0.01
                                convert()
                            })
                        DropdownMenuItem(
                            text = { Text("Millimeters") },
                            onClick = {
                                expanded2 = false
                                outputUnit = "Millimeters"
                                conversionFactorO=0.0001
                                convert()
                            })
                    }
                }


            }

            Text(
                text = "Result: $outputAmount $outputUnit",
                style=MaterialTheme.typography.displaySmall
            )

        }
    }



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    UnitConverterTheme {
//        Greeting("Android")
//    }
//}