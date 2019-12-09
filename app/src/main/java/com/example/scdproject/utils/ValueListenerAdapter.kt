package com.example.scdproject.utils

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ValueListenerAdapter(val handler:(DataSnapshot)->Unit):ValueEventListener {
    override fun onCancelled(data: DatabaseError) {

    }

    override fun onDataChange(data: DataSnapshot) {
        handler(data)
    }
}

