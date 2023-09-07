package ru.kassi.onlinekassa.presentation.mainFragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.kassi.onlinekassa.data.News
import ru.kassi.onlinekassa.data.Point
import ru.kassi.onlinekassa.databinding.ItemNewsBinding
import ru.kassi.onlinekassa.databinding.ItemPointBinding

class NewsAdapter(val listener: (String)-> Unit): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var data: List<News> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)

        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = data[position]
        with(holder.binding){
            title.text = news.title
            desc.text = news.desc
            if (news.imageLink.isNotEmpty()) {
                image.visibility = View.VISIBLE
                image.load(news.imageLink)
            }
            root.setOnClickListener {
                listener.invoke(news.link)
            }
        }
    }

    class NewsViewHolder(val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root)
}