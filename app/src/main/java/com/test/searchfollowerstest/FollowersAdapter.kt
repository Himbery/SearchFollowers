package com.test.searchfollowerstest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.searchfollowerstest.databinding.ItemFollowerBinding

class FollowersAdapter (): RecyclerView.Adapter<FollowersAdapter.ViewHolder>()  {
    var selected_position: Int = -1
    var userArrayList: ArrayList<User> = ArrayList<User>()
    var followers_url: String = ""

    fun setUserData(listOfMeals : List<User>) {
        this.userArrayList = listOfMeals as ArrayList<User>
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
        followers_url = userArrayList[position].followers_url
        Glide.with(holder.itemView).load(userArrayList[position].avatar_url).into(holder.binding.logoUser)
        holder.binding.userName.text = userArrayList[position].login

    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }
}