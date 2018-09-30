package com.example.admin.registrationexample

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseException
import com.parse.ParseUser
import com.parse.SignUpCallback
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        btnBack.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        btnRegister.setOnClickListener {
            if(editPassword.text.toString() == "" || editPassword2.text.toString() == "" ||
                    editUsername.text.toString() == ""){
                Toast.makeText(this, "Enter All Data", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            val dlg = ProgressDialog(this)
            dlg.setTitle("Please, wait a moment.")
            dlg.setMessage("Signing up...")
            dlg.show()


            val user = ParseUser()
            user.username = editUsername.getText().toString()
            user.setPassword(editPassword.getText().toString())
            user.signUpInBackground { e ->
                if (e == null) {
                    dlg.dismiss()
                    alertDisplayer("Successful Login", "Welcome " + editUsername.text.toString() + "!" )

                } else {
                    dlg.dismiss()
                    ParseUser.logOut()
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                }
            }



        }
    }

    private fun isEmpty(text: EditText): Boolean {
        return if (text.text.toString().trim { it <= ' ' }.length > 0) {
            false
        } else {
            true
        }
    }

    private fun isMatching(text1: EditText, text2: EditText): Boolean {
        return if (text1.text.toString() == text2.text.toString()) {
            true
        } else {
            false
        }
    }

    private fun alertDisplayer(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK") { dialog, which ->
                    dialog.cancel()
                    val intent = Intent(this, LogoutActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
        val ok = builder.create()
        ok.show()
    }
}
