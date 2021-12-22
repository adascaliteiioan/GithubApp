package com.example.githubapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.data.db.GitRepo
import com.example.githubapp.databinding.ItemRepoBinding

class ReposAdapter : ListAdapter<GitRepo, ReposAdapter.GitVH>(ReposComparator()) {

    class GitVH(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: GitRepo) {
            with(binding) {
                Glide.with(ivAvatar)
                    .load(repo.ownerUrl)
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .into(ivAvatar)
                tvTitle.text = repo.name
                tvRepoUrl.text = repo.url
                tvDescription.text = repo.description
                ccForks.apply {
                    tvDetails.text = repo.forks.toString()
                    ivPicture.setImageResource(R.drawable.ic_fork)
                }
                ccStars.apply {
                    ivPicture.setImageResource(R.drawable.ic_star)
                    tvDetails.text = repo.stars.toString()
                }
                ccSubscribers.apply {
                    ivPicture.setImageResource(R.drawable.ic_subscriber)
                    tvDetails.text = repo.subscribers.toString()
                }
                ccWatchers.apply {
                    ivPicture.setImageResource(R.drawable.ic_watchers)
                    tvDetails.text = repo.watchers.toString()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitVH = GitVH(
        ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: GitVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class ReposComparator : DiffUtil.ItemCallback<GitRepo>() {
    override fun areItemsTheSame(oldItem: GitRepo, newItem: GitRepo) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: GitRepo, newItem: GitRepo) = oldItem == newItem
}