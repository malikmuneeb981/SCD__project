package com.example.scdproject

import Books
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_adminchooseactivity.*


class adminchooseactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminchooseactivity)

        btnaddbookchoose.setOnClickListener()
        {
            startActivity(Intent(this,addbook::class.java))
        }
        btnshowallbook.setOnClickListener()
        {
            startActivity(Intent(this,retrievebookdetails::class.java))
        }
        btnshowallusers.setOnClickListener()
        {
            startActivity(Intent(this,retrieveuserdetails::class.java))
        }
    }
}
