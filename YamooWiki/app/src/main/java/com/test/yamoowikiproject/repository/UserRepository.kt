package com.test.yamoowikiproject.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.test.yamoowikiproject.dataclassmodel.User

class UserRepository {
    companion object{
        private val database = FirebaseDatabase.getInstance()
        private val userDataRef = database.getReference("User")

        fun addUser(userClass: User, callback: (Task<Void>) -> Unit){
            userDataRef.push().setValue(userClass).addOnCompleteListener(callback)
        }
    }
}