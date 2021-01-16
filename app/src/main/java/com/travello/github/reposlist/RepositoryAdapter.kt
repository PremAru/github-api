package com.travello.github.reposlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.travello.github.R

class RepositoryAdapter constructor(private val presenter: RepositoriesListPresenter): RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_repo, parent, false)
        return  RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class RepositoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

}