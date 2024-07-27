package com.example.icardrecyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recycle:RecyclerView
    private lateinit var data : ArrayList<MyModel>
    private lateinit var insertBottomSheetFragment: InsertBottomSheetFragment

    private lateinit var floatingActionButton: FloatingActionButton

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

        data = ArrayList<MyModel>()

        data.add(MyModel("Patel Jyot Kalpesh","2305112110059","FITCS","PIET",R.drawable.profile))
        data.add(MyModel("Patel Jyot Kalpesh","2305112110059","FITCS","PIET",R.drawable.profile))
        data.add(MyModel("Patel Jyot Kalpesh","2305112110059","FITCS","PIET",R.drawable.profile))
        data.add(MyModel("Patel Jyot Kalpesh","2305112110059","FITCS","PIET",R.drawable.profile))
        data.add(MyModel("Patel Jyot Kalpesh","2305112110059","FITCS","PIET",R.drawable.profile))

        recycle.adapter =MyAdapter(applicationContext,data)
        recycle.layoutManager = LinearLayoutManager(this)

    }
}