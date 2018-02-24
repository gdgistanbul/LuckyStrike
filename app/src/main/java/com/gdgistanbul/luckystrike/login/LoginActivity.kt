package com.gdgistanbul.luckystrike.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.gdgistanbul.luckystrike.R
import com.gdgistanbul.luckystrike.main.HomeActivity
import com.gdgistanbul.luckystrike.model.Constant
import com.gdgistanbul.luckystrike.model.User
import com.gdgistanbul.util.PrefUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    /**
     * The entry point of the Firebase Authentication SDK.
     */
    private val firebaseAuth = FirebaseAuth.getInstance()

    /**
     * A Reference represents a specific location in the DB and can be used for reading or writing data to that DB location.
     */
    private val firebaseReference = FirebaseDatabase.getInstance().reference

    private lateinit var edittextUsername: EditText
    private lateinit var buttonLogin: Button
    private lateinit var waitingIndicator: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /**
         *  check if the user is signed in and check for null.
         */
        if (firebaseAuth.currentUser != null) {
            navigateHomePage()
            return
        }

        edittextUsername = findViewById(R.id.edittext_username)
        buttonLogin = findViewById(R.id.button_login)
        waitingIndicator = findViewById(R.id.waiting_indicator)
        buttonLogin.setOnClickListener { login() }
    }

    /**
     * Login user anonymously and call @saveUserToDatabase method on
     * user successfully logged in
     */
    private fun login() {
        if (TextUtils.isEmpty(edittextUsername.text)) return
        showWaitingIndicator()
        disableTouch()
        firebaseAuth.signInAnonymously()
                .addOnSuccessListener { saveUserToDatabase() }
                .addOnFailureListener { Log.v("TEST", "Error") }
    }

    /**
     * Disable touch to scren when click to login button
     */
    private fun disableTouch(){
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    /**
     * Enable touch if login is successfull
     */
    private fun enableTouch(){
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    /**
     * Show Indicator when click to login button
     */
    private fun showWaitingIndicator(){
        waitingIndicator.visibility = View.VISIBLE
    }

    /**
     * Hide Indicator if login is successfull
     */
    private fun hideWaitingIndicator(){
        waitingIndicator.visibility = View.INVISIBLE
    }

    /**
     * Find "waitings" child on firebase database reference
     * push() method generates a "id" for our user.
     * We create a user with generated id and save user to waiting list.
     */
    private fun saveUserToDatabase() {
        val firebaseReferences = firebaseReference.child(Constant.WAITING).push()
        val key = firebaseReferences.key
        firebaseReferences.setValue(
                User(key, getUserName()), listener)
    }

    /**
     * Skip next activity on user saved to database successfully
     */
    private val listener: DatabaseReference.CompletionListener =
            DatabaseReference.CompletionListener { databaseError: DatabaseError?, databaseReference: DatabaseReference? ->
                databaseReference?.let {
                    PrefUtil.putString(this, Constant.USER_ID, databaseReference.key)
                    PrefUtil.putString(this, Constant.USER_NAME, getUserName())
                    hideWaitingIndicator()
                    enableTouch()
                    navigateHomePage()
                }
            }


    /**
     * get user name from username edittext
     */
    private fun getUserName(): String {
        return edittextUsername.text.toString().trim()
    }

    /**
     * open HomeActivity.
     */
    private fun navigateHomePage() {
        startActivity(HomeActivity.newIntent(this))
        finish()
    }
}