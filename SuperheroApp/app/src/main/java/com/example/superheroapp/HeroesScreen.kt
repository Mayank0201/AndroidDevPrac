package com.example.superheroapp


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superheroapp.data.Hero
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.ui.tooling.preview.Preview



@Composable
fun HeroList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
        LazyColumn(contentPadding = contentPadding,modifier=modifier) {
            itemsIndexed(heroes) { index, it ->
                HeroCard(
                    hero = it,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }





@Composable
fun HeroCard(modifier: Modifier = Modifier,hero: Hero) {
    val screen = LocalConfiguration.current
    val sHeight = screen.screenHeightDp

    Card(
        modifier = modifier
            .height((sHeight * 0.15).dp)
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,

        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f).clip(MaterialTheme.shapes.medium)
            ) {
                Text(text = stringResource(hero.name),
                    style = MaterialTheme.typography.displaySmall)

                Text(text = stringResource(hero.description),
                    style = MaterialTheme.typography.bodyLarge)
            }

            Image(
                painter = painterResource(hero.img),
                contentDescription = stringResource(hero.name),
                modifier = Modifier
                    .size(72.dp)
                    .clip(MaterialTheme.shapes.small)
            )
        }
    }
}