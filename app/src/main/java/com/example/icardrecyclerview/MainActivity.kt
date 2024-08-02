package com.example.icardrecyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var recycle:RecyclerView
    private lateinit var data : ArrayList<MyModel>

    private lateinit var insertBottomSheetFragment: InsertBottomSheetFragment

    private lateinit var floatingActionButton: FloatingActionButton

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recycle = findViewById(R.id.recycle_rv)
        floatingActionButton = findViewById(R.id.floatingActionButton)

        floatingActionButton.setOnClickListener{
            insertBottomSheetFragment = InsertBottomSheetFragment()
            insertBottomSheetFragment.show(supportFragmentManager,insertBottomSheetFragment.tag)
        }


        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference().child("CardData")

        data = ArrayList<MyModel>()


        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                data.clear()
                for (user in snapshot.children){
                    if (user.exists()){
                        data.add(
                            MyModel(
                                user.child("name").value.toString(),
                                user.child("enroll").value.toString(),
                                user.child("department").value.toString(),
                                user.child("institute").value.toString(),
//                                user.child("profile").value as Int
                                R.drawable.logo
                            )
                        )
                    }

                }
                recycle.adapter = MyAdapter(applicationContext,data)
                recycle.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


    }
}