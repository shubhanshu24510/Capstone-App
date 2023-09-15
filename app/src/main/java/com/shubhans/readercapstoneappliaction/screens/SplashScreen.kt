package com.shubhans.readercapstoneappliaction.screens

import android.annotation.SuppressLint
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shubhans.readercapstoneappliaction.components.ReaderLogo
import com.shubhans.readercapstoneappliaction.navigation.ReaderScreen
import kotlinx.coroutines.delay

@SuppressLint("RememberReturnType")
@Composable
fun SplashScreen(navController: NavHostController) {
    val scale = remember {
        Animatable(0f)
    }
  LaunchedEffect(key1 = true ){
      scale.animateTo(0.9f, animationSpec = tween(durationMillis = 900,
          easing = {
              OvershootInterpolator(8f)
                  .getInterpolation(it)
          }))
      delay(2000L)
//      if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
//          navController.navigate(ReaderScreen.LoginScreen.name)
//      }else{
//          navController.navigate(ReaderScreen.HomeScreen.name)
//      }
      navController.navigate(ReaderScreen.LoginScreen.name)

  }
    Surface(modifier = Modifier
        .padding(15.dp)
        .size(330.dp)
        .scale(scale.value),
        shape = CircleShape,
        border = BorderStroke(width = 2.dp,
        color = Color.Gray)) {
        Column(modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            ReaderLogo()
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Read Change Yourself",
                style = MaterialTheme.typography.h5,
                color = Color.Gray)
               
        }

    }
}


