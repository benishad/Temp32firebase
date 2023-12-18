package com.example.temp32_firebase

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseDatabase.getInstance() //실시간
        val tempCRef = db.getReference("TempC")
        val tempFRef = db.getReference("TempF")
        val humRef = db.getReference("Hum")
        val hicRef = db.getReference("HIC")
        val hifRef = db.getReference("HIF")

        val TempCVal = findViewById<TextView>(R.id.TempCval)
        val TempFVal = findViewById<TextView>(R.id.TempFval)
        val HumVal = findViewById<TextView>(R.id.Humval)
        val HicVal = findViewById<TextView>(R.id.Hicval)
        val HifVal = findViewById<TextView>(R.id.Hifval)

        val switch = findViewById<Switch>(R.id.switch1)

//        tempCRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (switch.isChecked) {
//                    Toast.makeText(this@MainActivity, "데이터 불러오는 중...", Toast.LENGTH_SHORT).show()
//                    val TempC = dataSnapshot.child("Temp(C)").getValue(Float::class.java)
//                    Log.d(ContentValues.TAG, "온도는 : $TempC")
//                    TempCVal.text = TempC?.toString() ?: "novalue"
//                } else {
//                    Toast.makeText(this@MainActivity, "초기화 중...", Toast.LENGTH_SHORT).show()
//                    TempCVal.text = "-"
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
//            }
//
//        })

        val tempCListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val TempC = dataSnapshot.child("Temp(C)").getValue(Float::class.java)
                Log.d(ContentValues.TAG, "온도는 : $TempC")
                TempCVal.text = TempC?.toString() ?: "novalue"
                Toast.makeText(this@MainActivity, "데이터 갱신...", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        }

        val tempFListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val TempF = dataSnapshot.child("Temp(F)").getValue(Float::class.java)
                Log.d(ContentValues.TAG, "온도는 : $TempF")
                TempFVal.text = TempF?.toString() ?: "novalue"
                Toast.makeText(this@MainActivity, "데이터 갱신...", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        }

        val humListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val hum = dataSnapshot.child("Humidity").getValue(Float::class.java)
                Log.d(ContentValues.TAG, "습도는 : $hum")
                HumVal.text = hum?.toString() ?: "novalue"
                Toast.makeText(this@MainActivity, "데이터 갱신...", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        }

        val hicListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val hic = dataSnapshot.child("HIc(C)").getValue(Float::class.java)
                Log.d(ContentValues.TAG, "열지수(°C) : $hic")
                HicVal.text = hic?.toString() ?: "novalue"
                Toast.makeText(this@MainActivity, "데이터 갱신...", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        }

        val hifListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val hif = dataSnapshot.child("HIf(F)").getValue(Float::class.java)
                Log.d(ContentValues.TAG, "열지수(°F) : $hif")
                HifVal.text = hif?.toString() ?: "novalue"
                Toast.makeText(this@MainActivity, "데이터 갱신...", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        }

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this@MainActivity, "데이터 불러오는 중...", Toast.LENGTH_SHORT).show()
                tempCRef.addValueEventListener(tempCListener)
                tempFRef.addValueEventListener(tempFListener)
                humRef.addValueEventListener(humListener)
                hicRef.addValueEventListener(hicListener)
                hifRef.addValueEventListener(hifListener)
            } else {
                Toast.makeText(this@MainActivity, "초기화 중...", Toast.LENGTH_SHORT).show()
                tempCRef.removeEventListener(tempCListener)
                tempFRef.removeEventListener(tempFListener)
                humRef.removeEventListener(humListener)
                hicRef.removeEventListener(hicListener)
                hifRef.removeEventListener(hifListener)
                TempCVal.text = "-"
                TempFVal.text = "-"
                HumVal.text = "-"
                HicVal.text = "-"
                HifVal.text = "-"
            }
        }

//        tempFRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (switch.isChecked) {
//                    Toast.makeText(this@MainActivity, "데이터 불러오는 중...", Toast.LENGTH_SHORT).show()
//                    val TempF = dataSnapshot.child("Temp(F)").getValue(Float::class.java)
//                    Log.d(ContentValues.TAG, "온도는 : $TempF")
//                    TempFVal.text = TempF?.toString() ?: "novalue"
//                } else {
//                    Toast.makeText(this@MainActivity, "초기화 중...", Toast.LENGTH_SHORT).show()
//                    TempFVal.text = "-"
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
//            }
//
//        })


//        humRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (switch.isChecked) {
//                    Toast.makeText(this@MainActivity, "데이터 불러오는 중...", Toast.LENGTH_SHORT).show()
//                    val hum = dataSnapshot.child("Humidity").getValue(Float::class.java)
//                    Log.d(ContentValues.TAG, "습도는 : $hum")
//                    HumVal.text = hum?.toString() ?: "novalue"
//                } else {
//                    Toast.makeText(this@MainActivity, "초기화 중...", Toast.LENGTH_SHORT).show()
//                    HumVal.text = "-"
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
//            }
//
//        })


//        hicRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (switch.isChecked) {
//                    Toast.makeText(this@MainActivity, "데이터 불러오는 중...", Toast.LENGTH_SHORT).show()
//                    val hic = dataSnapshot.child("HIc(C)").getValue(Float::class.java)
//                    Log.d(ContentValues.TAG, "열지수(°C) : $hic")
//                    HicVal.text = hic?.toString() ?: "novalue"
//                } else {
//                    Toast.makeText(this@MainActivity, "초기화 중...", Toast.LENGTH_SHORT).show()
//                    HicVal.text = "-"
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
//            }
//
//        })


//        hifRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (switch.isChecked) {
//                    Toast.makeText(this@MainActivity, "데이터 불러오는 중...", Toast.LENGTH_SHORT).show()
//                    val hif = dataSnapshot.child("HIf(F)").getValue(Float::class.java)
//                    Log.d(ContentValues.TAG, "열지수(°F) : $hif")
//                    HifVal.text = hif?.toString() ?: "novalue"
//                } else {
//                    Toast.makeText(this@MainActivity, "초기화 중...", Toast.LENGTH_SHORT).show()
//                    HifVal.text = "-"
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
//            }
//
//        })



    }
}