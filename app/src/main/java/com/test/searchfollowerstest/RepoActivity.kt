package com.test.searchfollowerstest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.searchfollowerstest.databinding.ListRepoScreenBinding

class RepoActivity : AppCompatActivity() {

    private lateinit var  binding: ListRepoScreenBinding
    private lateinit var viewModel: FollowersViewModel
    lateinit var repoAdapter: RepoAdapter
    var repos_url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListRepoScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        repos_url = intent.getStringExtra("repos_url")

        prepareRecyclerView()

        viewModel = ViewModelProvider(this)[FollowersViewModel::class.java]
        repos_url?.let { viewModel.getReposUsers(it) }
        viewModel.observeRepoLiveData().observe(this , Observer {  it ->
            repoAdapter.setRepoData(it)

        })
    }

    private fun prepareRecyclerView() {
        repoAdapter = RepoAdapter()
        binding.listRepo.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = repoAdapter
        }
    }
}