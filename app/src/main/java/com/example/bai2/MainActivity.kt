package com.example.bai2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>
    private lateinit var recyclerView : RecyclerView
    private lateinit var searchView : SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        // Initialize the list of students
        studentList = listOf(
            Student("A", "12345"),
            Student("B", "23456"),
            Student("C", "34567"),
            Student("D", "45678"),
            Student("E", "56789")
            // Add more students as needed
        )

        // Set up the RecyclerView
        studentAdapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // Set up the SearchView listener
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String?) {
        if (!query.isNullOrEmpty() && query.length > 2) {
            val filteredList = studentList.filter {
                it.name.contains(query, ignoreCase = true) || it.studentId.contains(query)
            }
            studentAdapter.updateList(filteredList)
        } else {
            studentAdapter.updateList(studentList) // Show the full list if query is empty or less than 3 characters
        }
    }
}