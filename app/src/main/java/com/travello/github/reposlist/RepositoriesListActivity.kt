package com.travello.github.reposlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.travello.github.R
import kotlinx.android.synthetic.main.activity_repos_list.*

class RepositoriesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos_list)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        reposListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
    }
}