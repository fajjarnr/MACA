package com.example.maca.fragment.Model.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.maca.R
import com.example.maca.fragment.Model.Interface.IItemClickListener
import com.example.maca.fragment.Model.ItemData
import com.squareup.picasso.Picasso

class MyItemAdapterExplore(
    private val context: Context,
    private val itemList: List<ItemData>?
) : RecyclerView.Adapter<MyItemAdapterExplore.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view: View?
        view = LayoutInflater.from(context).inflate(R.layout.layout_item_explore, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList?.size ?: 0
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        myViewHolder.txt_title.setText(itemList!![position].name!!)
        Picasso.get().load(itemList[position].image).into(myViewHolder.img_item)

        myViewHolder.setClick(object : IItemClickListener {
            override fun onClickListener(view: View, position: Int) {
                Toast.makeText(context, "" + itemList[position].name, Toast.LENGTH_LONG).show()
            }

        })
    }


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var txt_title: TextView
        var img_item: ImageView

        lateinit var iItemClickListener: IItemClickListener

        fun setClick(iItemClickListener: IItemClickListener) {
            this.iItemClickListener = iItemClickListener
        }


        init {
            txt_title = view.findViewById(R.id.tvTitle) as TextView
            img_item = view.findViewById(R.id.item_image) as ImageView

            view.setOnClickListener(this)

        }

        override fun onClick(view: View?) {
            iItemClickListener.onClickListener(view!!, adapterPosition)

        }
    }
}