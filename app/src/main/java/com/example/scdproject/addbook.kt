package com.example.scdproject

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_addbook.*

class addbook : AppCompatActivity() {
    var maxid:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addbook)
        sendingbookdetailstodatabase()
        btnaddbook.setOnClickListener()
        {

            val bookid = tilbookid3.text.toString()
            val bookname = tilbookname1.text.toString()
            val booklocation = tilbooklocation1.text.toString()
            // val Auth=FirebaseAuth.getInstance().currentUser!!.uid
            val dbref = FirebaseDatabase.getInstance().getReference().child("books")
                .child((maxid + 1).toString())
            val bookdetails1 = bookdetails(bookid, bookname, booklocation)
            dbref.setValue(bookdetails1).addOnSuccessListener()
            {
                val builder=AlertDialog.Builder(this)
                builder.setTitle("SUCCESS")
                builder.setMessage("BOOK HAS BEEN ADDED TO OUR DATABASE")
                builder.setPositiveButton("OK",{ dialogInterface: DialogInterface, i: Int -> startActivity(Intent(this,retrievebookdetails::class.java))})
                builder.show()
            }
        }
        btnviewallbooks.setOnClickListener()
        {
            startActivity(Intent(this,retrievebookdetails::class.java))
        }

    }

   fun sendingbookdetailstodatabase()
    {
        val dbref = FirebaseDatabase.getInstance().getReference().child("books")
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
    }
}
