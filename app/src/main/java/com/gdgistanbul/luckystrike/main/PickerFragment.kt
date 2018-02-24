package com.gdgistanbul.luckystrike.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdgistanbul.luckystrike.R
import com.gdgistanbul.luckystrike.model.Constant
import com.gdgistanbul.luckystrike.model.User
import com.gdgistanbul.util.PrefUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PickerFragment : Fragment(), PickerListAdapter.OnClickItemListener {

    companion object {
        fun newInstance() = PickerFragment()
    }

    private val userList: MutableList<User> = mutableListOf()
    private val databaseReference = FirebaseDatabase.getInstance().reference

    private lateinit var recylerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_picker, container, false)
        recylerView = view?.findViewById(R.id.recyclerview) as RecyclerView
        return view.rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

  /*      databaseReference.child(Constant.WAITING).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot?) {

                *//**
                 * clear user list every data change.
                 *//*
                userList.clear()

                *//**
                 * receives a DataSnapshot that contains the data at the specified  children.
                 *//*
                dataSnapshot?.let {
                    it.children
                            .map { it.getValue(User::class.java) }
                            .forEach {
                                it?.let {
                                    userList.add(it)
                                }
                            }
                    setPickerListAdapter(userList)
                }
            }

            override fun onCancelled(p0: DatabaseError?) {
            }

        })*/
    }

    private fun setPickerListAdapter(userList: MutableList<User>) {
        val pickerListAdapter = PickerListAdapter(userList, this)
        recylerView.adapter = pickerListAdapter
    }

    override fun pickUser(selectedUser: User) {
        //deleteSelectedUserFromWaitingList(selectedUser)
        //insertSelectedUserToPickerList(selectedUser)
        //deletePickerUserFromWaitingList()
        //insertPickerUserToLooserList()
    }

    /**
     * delete picked user from waiting list.
     * first get a DatabaseReference object and delete child node releted to selected user id.
     */
   /* private fun deletePickerUserFromWaitingList() {
        val waitingDatabaseReference = databaseReference.child(Constant.WAITING)
        val user = getUser()
        waitingDatabaseReference.child(user.userId).removeValue()
    }*/

    /**
     * insert selected user to picker list
     * first get a DatabaseReference object and insert a new child node releted to selected user id.
     */
   /* private fun insertSelectedUserToPickerList(selectedUser: User) {

        val pickerDatabaseReference = databaseReference.child(Constant.PICKER)

        pickerDatabaseReference.removeValue()
        pickerDatabaseReference
                .child(selectedUser.userId)
                .setValue(User(selectedUser.userId, selectedUser.userName))
    }*/

    /**
     * insert picker user to looser list.
     * that user should show Loser Page after this operation
     *//*
    private fun insertPickerUserToLooserList() {

        val looserDatabaseReference = databaseReference.child(Constant.LOOSERS)
        val user = getUser()

        looserDatabaseReference
                .child(user.userId)
                .setValue(user)
    }*/

    /**
     * delete picker user from waiting list.
     */
    /*private fun deleteSelectedUserFromWaitingList(user: User) {
        val waitingDatabaseReference = databaseReference.child(Constant.WAITING)
        waitingDatabaseReference.child(user.userId).removeValue()
    }*/

    /**
     * get user from local data source
     */
    private fun getUser(): User {
        val userId = PrefUtil.getString(context, Constant.USER_ID, "")
        val userName = PrefUtil.getString(context, Constant.USER_NAME, "")
        return User(userId, userName)
    }
}
