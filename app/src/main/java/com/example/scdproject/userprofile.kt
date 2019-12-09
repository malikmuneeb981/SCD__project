package com.example.scdproject

import User
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scdproject.utils.ValueListenerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_userprofile.*

class userprofile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var mUser: User
        lateinit var mAuth: FirebaseAuth
        lateinit var mDatabase: DatabaseReference
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userprofile)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference

        fun currentUserReference(): DatabaseReference =
            mDatabase.child("users").child(mAuth.currentUser!!.uid)

        currentUserReference().addListenerForSingleValueEvent(
            ValueListenerAdapter{
                mUser = it.asUser()!!
                tvname.setText(mUser.username)
                tvname1.setText(mUser.username)
                tvemail.setText(mUser.useremail)
                tvid.setText(mUser.userid)
                tvpassword.setText(mUser.userpassword)
            }
        )

    }
}
