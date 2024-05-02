package com.test.searchfollowerstest


import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.searchfollowerstest.databinding.MainScreenBinding


class MainScreenActivity : AppCompatActivity() {

    lateinit var editext: EditText
    lateinit var userAdapter: UsersAdapter

    private lateinit var  binding: MainScreenBinding
    private lateinit var viewModel: FollowersViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editext = findViewById(R.id.edit_followers)
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[FollowersViewModel::class.java]

        binding.searchBtn.setOnClickListener {
            if (editext.text.length > 0) {

                viewModel.getUsersByName(editext.text.toString())
                viewModel.observeLiveData().observe(this , Observer {  it ->
                    userAdapter.setUserData(it)

//                    for (user in it) {
//                        viewModel.loadFollowers(user.followers_url)
//
//                    }
                })
            }
        }

        userAdapter.setOnUserClickListener(object  : UsersAdapter.SetOnUserClickListener{
            override fun setOnClickListener() {
                val intent = Intent(applicationContext, RepoActivity::class.java)
                startActivity(intent)
            }
        })
    }
    private fun prepareRecyclerView() {
        userAdapter = UsersAdapter()
        binding.listFollowers.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = userAdapter
        }
    }
}