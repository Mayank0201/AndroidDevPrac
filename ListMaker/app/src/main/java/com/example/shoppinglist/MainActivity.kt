package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.ui.Items
//import androidx.compose.ui.tooling.preview.Preview
import com.example.shoppinglist.ui.theme.ShoppingListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(modifier: Modifier = Modifier) {


    var sItems by remember { mutableStateOf(listOf<Items>()) }
    var showDialog by remember {mutableStateOf(false)}

    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember {mutableStateOf("") }


    Column(modifier=modifier.fillMaxSize()){

        Button(onClick = {showDialog=true},
            modifier=modifier.align(Alignment.CenterHorizontally)){
            Text(text="Add Item")
        }

        LazyColumn(modifier=modifier
            .fillMaxSize()
            .padding(4.dp)){

                items(sItems) { item ->
                    if (item.isEditing) {
                        Editor(item=item,onEditClicked={
                            editedName,editedQuantity ->
                            sItems=sItems.map{it.copy(isEditing=false)
        //make copy of each item who is not being edited
                            }
                            val editedItem=sItems.find{it.id==item.id}
                            editedItem?.let{
                                it.name=editedName
                                it.quantity=editedQuantity
                                //changes where id same as item id
                            }
                        })

                    } else {
                        AppCard(modifier=modifier,item=item,
                            onEdit = {
                                sItems=sItems.map{it.copy(isEditing=it.id==item.id)//to check which id we are editing
                                }
                            //isEditing becomes true if id are equal
                            },
                            onDelete = {
                                sItems=sItems-item
                            }
                        )
                    }
                }
        }

        if(showDialog){

            AlertDialog(onDismissRequest = {showDialog=false}, confirmButton ={},
                title= { Text("Add shopping item") },
                text={
                    Column(modifier=modifier){
                        OutlinedTextField(
                            value=itemName,
                            onValueChange = {itemName=it},
                            placeholder = { Text(text="Enter your item:")},
                            modifier=modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value=itemQuantity,
                            onValueChange = {itemQuantity=it},
                            placeholder = { Text(text="Enter your item:")},
                            modifier=modifier.fillMaxWidth()
                        )

                        Row(modifier=modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween){
                            Button(
                                onClick = {
                                    if(itemName.isNotBlank() ){
                                        val newItem= Items(
                                        id= sItems.size+1, name=itemName, quantity=itemQuantity.toInt() )
                                        sItems=sItems+newItem
                                        showDialog=false
                                        itemName=""
                                        itemQuantity=""
                                    }
                                          },
                                ){
                                Text(
                                    text="Add"
                                )
                            }
                            Button(onClick={showDialog=false}){
                                Text(
                                    text="Exit"
                                )
                            }
                        }
                    }
                })
            }
        }
    }

@Composable
fun AppCard(modifier: Modifier=Modifier,item: Items,onEdit:()->Unit,onDelete:()->Unit){
    val screen= LocalConfiguration.current
    val sWidth=screen.screenWidthDp

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(4.dp, Color.Black, MaterialTheme.shapes.medium)
    ) {

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {

            Text(
                text = "Item: " + item.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier=modifier
                    .wrapContentSize()
                    .padding(6.dp)
                )

            Spacer(modifier = modifier.width((sWidth * 0.2).dp))

            Text(
                text = "Qty: " + (item.quantity).toString(),
                style = MaterialTheme.typography.bodyLarge,
                modifier=modifier
                    .wrapContentSize()
                    .padding(6.dp)
                )

            Spacer(modifier = modifier.width((sWidth * 0.2).dp))

            Row(modifier=modifier){
                IconButton(onEdit) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit button"
                    )
                }

                IconButton(onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Button"
                    )
                }

            }
        }
    }
}



@Composable
fun Editor(item:Items,modifier: Modifier=Modifier,onEditClicked:(String,Int)->Unit){
    var editedName by remember {mutableStateOf(item.name)}
    var editedQuantity by remember {mutableStateOf(item.quantity.toString())}
    var isEditing by remember{mutableStateOf(item.isEditing)}

    Row(modifier=modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround){
        Column{
            TextField(
                value=editedName,
                onValueChange = {editedName=it},
                singleLine = true,
                modifier=modifier
                    .wrapContentSize()
                    .padding(6.dp)
            )
            TextField(
                value=editedQuantity,
                onValueChange = {editedQuantity=it},
                singleLine = true,
                modifier=modifier
                    .wrapContentSize()
                    .padding(6.dp)
            )
        }

        Button(onClick={
            isEditing=false
            onEditClicked(editedName,editedQuantity.toIntOrNull()?:1)
        })
        {
            Text(text="Save")
        }




    }


}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ShoppingListTheme {
//        MainApp()
//    }
//}