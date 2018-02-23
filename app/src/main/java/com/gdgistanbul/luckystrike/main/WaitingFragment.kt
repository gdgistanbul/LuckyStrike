package com.gdgistanbul.luckystrike.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gdgistanbul.luckystrike.R
import com.gdgistanbul.luckystrike.model.Constant
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class WaitingFragment : Fragment() {

    companion object {
        fun newInstance() = WaitingFragment()
    }

    private val databaseReference = FirebaseDatabase.getInstance().reference
    private lateinit var textViewWaitingCount: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_waiting, container, false)
        textViewWaitingCount = view?.findViewById(R.id.textview_waiting) as TextView
        return view.rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        databaseReference.child(Constant.WAITING).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                var childSize = 0
                val childIterator = dataSnapshot?.children?.iterator()!!
                while (childIterator.hasNext()) {
                    childIterator.next()
                    childSize++
                }
                textViewWaitingCount.text = getWaitingListMessage(childSize)
            }

            override fun onCancelled(databaseError: DatabaseError?) {
            }

        })*/
    }

    private fun getWaitingListMessage(peopleCount: Int): String {
        return String.format(getString(R.string.waiting_list_message), peopleCount)
    }
}