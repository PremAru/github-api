package com.travello.github.reposlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.travello.github.R
import com.travello.github.model.Repository
import kotlinx.android.synthetic.main.adapter_repo.view.*
import javax.inject.Inject

class RepositoryAdapter @Inject constructor(private val presenter: RepositoriesListPresenter, private val picasso: Picasso): RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_repo, parent, false)
        return  RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return presenter.repositoryList?.count() ?: 0
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        presenter.getRepositorytPosistion(position)?.let { holder.bindFilter(it) }
    }

    inner class RepositoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindFilter(repository: Repository) {
            itemView.repoNameTextView.text = repository.name
            repository.description?.let {
                itemView.repoDescriptionTextView.text = it
            }
            itemView.repoUserNameTextView.text = repository.owner.userName
            itemView.repoStarCountTextView.text = repository.starCountDisplayName
            picasso.load(repository.owner.avatarUrl)
                .into(itemView.avatarImageView)
        }

    }

}