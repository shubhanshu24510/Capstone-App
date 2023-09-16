package com.shubhans.readercapstoneappliaction.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.shubhans.readercapstoneappliaction.components.ReaderAppBar
import com.shubhans.readercapstoneappliaction.navigation.ReaderScreen
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController =NavController(LocalContext.current)) {
    Scaffold(topBar = {
        ReaderAppBar(title = "Search Book",
            icon = Icons.Default.ArrowBack,
            showProfile =false,
            navController = navController){
            navController.popBackStack()
           // navController.navigate(ReaderScreen.SearchScreen.name)
        }
    })
    {
        Surface {
            Column {

            }
        }

    }

}
@SuppressLint("RememberReturnType")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(modifier: Modifier =Modifier,
               loading:Boolean =false,
               hint:String ="Search",
               onSearch :(String) -> Unit={}){
    Column {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController =LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value){
            searchQueryState.value.trim().isNotEmpty()

        }

        
    }

}