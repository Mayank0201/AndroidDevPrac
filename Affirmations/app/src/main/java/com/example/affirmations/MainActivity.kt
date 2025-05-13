package com.example.affirmations

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AffirmationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AffirmationApp()
                }
            }
        }
    }
}

@Composable
fun AffirmationApp(){
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection),
            ),
    ){
        AffirmationList(
            affirmationlist = Datasource().loadAffirmations(),

        )
    }
}

@Composable
fun AffirmationList( affirmationlist: List <Affirmation>,modifier: Modifier = Modifier) {
    LazyColumn(modifier=modifier) {
       items(affirmationlist){ affirmation ->
            AffirmationCard(affirmation=affirmation,
                modifier = Modifier.padding(8.dp))

        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation,modifier: Modifier=Modifier){
    Card(modifier=modifier){

        Column(modifier=modifier.fillMaxWidth()){

            Image(painter= painterResource(affirmation.imgId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxWidth())

            Text(text= stringResource(affirmation.stringId))

        }
        }

    }



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
// AffirmationsTheme {
//        AffirmationCard(Affirmation(R.string.affirmation1,R.drawable.image1))
//    }
//}