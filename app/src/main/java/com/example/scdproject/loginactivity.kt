package com.example.scdproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ProgressBar
import android.widget.Toast
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_loginactivity.*

class loginactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)
        supportActionBar?.hide()
            btnlogin1.setOnClickListener()
            {
                var mAuth1= FirebaseAuth.getInstance()
                val intent1= Intent(this,chooseactivity::class.java)
                intent1.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                val email1:String = tilemail4.text.toString()
                val password1:String = tilpassword4.text.toString()
                if (emailvalidation()&&passwordvalidation()&&(adminkeyvalidation()==false)) {
                    mAuth1.signInWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener(this, OnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val dialog= ACProgressFlower.Builder(this).direction(
                                    ACProgressConstant.DIRECT_CLOCKWISE)
                                    .themeColor(Color.WHITE)
                                    .text("loging in please wait..")
                                    .fadeColor(Color.DKGRAY).build()
                                dialog.show()

                               // Snackbar.make(cl1,"LOGIN SUCCESSFUL", Snackbar.LENGTH_LONG).show()
                                startActivity(intent1)
                            } else {
                                Toast.makeText(this, "incorrect password or email", Toast.LENGTH_SHORT).show()
                            }
                        })
                }
            }
        btnadminlogin.setOnClickListener()
        {
            var Auth=FirebaseAuth.getInstance()
            val intent=Intent(this,adminchooseactivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            val adminemail:String = tilemail4.text.toString()
            val adminpassword:String = tilpassword4.text.toString()
            if (emailvalidation()&&passwordvalidation()&&adminkeyvalidation())
            {
                Auth.signInWithEmailAndPassword(adminemail,adminpassword).addOnCompleteListener (this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val admindialog= ACProgressFlower.Builder(this).direction(
                            ACProgressConstant.DIRECT_CLOCKWISE)
                            .themeColor(Color.WHITE)
                            .text("loging in please wait..")
                            .fadeColor(Color.DKGRAY).build()
                        admindialog.show()

                        // Snackbar.make(cl1,"LOGIN SUCCESSFUL", Snackbar.LENGTH_LONG).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "incorrect password or email", Toast.LENGTH_SHORT).show()
                    }
                })
            }

        }
            btnsignup1.setOnClickListener()
            {
                intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
        fun emailvalidation(): Boolean {
            val email: String = tilemail4.text.toString()
            if (TextUtils.isEmpty(email)) {
                tilemail1.error = "you cant leave this empty"
                return false
            } else {
                return true
            }
        }

        fun passwordvalidation(): Boolean {
            val password: String = tilpassword4.text.toString()
            if (TextUtils.isEmpty(password)) {
                tilemail1.error = "you cant leave this empty"
                return false
            } else {
                return true
            }
        }
    fun adminkeyvalidation(): Boolean {
        val adminkey: String = tiladminkey4.text.toString()
        if (TextUtils.isEmpty(adminkey)) {
            tilemail1.error = "you cant leave this empty"
            return false
        } else if(adminkey=="qwerty") {
            return true
        }
        else
        {
            Toast.makeText(this,"you have entered the admin key wrong",Toast.LENGTH_SHORT).show()
            return false
        }
    }

    }

