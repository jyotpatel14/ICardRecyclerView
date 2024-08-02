package com.example.icardrecyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class InsertBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var firebaseReference: DatabaseReference
    private lateinit var addDataButton: Button

    private lateinit var nameEt:EditText
    private lateinit var enrollEt:EditText
    private lateinit var departmentSpi:AutoCompleteTextView
    private lateinit var instituteSpi:AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_insert_bottom_sheet, container, false)

        nameEt = view.findViewById(R.id.nameins_et)
        enrollEt = view.findViewById(R.id.enrollins_et)
        departmentSpi = view.findViewById(R.id.department_spi)
        instituteSpi = view.findViewById(R.id.institute_spi)

        val departmentData : Array<String> = resources.getStringArray(R.array.department_spi_array)
        val departmentAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line, departmentData)
        departmentSpi = view.findViewById(R.id.department_spi)
        departmentSpi.threshold = 0
        departmentSpi.setAdapter(departmentAdapter)
        departmentSpi.setOnClickListener{
            departmentSpi.showDropDown()
        }

        val instituteData : Array<String> = resources.getStringArray(R.array.institute_spi_array)
        val instituteAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line, instituteData)
        instituteSpi = view.findViewById(R.id.institute_spi)
        instituteSpi.threshold = 0
        instituteSpi.setAdapter(instituteAdapter)
        instituteSpi.setOnClickListener{
            instituteSpi.showDropDown()
        }


        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseReference = firebaseDatabase.getReference().child("CardData")
        addDataButton = view.findViewById(R.id.addCardData)
        val key = firebaseReference.push().key


        addDataButton.setOnClickListener{

            val name:String = nameEt.text.toString()
            val enroll:String = enrollEt.text.toString()
            val department:String = departmentSpi.text.toString()
            val institute:String = instituteSpi.text.toString()

            val data = HashMap<String, String>()
            data["name"] = name
            data["enroll"] = enroll
            data["department"] = department
            data["institute"] = institute

            data["key"] = key.toString()
            firebaseReference.child(key.toString()).setValue(data)

        }

        return view
    }

}