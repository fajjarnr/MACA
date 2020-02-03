package com.example.maca.Sign

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.maca.HomeActivity
import com.example.maca.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()
        button_register.setOnClickListener {
            signUpUser()
        }

        login_text_button.setOnClickListener {
            intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            // Judul
            .setTitle("PERINGATAN!")
            // Pesan yang di tamopilkan
            .setMessage("Apakah Anda Yakin Ingin Keluar?")
            .setPositiveButton("YA", DialogInterface.OnClickListener { dialogInterface, i ->
                finish()
            })
            .setNegativeButton("TIDAK", DialogInterface.OnClickListener { dialogInterface, i ->
            })
            .show()

    }
    private fun signUpUser(){
        if (tv_user.text.toString().isEmpty()){
            tv_user.error = "Please Enter Username"
            tv_user.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(tv_email.text.toString()).matches()){
            tv_email.error = "Please Valid Email"
            tv_email.requestFocus()
            return
        }
        if (password.text.toString().isEmpty()){
            password.error = "Please Enter Password"
            tv_user.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(tv_email.text.toString(),password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)

                } else {
                    updateUI(null)
                    Toast.makeText(baseContext, "Sign Up failed, Try Again Later",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun updateUI(currentUser: FirebaseUser?){
        if(currentUser != null){
            val user = auth.currentUser
            user?.sendEmailVerification()
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, SignInActivity::class.java))
                        finish()
                    }
                }

        }
        else{
        }
    }
}
