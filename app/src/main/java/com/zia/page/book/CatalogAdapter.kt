package com.zia.page.book

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zia.bookdownloader.R
import com.zia.bookdownloader.lib.bean.Catalog
import kotlinx.android.synthetic.main.item_catalog.view.*
import java.util.*


/**
 * Created by zia on 2018/11/1.
 */
class CatalogAdapter(private val catalogSelectListener: CatalogSelectListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    public var catalogs: ArrayList<Catalog>? = null
    public var history = 0

    fun freshCatalogs(catalogs: ArrayList<Catalog>, history: Int) {
        this.catalogs = catalogs
        this.history = history
        Log.e("CatalogAdapter", "history:$history")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_catalog, p0, false)
        return CatalogHolder(view)
    }

    override fun getItemCount(): Int {
        return if (catalogs == null) 0
        else {
            catalogs!!.size
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CatalogHolder -> {
                val catalog = catalogs!![position]
                holder.itemView.item_catalog_name.text = catalog.chapterName
                holder.itemView.setOnClickListener { catalogSelectListener.onCatalogSelect(holder.itemView, position) }
                if (position == history) {
                    holder.itemView.item_catalog_mark.visibility = View.VISIBLE
                } else {
                    holder.itemView.item_catalog_mark.visibility = View.INVISIBLE
                }
            }
        }
    }

    class CatalogHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface CatalogSelectListener {
        fun onCatalogSelect(itemView: View, position: Int)
    }
}