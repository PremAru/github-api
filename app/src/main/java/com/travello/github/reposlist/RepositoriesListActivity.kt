package com.travello.github.reposlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.travello.github.R
import com.travello.github.RepositoriesApplication
import kotlinx.android.synthetic.main.activity_repos_list.*
import javax.inject.Inject

class RepositoriesListActivity : AppCompatActivity(), RepositoriesListView {

    @Inject
    lateinit var repositoryAdapter: RepositoryAdapter

    @Inject
    lateinit var presenter: RepositoriesListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as RepositoriesApplication).appComponent.repositoriesListComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos_list)
        setRecyclerView()
        setRecyclerViewScrollListener()
        presenter.setView(this)
        getRepositories()
    }

    private fun setRecyclerViewScrollListener() {
        val layoutManager = reposListRecyclerView.layoutManager as LinearLayoutManager
        reposListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                presenter.scrolled(layoutManager.findLastVisibleItemPosition(), layoutManager.childCount, layoutManager.itemCount)
            }
        })
    }

    private fun setRecyclerView() {
        reposListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repositoryAdapter
        }
    }

    private fun getRepositories() {
        presenter.getRepositories()
    }

    override fun updateAdapter() {
        repositoryAdapter.notifyDataSetChanged()
    }

    override fun displayError(errorMessage: String) {
        var alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle(getString(R.string.error_title))
        alertBuilder.setMessage(errorMessage)
        alertBuilder.setPositiveButton(getString(R.string.ok_button), null)
        alertBuilder.show()
    }

    override fun displayLoadingIndicator() {
        progressBar.show()
    }

    override fun hideLoadingIndicator() {
        progressBar.hide()
     }
}