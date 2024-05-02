package com.test.searchfollowerstest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {
    private var usersLiveData = MutableLiveData<List<User>>()
    private var reposLiveData = MutableLiveData<List<ItemRepo>>()
    fun getUsersByName(name: String) {
        RetrofitHelper.api.getUsers(name).enqueue(object : Callback<ItemList> {
            override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
                response.body()?.let { usersList->
                    usersLiveData.postValue(usersList.items)

                }
            }

            override fun onFailure(call: Call<ItemList>, t: Throwable) {
                Log.i("TAG" , t.message.toString())
            }

        })
    }

    fun getReposUsers(url: String) {
        RetrofitHelper.api.loadRepos(url).enqueue(object : Callback<List<ItemRepo>> {
            override fun onResponse(call: Call<List<ItemRepo>>, response: Response<List<ItemRepo>>) {
                response.body()?.let { reposList->
                    val repos: List<ItemRepo>? = response.body()
                    reposLiveData.postValue(reposList)
                }
            }
            override fun onFailure(call: Call<List<ItemRepo>>, t: Throwable) {
                Log.i("TAG" , t.message.toString())
            }
        })
    }

    fun observeLiveData() : LiveData<List<User>> {
        return usersLiveData
    }

    fun observeRepoLiveData() : LiveData<List<ItemRepo>> {
        return reposLiveData
    }

    fun loadFollowers(name: String) {
        RetrofitHelper.api.loadFollowers(name).enqueue(object : Callback<List<ItemFollowers>> {

            override fun onResponse(call: Call<List<ItemFollowers>>, response: Response<List<ItemFollowers>>) {
                val followers: List<ItemFollowers>? = response.body()
                val index = (followers!!.size).toInt()
            }

            override fun onFailure(call: Call<List<ItemFollowers>>, t: Throwable) {
                Log.i("TAG" , t.message.toString())
            }

        })
    }
}