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
    fun getUsersByName(name: String) {
        RetrofitHelper.api.getFollowers(name).enqueue(object : Callback<ItemList> {
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
    fun observeLiveData() : LiveData<List<User>> {
        return usersLiveData
    }
}