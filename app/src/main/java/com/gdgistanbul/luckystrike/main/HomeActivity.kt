package com.gdgistanbul.luckystrike.main

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gdgistanbul.luckystrike.R
import com.gdgistanbul.luckystrike.model.Constant
import com.gdgistanbul.luckystrike.model.User
import com.gdgistanbul.util.PrefUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeActivity : AppCompatActivity() {

    private val databaseReference = FirebaseDatabase.getInstance().reference

    companion object {
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            return
        }

        supportFragmentManager.beginTransaction()
                .add(R.id.container, WaitingFragment.newInstance())
                .commit()


        /**
         * The listener receives a DataSnapshot that contains the data at the specified location in the database at the time of the event.
         * Calling getValue() on a snapshot returns the Java object representation of the data.
         * If no data exists at the location, calling getValue() returns null.
         */
        /*databaseReference.child(Constant.LOOSERS)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot?) {

                        dataSnapshot?.let {
                            dataSnapshot.children
                                    .map { it.getValue(User::class.java) }
                                    .forEach {
                                        it?.let {
                                            if (isSameUser(it.userId)) {
                                                openLoserPage()
                                            }
                                        }
                                    }
                        }
                    }
                })*/

        /**
         * The listener receives a DataSnapshot that contains the data at the specified location in the database at the time of the event.
         * Calling getValue() on a snapshot returns the Java object representation of the data.
         * If no data exists at the location, calling getValue() returns null.
         */
        /*databaseReference.child(Constant.PICKER)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot?) {

                        dataSnapshot?.let {

                            dataSnapshot.children
                                    .map { it.getValue(User::class.java) }
                                    .forEach {
                                        it?.let {
                                            if (isSameUser(it.userId)) {
                                                openPickerPage()
                                            }
                                        }
                                    }
                        }
                    }
                })*/
    }

    /**
     * get member id from local data source.
     */
    private fun getMemberId(): String {
        return PrefUtil.getString(this, Constant.USER_ID, "")
    }

    /**
     * Skip to picker page.
     */
    private fun openPickerPage() {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, PickerFragment.newInstance())
                .commit()
    }

    /**
     * Skip to loser page.
     */
    private fun openLoserPage() {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, LostFragment.newInstance())
                .commit()
    }

    private fun isSameUser(userId: String): Boolean {
        return userId.equals(getMemberId())
    }
}
