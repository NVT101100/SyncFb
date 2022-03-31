package com.nvt.syncfb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var database = Firebase.database("https://asyncfb-345816-default-rtdb.asia-southeast1.firebasedatabase.app").reference
    var i : Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btn)
        val number = findViewById<TextView>(R.id.number)
        btn.setOnClickListener {
            i++;
            database.child("number/test").setValue(i.toString())
        }
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val post = dataSnapshot.getValue<String>()
                number.text = post
                Log.d("Received from firebase",post.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        database.child("number/test").addValueEventListener(postListener)
    }
}