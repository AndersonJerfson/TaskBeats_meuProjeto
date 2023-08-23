package com.comunidadedevspace.taskbeats.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.News

class NewsListAdapter(): ListAdapter<News, NewsViewHolder>(NewsListAdapter) {


    companion object: DiffUtil.ItemCallback<News>(){
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }


        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return  oldItem.title == newItem.title &&
                    oldItem.imgUrl == newItem.imgUrl
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)

        return NewsViewHolder(view)    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
val item: News = getItem(position)
        holder.bind(item)
    }

}

class NewsViewHolder(private  val view: View) : RecyclerView.ViewHolder(view) {
    val tvtitle = view.findViewById<TextView>(R.id.tv_news_title)
    val  imgnews = view.findViewById<ImageView>(R.id.img_news)

    fun bind(news: News){

        tvtitle.text = news.title
        imgnews.load(news.imgUrl)



    }
}