package com.example.scdproject

import User
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
import kotlinx.android.synthetic.main.activity_retrieveuserdetails.*

class retrieveuserdetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val dbreference = FirebaseDatabase.getInstance().getReference("users")
        val usersarraylist=ArrayList<User?> ()
        val usersarrayadapter=
            ArrayAdapter<User>(this,R.layout.bookinfoadapter,R.id.booksrow,usersarraylist)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrieveuserdetails)
        val listView: ListView = findViewById(R.id.lvusersdetails)
        listView.setAdapter(usersarrayadapter)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (DataSnapshot in dataSnapshot.children)
                {
                    val booksinfo=DataSnapshot.getValue(User::class.java)
                    usersarraylist.add(booksinfo)

                }
                lvusersdetails.adapter=usersarrayadapter


            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@retrieveuserdetails,"cancelled", Toast.LENGTH_SHORT).show()
            }
        }
        dbreference.addValueEventListener(postListener)
    }
}
