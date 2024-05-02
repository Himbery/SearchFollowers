package com.test.searchfollowerstest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.searchfollowerstest.databinding.ItemRepoBinding
import java.text.SimpleDateFormat
import java.util.Date

class RepoAdapter (): RecyclerView.Adapter<RepoAdapter.ViewHolder>()  {

    var repoArrayList: ArrayList<ItemRepo> = ArrayList<ItemRepo>()
    var i = 0

    fun setRepoData(listOfRepos : List<ItemRepo>) {
        this.repoArrayList = listOfRepos as ArrayList<ItemRepo>
        notifyDataSetChanged()
    }

    class ViewHolder(val  binding: ItemRepoBinding)  : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {

        val dateString = repoArrayList[position].updated_at
        val currentFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date: Date = currentFormat.parse(dateString)
        val targetFormat = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = targetFormat.format(date)

        holder.binding.dateText.text = "Last commit: " + formattedDate
        holder.binding.nameRepo.text = "Name: " + repoArrayList[position].name
        holder.binding.branch.text = "default branch: " + repoArrayList[position].default_branch
        holder.binding.forks.text = "forks: " + repoArrayList[position].forks_count.toString()
        holder.binding.description.text = repoArrayList[position].description
        holder.binding.language.text = repoArrayList[position].language
        holder.binding.stars.text = repoArrayList[position].stargazers_count.toString()
    }

    override fun getItemCount(): Int {
        return repoArrayList.size
    }
}