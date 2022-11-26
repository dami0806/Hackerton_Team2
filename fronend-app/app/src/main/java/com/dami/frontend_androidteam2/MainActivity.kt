package com.dami.frontend_androidteam2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.dami.frontend_androidteam2.databinding.ActivityAddBinding
import com.dami.frontend_androidteam2.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var addimg:ImageView
    lateinit var calendarView: CalendarView
    lateinit var dateTV: TextView
    val list_item = mutableListOf<AddlistModel>()
    val listitem = mutableListOf<AddModel>()
    var Datetext:String= ""
    var dateKey: String="" //넘길 키
    var infoTitle: String="" //넘길 키
    var infoContent: String="" //넘길 키
    var infoTime: String="" //넘길 키
    var titleText:String =""
    var timeText:String =""
    private lateinit var listAdapter: listAdapter
    private lateinit var titleAdapter:titleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val listview = binding.listView

        //titleAdapter = titleAdapter(list_item)
       // listview.adapter =  titleAdapter
         listAdapter=listAdapter(listitem)
         listview.adapter = listAdapter

        listview.setOnItemClickListener { parent, view, position, id ->
            Log.d("화긴", listitem[position].toString()) // AddModel(title=가, content=나, time=2022년 11월 26일 11:18:48)
            Log.d("화긴",position.toString())
            //actiity_show에 정보 intent
            val intent = Intent(this, showActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            infoTitle = intent.putExtra("infoTitle", listitem[position].title).toString()
            infoContent = intent.putExtra("infoContent", listitem[position].content).toString()
            infoTime = intent.putExtra("infoTime", listitem[position].time).toString()
            dateKey = intent.putExtra("dateKey", Datetext).toString()
            startActivity(intent)

        }



        calendarView = findViewById(R.id.calendarView)
        addimg = findViewById(R.id.addimg)
        dateTV = findViewById(R.id.idTVDate)
        calendarView.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
                Datetext =
                    (year.toString() + "년 " + (month + 1).toString() + "월 " + dayOfMonth.toString() + "일")
                Log.d("확인", Datetext)
                dateTV.setText(Datetext)
                //getData(dateTV.toString())
                getCommentData(Datetext)
            })

        addimg.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            dateKey = intent.putExtra("dateKey", Datetext).toString()
            startActivity(intent)
        }
    }


fun getCommentData(date: String) {
//comment 아래 board아래 commentkey 아래 comment 데이터들
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listitem.clear()

                for (dataModel in dataSnapshot.children) {
                    if (date == dataSnapshot.key.toString()){
                                Log.d("담3", dataModel.toString())
                                val item = dataModel.getValue(AddModel::class.java)
                                if (item != null) {
                                    listitem.add(item)}
                                listAdapter.notifyDataSetChanged()//어댑터 동기화*/
                }

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        FBRef.boardRef
            .child(date)
            .addValueEventListener(postListener)

    }
}





