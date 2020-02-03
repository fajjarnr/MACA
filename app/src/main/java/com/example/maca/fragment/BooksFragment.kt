package com.example.maca.fragment


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
import com.example.maca.R
import com.example.maca.fragment.Model.Adapter.MyGroupAdapter
import com.example.maca.fragment.Model.Adapter.MyItemAdapter
import com.example.maca.fragment.Model.Interface.IFirebaseLoadListener
import com.example.maca.fragment.Model.ItemData
import com.example.maca.fragment.Model.ItemGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*


/**
 * A simple [Fragment] subclass.
 */
class BooksFragment : Fragment(), IFirebaseLoadListener {
    override fun onFirebaseLoadSuccess(itemGroupList: List<ItemGroup>) {
        val adapter = MyGroupAdapter(activity!!, itemGroupList)
        recyclerView.adapter = adapter
        dialog.dismiss()
    }

    override fun onFirebaseLoadFailed(message: String) {
        dialog.dismiss()
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private lateinit var auth: FirebaseAuth
    lateinit var dialog: AlertDialog
    lateinit var iFirebaseLoadListener: IFirebaseLoadListener
    lateinit var myData: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        val root = inflater.inflate(R.layout.fragment_books, container, false)
        return root
    }

    fun getFirebaseData() {
        dialog.show()
        myData.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                iFirebaseLoadListener.onFirebaseLoadFailed(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                val itemGroups = ArrayList<ItemGroup>()
                for (myDataSnapShot in p0.children) {
                    val itemGroup = ItemGroup()
                    itemGroup.headerTitle = myDataSnapShot.child("headerTitle")
                        .getValue(true).toString()
                    val t = object : GenericTypeIndicator<ArrayList<ItemData>>() {}
                    itemGroup.listItem = myDataSnapShot.child("listItem").getValue(t)
                    itemGroups.add(itemGroup)
                }
                iFirebaseLoadListener.onFirebaseLoadSuccess(itemGroups)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iFirebaseLoadListener = this
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity!!)
        dialog = SpotsDialog.Builder().setContext(activity).build()
        myData = FirebaseDatabase.getInstance().getReference("MyData")
        getFirebaseData()
    }

//    companion object {
//        const val EXTRA_MYDATA = "extra_mydata"   //tambahan
//    }
//
//    private fun showRecyclerView() {
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        val recyclerViewMyDataAdapter = MyItemAdapter (list,this@BookFragment)
//        recyclerView.adapter = recyclerViewMyDataAdapter
//    }
}