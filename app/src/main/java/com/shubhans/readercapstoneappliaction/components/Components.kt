package com.shubhans.readercapstoneappliaction.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.shubhans.readercapstoneappliaction.navigation.ReaderScreen

@Composable
 fun ReaderLogo(modifier: Modifier = Modifier) {
    Text(
        text = "A Reader", style = MaterialTheme.typography.h3,
        modifier =modifier.padding(bottom = 16.dp),
        color = Color.Red.copy(alpha = 0.5f)
    )
}
@Composable
fun EmailInput(
    modifier: Modifier =Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(modifier =modifier,
        valueState = emailState,
        labelId =labelId,
        enabled =enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction)
}

@Composable
fun InputField(
    modifier: Modifier,
    valueState: MutableState<String>,
    labelId:String,
    enabled: Boolean,
    isSingleton: Boolean =true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default) {

    OutlinedTextField(value = valueState.value,
        onValueChange ={valueState.value =it},
        label = { Text(text = labelId) },
        singleLine = isSingleton,
        textStyle = TextStyle(fontSize = 18.sp, color =MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled =enabled,
        keyboardOptions = KeyboardOptions(keyboardType =keyboardType, imeAction = imeAction),
        keyboardActions = onAction)

}


@Composable
fun PasswordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    labelId: String, enabled: Boolean,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
    passwordVisibility: MutableState<Boolean>
) {
    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None
    else PasswordVisualTransformation()
    OutlinedTextField(value = passwordState.value,
        onValueChange = { passwordState.value = it },
        label = {Text(text = labelId) },
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        visualTransformation = visualTransformation,
        trailingIcon = { PasswordVisibility(passwordVisibility = passwordVisibility) },
        keyboardActions = onAction)

}


@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icons.Default.Close

    }

}
@Composable
fun TitleSection(modifier: Modifier = Modifier, label: String) {
    Surface(modifier = modifier.padding(start = 5.dp, top = 1.dp)) {
        Column {
            Text(
                text = label,
                fontSize = 19.sp,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Left
            )

        }

    }


}

@Composable
fun ReaderAppBar(
    title: String,
    icon: ImageVector? =null,
    showProfile: Boolean = true,
    navController: NavController,
    onBackArrowClicked:() -> Unit ={}
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically ) {
                if (showProfile) {
                    Icon(
                        imageVector = Icons.Default.Favorite, contentDescription = "logo icon",
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .scale(0.9f)
                    )

                }
                if(icon !=null){
                    Icon(imageVector = icon, contentDescription = "arrow back",
                        tint = Color.Red.copy(alpha = 0.7f),
                        modifier = Modifier.clickable { onBackArrowClicked.invoke() }
                    )
                }
                Spacer(modifier = Modifier.width(50.dp))
                Text(
                    text = title,
                    color = Color.Red.copy(alpha = 0.7f),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )

            }
        },
        actions = {
            IconButton(onClick = {
                FirebaseAuth.getInstance().signOut().run {
                    navController.navigate(ReaderScreen.LoginScreen.name)
                }
            }) {
                if(showProfile) Row (){
                    Icon(imageVector = Icons.Filled.ExitToApp,
                        contentDescription = "Logout")

            }else Box {}
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp)

}


@Composable
fun RoundedButton(
    label: String = "Reading",
    radius: Int = 30,
    onPress: () -> Unit = {}
) {
    Surface(modifier = Modifier.clip(RoundedCornerShape(
        bottomEndPercent = radius,
        topStartPercent = radius)),
        color = Color(0xFF92CBDF)) {

        Column(modifier = Modifier
            .width(90.dp)
            .heightIn(40.dp)
            .clickable { onPress.invoke() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = label, style = TextStyle(color = Color.White,
                fontSize = 15.sp),)
        }
    }
}


@Composable
fun FABContent(onTap: () -> Unit) {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color.Blue
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add a book",
            tint = Color.White
        )

    }
}
@Composable
fun BookRating(score: Double = 4.5) {
    Surface(modifier = Modifier
        .height(70.dp)
        .padding(4.dp),
        shape = RoundedCornerShape(56.dp),
        elevation = 6.dp,
        color = Color.White) {
        Column(modifier = Modifier.padding(4.dp)) {
            Icon(imageVector = Icons.Filled.Star, contentDescription = "Star",
                modifier = Modifier.padding(3.dp))
            Text(text = score.toString(), style = MaterialTheme.typography.subtitle1)

        }

    }

}
@Composable
fun ListCard(onPressDetails: (String) -> Unit = {}) {
    val context = LocalContext.current
    val resources = context.resources

    val displayMetrics = resources.displayMetrics

    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val spacing = 10.dp

    Card(shape = RoundedCornerShape(29.dp),
        backgroundColor = Color.White,
        elevation = 6.dp,
        modifier = Modifier
            .padding(16.dp)
            .height(242.dp)
            .width(202.dp)
            .clickable {}) {
        Column(modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
            horizontalAlignment = Alignment.Start) {
            Row(horizontalArrangement = Arrangement.Center) {

                Image(painter = rememberImagePainter({}),
                    contentDescription = "book image",
                    modifier = Modifier
                        .height(140.dp)
                        .width(100.dp)
                        .padding(4.dp))
                Spacer(modifier = Modifier.width(50.dp))

                Column(modifier = Modifier.padding(top = 25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Rounded.FavoriteBorder,
                        contentDescription = "Fav Icon",
                        modifier = Modifier.padding(bottom = 1.dp))

                    BookRating(score = 4.9)
                }

            }
            Text(text = "Title name", modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)

            Text(text ="auther name", modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.caption) }

//        val isStartedReading = remember {
//            mutableStateOf(false)
//        }

        Row(horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom) {
            // isStartedReading.value = book.startedReading != null


            RoundedButton(label = "Reading",
                radius = 70)

        }
    }

}


