package com.example.lab7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DriverScreen(navController: NavController) {
    var location by remember {
        mutableStateOf("")
    }

    val races = remember {
        mutableStateListOf<String>()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // back button
        Button(
            onClick = {
                navController.navigate("home")
            },
            modifier = Modifier.testTag("home") // for testing
        ) {
            Text("Home")
        }

        // text field and button to add race locations
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                text = "FormulaZero Drivers",
                fontSize = 30.sp
                )
                TextField(
                    value = location,
                    onValueChange = {
                        location = it // update location to new value
                    },
                    label = {
                        Text("Driver Name")
                    },
                    modifier = Modifier.testTag("driverInput") // for testing
                )}

            Spacer(modifier = Modifier.height(8.dp))
            Column {
                Button(
                    onClick = {
                        if (location.isNotBlank()) {
                            races.add(location)
                            location = ""
                        }
                    },
                    modifier = Modifier.testTag("addDriverButton") // for testing
                ) {
                    Text("Add")
                }
                Button(
                    onClick = {
                        races.clear()
                        location = ""
                    },
                    modifier = Modifier.testTag("clearDrivers") // for testing
                ) {
                    Text("Clear")
                }
            }
        }

        // list to display races
        LazyColumn {
            items(races) { race ->
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = race,
                    fontSize = 24.sp
                )
            }
        }
    }
}