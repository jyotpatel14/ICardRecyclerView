package com.example.icardrecyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class InsertBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var firebaseReference: DatabaseReference
    private lateinit var addDataButton: Button

    private lateinit var name_et:EditText
    private lateinit var enroll_et:EditText
    private lateinit var department_spi:Spinner
    private lateinit var institute_spi:Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_insert_bottom_sheet, container, false)

        name_et = view.findViewById(R.id.nameins_et)
        enroll_et = view.findViewById(R.id.enrollins_et)
        department_spi = view.findViewById(R.id.department_spi)
        institute_spi = view.findViewById(R.id.institute_spi)

        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseReference = firebaseDatabase.getReference().child("CardData")
        addDataButton = view.findViewById(R.id.addCardData)

        val name:String = name_et.text.toString().trim()
        val enroll:String = enroll_et.text.toString().trim()
        val department:String = department_spi.selectedItem.toString()
        val institute:String = institute_spi.selectedItem.toString()

        val data = HashMap<String, String>()
        data["name"] = name
        data["enroll"] = enroll
        data["department"] = department
        data["institute"] = institute

        addDataButton.setOnClickListener{
            val key = firebaseReference.push().key

            data["key"] = key.toString()
            firebaseReference.child(key.toString()).setValue(data)
        }

        return view
    }

}