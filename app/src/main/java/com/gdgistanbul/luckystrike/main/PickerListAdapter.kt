package com.gdgistanbul.luckystrike.main

import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gdgistanbul.luckystrike.R
import com.gdgistanbul.luckystrike.model.User

/**
 * Created by mertsimsek on 21/02/18.
 */
class PickerListAdapter(val userList: MutableList<User>, val listener: OnClickItemListener) : RecyclerView.Adapter<PickerListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.textViewUserName?.text = userList[position].userName
        holder?.buttonUserPick?.setOnClickListener({
            listener.pickUser(userList[position])
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_picker_list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewUserName = itemView.findViewById<TextView>(R.id.textViewUserName)
        val buttonUserPick = itemView.findViewById<AppCompatButton>(R.id.buttonUserPick)

    }

    interface OnClickItemListener {
        fun pickUser(selectedUser: User)
    }
}
