package com.example.scdproject

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chooseactivity.*

class chooseactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chooseactivity)
        /*getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
        getSupportActionBar()!!.setLogo(R.drawable.logoblackbackground)
        getSupportActionBar()!!.setDisplayUseLogoEnabled(true)*/
        val uid=FirebaseAuth.getInstance().uid
        if(uid==null)
        {
            val intent=Intent(this,studentregister::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        btnissuechoose.setOnClickListener()
        {
            startActivity(Intent(this,choosebook::class.java))
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionchoose,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId)
        {
            R.id.btnsignout->{
                val builder=AlertDialog.Builder(this)
                builder.setTitle("ARE YOU SURE?")
                builder.setMessage("YOU WANT TO SIGNOUT?")
                builder.setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int -> finish()
                    FirebaseAuth.getInstance().signOut()
                   val intent=Intent(this,studentregister::class.java )
                    intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                })
                builder.setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int -> })
                builder.show()
            }
            R.id.btnuserprofile->{
                val intent=Intent(this,userprofile::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val builder=AlertDialog.Builder(this)
        builder.setTitle("ARE YOU SURE?")
        builder.setMessage("YOU WANT TO EXIT THIS APP?")
        builder.setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int -> finish() })
        builder.setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int -> })
        builder.show()
    }
}
