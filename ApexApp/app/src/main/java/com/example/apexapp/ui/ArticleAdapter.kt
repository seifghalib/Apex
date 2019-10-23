package com.example.apexapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apexapp.R
import com.example.apexapp.apidata.Article

import com.squareup.picasso.Picasso

class ArticleAdapter(private val mContext: Context, private val mArticleList: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(url: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        val v = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false)
        return ArticleViewHolder(v)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = mArticleList[position]
        holder.onBind(article)
    }

    override fun getItemCount(): Int {
        return mArticleList.size
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mTextViewTitle: TextView = itemView.findViewById(R.id.text_view_title)
        private val mTextViewAuthor: TextView = itemView.findViewById(R.id.text_view_author)
        private val mImageView: ImageView = itemView.findViewById(R.id.image_view)
        private val mTextViewDescription: TextView = itemView.findViewById(R.id.text_view_description)

        init {
            itemView.setOnClickListener {
                mListener?.run {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val article = mArticleList[position]
                        onItemClick(article.url)
                    }
                }
            }
        }

        fun onBind(article: Article) {
            with(article) {
                mTextViewTitle.text = title
                mTextViewAuthor.text = author
                Picasso.get().load(urlToImage).fit().centerInside().into(mImageView)
                mTextViewDescription.text = description
            }
        }
    }
}
