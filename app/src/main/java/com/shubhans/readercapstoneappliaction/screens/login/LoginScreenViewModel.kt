package com.shubhans.readercapstoneappliaction.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.shubhans.readercapstoneappliaction.model.MUser
import kotlinx.coroutines.launch

class LoginScreenViewModel:ViewModel(){
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading


    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit)
    =viewModelScope.launch{
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("FB", "signInWithEmailAndPassword: Yayayay! ${task.result}")
                        //TODO("take to go home page")
                        home()
                    } else {
                        Log.d("Firebase", "sign in with email amd password:${task.result}"
                        )
                    }

                }
        } catch (ex: Exception) {
            Log.d("FIRE", "sign in with email and password:${ex.message}")
        }
    }
    fun createUserWithEmailAndPassword(email: String,password: String,home: () -> Unit) {
        if(loading.value ==false){
            _loading.value =true
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {task->
                    if(task.isSuccessful){
                        val displayName = task.result.user?.email?.split('@')?.get(0)
                        createUser(displayName)
                        home()
                    }else{
                        Log.d("FB","createUserWithEmailAndPassword:${task.result}")

                    }
                    _loading.value =false
                }
        }
    }

    private fun createUser(displayName: String?) {
        val userId =auth.currentUser?.uid

                val user =MUser(userId =userId.toString(),
                    displayName = displayName.toString(),
                    avatarUrl = "",
                    quote ="Focus Your Dream",
                    profession = "Android Developer",
                    id = null.toString()
                ).toMap()

        FirebaseFirestore.getInstance().collection("users").add(user)

    }
}


