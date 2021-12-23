package com.example.cryptogetter.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptogetter.Model.CryptoModel
import com.example.cryptogetter.R
import kotlinx.android.synthetic.main.row_item.view.*

class RecylerViewAdapter(private val list: ArrayList<CryptoModel>, private val listener: Listener) :
    RecyclerView.Adapter<RecylerViewAdapter.MyViewHolder>() {

    interface Listener {
        fun onItemClickListener(model: CryptoModel)
    }


    var colors: Array<String> =
        arrayOf("#19697b", "#354a2f", "#587b7f", "#84737b")

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: CryptoModel, colors: Array<String>, position: Int, listener: Listener) {
            itemView.setOnClickListener {
                listener.onItemClickListener(model)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 4]))
            itemView.textName.text = model.currency
            itemView.textPrice.text = model.price
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list.get(position), colors, position, listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}