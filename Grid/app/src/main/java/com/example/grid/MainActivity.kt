package com.example.grid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grid.data.DataSource
import com.example.grid.data.topics
import com.example.grid.data.topics2
import com.example.grid.ui.theme.GridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GridTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GridApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GridApp(modifier: Modifier = Modifier) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        color = MaterialTheme.colorScheme.background
    )
    {
        Row(modifier=modifier){
        GridList(dataList = topics,modifier=modifier)
        GridList(dataList = topics2,modifier=modifier)
    }
    }
}

@Composable
fun GridList(dataList:List<DataSource>, modifier: Modifier=Modifier){
    LazyColumn(modifier=modifier){

        items(dataList){values->
            GridCard(
                data=values,
            )

        }

    }
}

@Composable
fun GridCard(data:DataSource,modifier: Modifier=Modifier){

    val current= LocalConfiguration.current
    val sWidth=current.screenWidthDp

    Card (modifier=modifier.width((sWidth*0.45).dp)
        .padding(8.dp),
        shape= RoundedCornerShape(16.dp)){
        Row {
            Image(
                painter = painterResource(data.imgId),
                contentDescription = stringResource(data.stringId),
                modifier=modifier.width(68.dp)
                    .height(68.dp)
                    .aspectRatio(1f)
            )

            Column {
                Text(text = stringResource(data.stringId),
                    modifier=modifier.padding(top=16.dp,start=16.dp,end=16.dp,
                        bottom=8.dp),
                    style= MaterialTheme.typography.bodyMedium)

                Row{
                    Image(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = "Grains",
                        modifier=modifier.padding(start=16.dp,end=8.dp)
                    )
                    Text(text = data.id.toString(),
                        style= MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }


}



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//   GridTheme {
//    GridApp()
//  }
//}