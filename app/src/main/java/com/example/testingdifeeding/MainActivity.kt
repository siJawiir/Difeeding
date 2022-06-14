package com.example.testingdifeeding

import android.os.Bundle
import android.view.WindowManager
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : BaseActivity() {

//    private lateinit var listFCR : ListView
//    private lateinit var ref : DatabaseReference
//    private lateinit var dataList : MutableList<dataFCR>

    private lateinit var dbref : DatabaseReference
    private lateinit var dataRecyclerView: RecyclerView
    private lateinit var dataArrayList: ArrayList<dataFCR>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        dataRecyclerView = findViewById(R.id.rv_data_fcr)
        dataRecyclerView.layoutManager = LinearLayoutManager(this)
        dataRecyclerView.setHasFixedSize(true)

        dataArrayList = arrayListOf<dataFCR>()
        getDataFCR()
//        ref = FirebaseDatabase.getInstance().getReference("User")
//        listFCR = findViewById(R.id.lv_data_fcr)
//        dataList = mutableListOf()
//        ref.addValueEventListener(object : ValueEventListener {
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists()){
//                    for (h in snapshot.children){
//                        val fcr = h.getValue(dataFCR::class.java)
//                        if (fcr != null) {
//                            dataList.add(fcr)
//                        }
//                    }
//                    val adapter = DataAdapter(applicationContext,R.layout.data_fcr, dataList)
//                    listFCR.adapter = adapter
//                }
//            }
//        })

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.menu.findItem(R.id.nav_home).isChecked = true
        bottomNavigation.setOnNavigationItemSelectedListener(nav)
    }

    private fun getDataFCR() {
        val idUser = FirebaseAuth.getInstance().currentUser?.uid
        dbref = FirebaseDatabase.getInstance().getReference(idUser!!)
        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(dataSnapshot in snapshot.children){
                        val data = dataSnapshot.getValue(dataFCR::class.java)
                        dataArrayList.add(data!!)
                    }
                    dataRecyclerView.adapter = DataAdapter(dataArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}