package com.dami.frontend_androidteam2

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.dami.frontend_androidteam2.databinding.ActivityAddBinding
import com.dami.frontend_androidteam2.databinding.ActivityShowBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class showActivity : AppCompatActivity() {
    private lateinit var binding : ActivityShowBinding
    private var isImageUpload = false
    val list_item = mutableListOf<String>()
    lateinit var dateKey:String
    lateinit var infoTitle:String
    lateinit var infoContent:String
    lateinit var infoTime:String
    private lateinit var listAdapter: listAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        val listview: ListView
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show)
        dateKey = intent.getStringExtra("dateKey").toString()
        Log.d("확인~",dateKey)
        infoTitle = intent.getStringExtra("infoTitle").toString()
        infoContent = intent.getStringExtra("infoContent").toString()
        infoTime= intent.getStringExtra("infoTime").toString()
        Log.d("확인!!", infoTitle )
        //binding.MonthArea.setText(dateKey)
        binding.timeArea.setText( " 작성일: "+getTime())

            binding.MonthArea.setText("♥ "+dateKey+"의 하루 ♥")
           binding.titleArea.setText(infoTitle)
            binding.contentArea.setText(infoContent)

            val time = getTime()

            // 파이어베이스 store에 이미지를 저장하고 싶습니다
            // 만약에 내가 게시글을 클릭했을 때, 게시글에 대한 정보를 받아와야 하는데
            // 이미지 이름에 대한 정보를 모르기 때문에
            // 이미지 이름을 문서의 key값으로 해줘서 이미지에 대한 정보를 찾기 쉽게 해놓음.




    }

    fun getTime() : String {

        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss", Locale.KOREA).format(currentDateTime)

        return dateFormat

    }








    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 100) {
            binding.imageArea.setImageURI(data?.data)
        }

    }




}