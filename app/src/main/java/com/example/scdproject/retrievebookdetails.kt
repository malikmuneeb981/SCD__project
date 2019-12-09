package com.example.scdproject

import Books
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_retrievebookdetails.*

class retrievebookdetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val dbreference = FirebaseDatabase.getInstance().getReference("books")
        val booksarraylist=ArrayList<Books?> ()
        val booksarrayadapter=
            ArrayAdapter<Books>(this,R.layout.bookinfoadapter,R.id.booksrow,booksarraylist)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrievebookdetails)
        val listView: ListView = findViewById(R.id.lvbooksinfo)
        listView.setAdapter(booksarrayadapter)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (DataSnapshot in dataSnapshot.children)
                {
                    val booksinfo=DataSnapshot.getValue(Books::class.java)
                    booksarraylist.add(booksinfo)

                }
                lvbooksinfo.adapter=booksarrayadapter


            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@retrievebookdetails,"cancelled",Toast.LENGTH_SHORT).show()
            }
        }
        dbreference.addValueEventListener(postListener)
    }

    }

