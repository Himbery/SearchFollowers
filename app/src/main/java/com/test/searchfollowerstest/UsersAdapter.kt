package com.test.searchfollowerstest

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.searchfollowerstest.databinding.ItemFollowerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersAdapter (): RecyclerView.Adapter<UsersAdapter.ViewHolder>()  {
    var selected_position: Int = -1
    var userArrayList: ArrayList<User> = ArrayList<User>()
    var repos_url: String = ""
    lateinit var setOnUserClickListener : SetOnUserClickListener
    var i = 0

    interface SetOnUserClickListener {
        fun setOnClickListener()
    }
    fun setOnUserClickListener(setOnUserClickListener: SetOnUserClickListener){
        this.setOnUserClickListener = setOnUserClickListener
    }

    fun setUserData(listOfUsers : List<User>) {
        this.userArrayList = listOfUsers as ArrayList<User>
//        for (user in userArrayList) {
//            loadFollowers(user.followers_url)
//        }
        notifyDataSetChanged()
    }

    class ViewHolder(val  binding: ItemFollowerBinding)  : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFollowerBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
//        followers_url = userArrayList[position].followers_url
//        val deferredResult: Deferred<Int> = CoroutineScope(Dispatchers.IO).async {
//            loadFollowers(followers_url)
//            return@async i
//        }

        holder.binding.followers.text = i.toString() + " followers"
        Glide.with(holder.itemView).load(userArrayList[position].avatar_url).into(holder.binding.logoUser)
        holder.binding.userName.text = userArrayList[position].login

        repos_url = userArrayList[position].repos_url

        holder.itemView.setOnClickListener {
            val c = holder.itemView.context
            val intent = Intent(c, RepoActivity::class.java)
            intent.putExtra("repos_url", repos_url)
            c.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }

   fun loadFollowers(url : String) {
       RetrofitHelper.api.loadFollowers(url).enqueue(object : Callback<List<ItemFollowers>> {
           override fun onResponse(call: Call<List<ItemFollowers>>, response: Response<List<ItemFollowers>>) {
               val followers: List<ItemFollowers>? = response.body()
               i = (followers!!.size).toInt()
           }

           override fun onFailure(call: Call<List<ItemFollowers>>, t: Throwable) {
               Log.i("TAG" , t.message.toString())
           }

       })
    }
}