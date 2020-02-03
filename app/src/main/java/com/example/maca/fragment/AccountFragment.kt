package com.example.maca.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.maca.R
import com.example.maca.ReadContact.ReadContactActivity
import com.example.maca.Sign.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_account.view.*

/**
 * A simple [Fragment] subclass.
 */
class AccountFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()

        val root = inflater.inflate(R.layout.fragment_account, container, false)
        root.logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            activity!!.startActivity(Intent(activity, SignInActivity::class.java))
            activity!!.finish()
        }

        root.btn_contact.setOnClickListener {
            activity!!.startActivity(Intent(activity, ReadContactActivity::class.java))
        }

        return root
    }
}
