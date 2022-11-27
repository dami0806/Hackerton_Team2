package com.dami.frontend_androidteam2

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dami.frontend_androidteam2.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var addimg: ImageView
    lateinit var calendarView: CalendarView
    lateinit var dateTV: TextView

    val listitem = mutableListOf<AddModel>()
    var Datetext: String = ""
    //넘길 키
    var dateKey: String = ""
    var infoTitle: String = ""
    var infoContent: String = ""
    var infoTime: String = ""
    var imgkey: String = ""

    private lateinit var listAdapter: listAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val listview = binding.listView

        listAdapter = listAdapter(listitem)
        listview.adapter = listAdapter

        calendarView = findViewById(R.id.calendarView)
        addimg = findViewById(R.id.addimg)
        dateTV = findViewById(R.id.idTVDate)

        calendarViewData()

        listview.setOnItemClickListener { parent, view, position, id ->
            listviewTap(position)
        }


      listview.setOnItemLongClickListener { parent, view, position, id ->
          removeList(position)
          true

      }


        addimg.setOnClickListener {
            addimgTap()
        }
    }

    private fun removeList(position: Int) {
        var dlg = AlertDialog.Builder(this@MainActivity)
        dlg.setTitle("ourStory")
        dlg.setMessage("삭제하시겠습니까?")
        dlg.setIcon(R.drawable.img_2)
        var seleitem=listitem[position].time.toString()
        dlg.setNegativeButton("삭제") { dialog, which ->

            val postListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (dataModel in dataSnapshot.children) { //datModel: key값
                        Log.d("출력1",FBRef.boardRef.child(Datetext).child( dataModel.key.toString()).toString())
                        if (seleitem == dataModel.getValue(AddModel::class.java)!!.time.toString()) //클릭한 list[postiion]time과 dateModel의 value time 같을시 삭제
                        {
                            Log.d("출력2",seleitem)
                            Log.d("출력3",dataModel.getValue(AddModel::class.java)!!.time.toString())
                            Log.d("출력3",dataModel.key.toString())
                            FBRef.boardRef
                                .child(Datetext)
                                .child( dataModel.key.toString())
                            .removeValue()



                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w(
                        "ContentListActivity",
                        "loadPost:onCancelled",
                        databaseError.toException()
                    )
                }
            }

            FBRef.boardRef
                .child(Datetext)
                .addValueEventListener(postListener)


            Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show()

        }
        dlg.setPositiveButton("취소", null)
        dlg.show()

    }


    private fun calendarViewData() {
        calendarView.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
                Datetext =
                    (year.toString() + "년 " + (month + 1).toString() + "월 " + dayOfMonth.toString() + "일")
                Log.d("확인", Datetext)
                dateTV.setText(Datetext)
                getCommentData(Datetext)
            })
    }

    private fun addimgTap() {
        val intent = Intent(this, AddActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        dateKey = intent.putExtra("dateKey", Datetext).toString()
        startActivity(intent)
    }


    private fun listviewTap(position: Int) {


        val intent = Intent(this, showActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        infoTitle = intent.putExtra("infoTitle", listitem[position].title).toString()
        infoContent = intent.putExtra("infoContent", listitem[position].content).toString()
        infoTime = intent.putExtra("infoTime", listitem[position].time).toString()
        dateKey = intent.putExtra("dateKey", Datetext).toString()
        startActivity(intent)
        imgkey = intent.getStringExtra("imgKey").toString()


    }


    fun getCommentData(date: String) {
        listitem.clear()
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("담52", date.toString())
                Log.d("담5", dataSnapshot.key.toString())
                listitem.clear()
                listAdapter.notifyDataSetChanged()
                for (dataModel in dataSnapshot.children) {
                    Log.d("담523",dataModel.toString())
                    if(dataModel.key != null) {
                        if (date == dataSnapshot.key.toString()) {
                            val item = dataModel.getValue(AddModel::class.java)
                            if (item != null) {
                                listitem.add(item)
                            }
                            listAdapter.notifyDataSetChanged()//어댑터 동기화
                        }
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





