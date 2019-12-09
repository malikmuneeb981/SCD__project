package com.example.scdproject

import android.app.Activity
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_studentregister.*
import kotlinx.android.synthetic.main.activity_studentregister.cl
import java.util.*

class studentregister : AppCompatActivity() {
    var maxid:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studentregister)
        supportActionBar?.hide()
        var mAuth = FirebaseAuth.getInstance()
        sendingusersdetailstodatabase()
        btnsignup2.setOnClickListener()
        {
            val email: String = tilemail3.text.toString()
            val password: String = tilpassword3.text.toString()
            if (emailvalidation() && passwordvalidation() && namevalidation() && idvalidation()) {
                val dialog = ACProgressFlower.Builder(this).direction(
                    ACProgressConstant.DIRECT_CLOCKWISE
                )
                    .themeColor(Color.WHITE)
                    .text("Registering user please wait..")
                    .textSize(15)
                    .isTextExpandWidth(true)
                    .fadeColor(Color.DKGRAY).build()
                dialog.show()
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                            sendinginfotodatabase()
                            dialog.dismiss()
                            Snackbar.make(cl, "REGISTRATION SUCCESSFUL", Snackbar.LENGTH_LONG)
                                .show()
                        } else {
                            Snackbar.make(cl, "REGISTRATION FAILED", Snackbar.LENGTH_LONG).show()
                        }
                    })
            }
        }

        btnadminsignup.setOnClickListener()
        {
            val adminemail:String=tilemail3.text.toString()
            val adminpassword:String=tilemail3.text.toString()
            if (emailvalidation() && passwordvalidation() && namevalidation() && idvalidation())
            {
                val admindialog = ACProgressFlower.Builder(this).direction(
                    ACProgressConstant.DIRECT_CLOCKWISE
                )
                    .themeColor(Color.WHITE)
                    .text("Registering user please wait..")
                    .textSize(15)
                    .isTextExpandWidth(true)
                    .fadeColor(Color.DKGRAY).build()
                admindialog.show()
                mAuth.createUserWithEmailAndPassword(adminemail,adminpassword).addOnCompleteListener(this, OnCompleteListener<AuthResult>  { task ->
                    if (task.isSuccessful)
                    {
                        sendingadmininfotodatabase()
                        admindialog.dismiss()
                        Snackbar.make(cl, "REGISTRATION SUCCESSFUL", Snackbar.LENGTH_LONG)
                            .show()
                    }
                    else {
                        Snackbar.make(cl, "REGISTRATION FAILED", Snackbar.LENGTH_LONG).show()
                    }
                })

            }
        }
        btnlogin2.setOnClickListener()
        {
            val intent = Intent(this, loginactivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }


    }


    fun emailvalidation(): Boolean {
        val email: String = tilemail3.text.toString()
        if (TextUtils.isEmpty(email)) {
            tilemail2.error = "you cant leave this empty"
            return false
        } else {
            return true
        }
    }
    fun namevalidation():Boolean{
        val name: String = tilname3.text.toString()
        if (TextUtils.isEmpty(name)) {
            tilemail2.error = "you cant leave this empty"
            return false
        } else {
            return true
        }
    }
    fun passwordvalidation(): Boolean {
        val password: String = tilpassword3.text.toString()
        if (TextUtils.isEmpty(password)) {
            tilemail2.error = "you cant leave this empty"
            return false
        } else {
            return true
        }
    }
    fun idvalidation(): Boolean {
        val id: String = tilid3.text.toString()
        if (TextUtils.isEmpty(id)) {
            tilemail2.error = "you cant leave this empty"
            return false
        } else {
            return true
        }
    }

    fun sendinginfotodatabase() {
        val password: String = tilpassword3.text.toString()
        val email: String = tilemail3.text.toString()
        val name:String=tilname3.text.toString()
        val id: String = tilid3.text.toString()
        val databaseref = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        val userprofileinformation = userinfo(name,email,password,id)
        databaseref.setValue(userprofileinformation)
    }
    fun sendingadmininfotodatabase()
    {
        val adminname:String=tilname3.text.toString()
        val adminpassword:String=tilpassword3.text.toString()
        val adminemail: String = tilemail3.text.toString()
        val adminid: String = tilid3.text.toString()
        val admindatabaseref = FirebaseDatabase.getInstance().getReference().child("admin").child((maxid + 1).toString())
        val adminprofileinformation=adminlogininfo(adminemail,adminname,adminpassword,adminid)
        admindatabaseref.setValue(adminprofileinformation)
    }
    fun sendingusersdetailstodatabase()
    {
       /*val dbref = FirebaseDatabase.getInstance().getReference().child("users")
        val admindatabaseref = FirebaseDatabase.getInstance().getReference().child("admin").child((maxid + 1).toString())
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid = dataSnapshot.childrenCount
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        dbref.addValueEventListener(postListener)
        admindatabaseref.addValueEventListener(postListener)*/
    }
    fun notification(){
        var builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("REGISTRATION")
            .setContentText("You have registered to buitems library")
        val intent=Intent(this,studentregister::class.java)
        var pendingIntent=PendingIntent.getActivity(this,0, intent , PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)
        var notificationManager:NotificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0,builder.build())
    }

    }

