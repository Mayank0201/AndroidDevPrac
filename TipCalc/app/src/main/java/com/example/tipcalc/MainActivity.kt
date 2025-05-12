
package com.example.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalc.ui.theme.TipCalcTheme
import java.text.NumberFormat
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Switch
import androidx.annotation.StringRes
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import org.jetbrains.annotations.VisibleForTesting

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TipCalcTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TipTimeLayout()
                }
            }
        }
    }
}

@Composable
fun TipTimeLayout() {
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0

    var tipInput by remember{mutableStateOf("")}
    val tipAmount=tipInput.toDoubleOrNull()?:0.0

    var roundUp by remember {mutableStateOf(false)}

    val tip = calculateTip(amount,tipAmount,roundUp)

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )

        EditNumberField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label=R.string.bill_amount,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType= KeyboardType.Number,
                imeAction = ImeAction.Next)
        )

        EditNumberField(
            value = tipInput,
            onValueChange = { tipInput = it },
            label=R.string.how_was_the_service,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType= KeyboardType.Number,
                imeAction = ImeAction.Done)
        )

        RoundTheTipRow(roundUp = roundUp, onRoundUpChanged = { roundUp = it },
            modifier = Modifier.padding(bottom = 20.dp))

        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
    }
        //Spacer(modifier = Modifier.height(100.dp))
    }


@Composable
fun EditNumberField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
                    value: String,
                    onValueChange: (String) -> Unit,
                    modifier: Modifier = Modifier
) {

    TextField(
        value = value,
        onValueChange =  onValueChange ,
        singleLine = true,
        label = { Text(stringResource(label)) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions)

//makes sure that the others use default and make changes only to the ones needed to be changed




}


@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    Row(modifier=Modifier.
    fillMaxWidth(),
        verticalAlignment=Alignment.CenterVertically
     )
     {
        Text(text=stringResource(R.string.round_up_tip))
        Switch(modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End),//to align the Switch composable to the end of the screen
            checked = roundUp,
            onCheckedChange = onRoundUpChanged)
            //checked
            //whether the switch is checked. This is the state of the Switch composable.

            //onCheckedChange
            //the callback to be called when the switch is clicked.

    }
}
//is private but made internal for the unit test to use
@VisibleForTesting
internal fun calculateTip(amount: Double, tipPercent: Double,roundUp:Boolean): String {
    var tip = tipPercent / 100 * amount
    if(roundUp){
        tip=kotlin.math.ceil(tip)
        return NumberFormat.getCurrencyInstance().format(tip)
    }
    else {
        return NumberFormat.getCurrencyInstance().format(tip)
    }// might print ₹15.00 (in India) or $15.00 (in US) depends on device's locale settings
}

@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    TipCalcTheme {
        TipTimeLayout()
    }
}