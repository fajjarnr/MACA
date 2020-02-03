package com.example.maca.Sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.maca.HomeActivity
import com.example.maca.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth = FirebaseAuth.getInstance()
        button_login.setOnClickListener {
            doLogin()
        }

        register_text_button.setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    private fun doLogin(){
        if(!Patterns.EMAIL_ADDRESS.matcher(tv_email.text.toString()).matches()){
            tv_email.error = "Please Valid Email"
            tv_email.requestFocus()
            return
        }
        if (password.text.toString().isEmpty()){
            password.error = "Please Enter Password"
            tv_email.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(tv_email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                    Toast.makeText(baseContext, "Login failed.",
                        Toast.LENGTH_SHORT).show()
                }

            }
    }
    private fun updateUI(currentUser: FirebaseUser?){
        if(currentUser != null){
            if (currentUser.isEmailVerified){
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }
            else{
                Toast.makeText(baseContext, "Please Verify Your Email Address",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}
