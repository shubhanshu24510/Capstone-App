package com.shubhans.readercapstoneappliaction.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.shubhans.readercapstoneappliaction.components.FABContent
import com.shubhans.readercapstoneappliaction.components.ListCard
import com.shubhans.readercapstoneappliaction.components.ReaderAppBar
import com.shubhans.readercapstoneappliaction.components.TitleSection
import com.shubhans.readercapstoneappliaction.model.MBook
import com.shubhans.readercapstoneappliaction.navigation.ReaderScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = { ReaderAppBar(title = "Kindle Reader", navController = navController) },
        floatingActionButton = { FABContent {
            navController.navigate(ReaderScreen.SearchScreen.name)
        } })
    {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeContent(navController)

        }
    }
}

@Composable
fun HomeContent(navController: NavController) {
    val listOfBooks = listOf(
        MBook(id ="bella", title ="conscious mind" , authers = "carlo",notes = null),
        MBook(id = "bella", title ="read mind" , authers = "carlo",notes = null),
        MBook(id ="bella", title ="conscious mind" , authers = "carlo",notes = null),
        MBook(id ="bella", title ="picaso mind" , authers = "carlo",notes = null),
        MBook(id ="bella", title ="hvcdsbc mind" , authers = "carlo",notes = null),
        MBook(id ="bella", title ="cbdsdcsdc mind" , authers = "carlo",notes = null)
    )
    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty())
        FirebaseAuth.getInstance().currentUser?.email?.split("@")
            ?.get(0) else
        "N/A"
    Column(
        Modifier.padding(2.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Your reading \n " + " activity right now...")
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            Column {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ReaderScreen.ReaderStateScreen.name)
                        }
                        .size(45.dp),
                    tint = MaterialTheme.colors.secondaryVariant)
                Text(
                    text = currentUserName!!,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.overline,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                Divider()
            }
        }
        ReadingNowArea(books = listOf(), navController = navController)
        TitleSection(label = "Reading List")
        BookListArea(listOfBooks = listOfBooks, navController)



    }
}

    @Composable
    fun ReadingNowArea(books: List<MBook>, navController: NavController) {
        ListCard()
    }

@Composable
fun BookListArea(listOfBooks: List<MBook>,
                 navController: NavController) {
    HorizontalScrollableComponent(listOfBooks){}

}

@Composable
fun HorizontalScrollableComponent(listOfBooks: List<MBook>,onCardPressed:(String) -> Unit) {
    val scrollState = rememberScrollState()

    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(280.dp)
        .horizontalScroll(scrollState)) {
        for (book in listOfBooks){
            ListCard {
                onCardPressed(it)


            }
        }


    }

}

@Composable
fun FABContent(onTap: () -> Unit) {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color.Magenta


    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add a book",
            tint = Color.White
        )

    }

}
    

//aknsngh10051996@gmail.com