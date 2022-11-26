package com.dami.frontend_androidteam2

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.dami.frontend_androidteam2.databinding.ActivityAddBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddBinding
    private var isImageUpload = false
    val list_item = mutableListOf<String>()
    lateinit var dateKey:String
    var itemkey:String?=null
    private lateinit var listAdapter: listAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_add)
        val listview:ListView

        dateKey = intent.getStringExtra("dateKey").toString()

        binding.MonthArea.setText(dateKey)
        binding.timeArea.setText( getTime())
        key(dateKey)

        binding.imageArea.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)

        }

        binding.btnSend.setOnClickListener {

            val title = binding.titleArea.text.toString()
            val content = binding.contentArea.text.toString()
            val time = getTime()



            FBRef.boardRef
                .child(dateKey)
                .push()
                .setValue(AddModel(title, content, time))


            Log.d("이이입려려력", title.toString())

            Toast.makeText(this, "오늘의 일기 입력 완료!!", Toast.LENGTH_LONG).show()
                imageUpload( title.toString())

          finish()
        }
            }


fun key(date:String){

    val postListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {

            for (dataModel in dataSnapshot.children) {
                if (date == dataSnapshot.key.toString()){

                    itemkey = dataModel.getValue(AddModel::class.java).toString()



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
    fun getTime() : String {

        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss", Locale.KOREA).format(currentDateTime)
        return dateFormat

    }

    private fun imageUpload(key : String){
        // Get the data from an ImageView as bytes

        val storage = Firebase.storage
        val storageRef = storage.reference
        val mountainsRef = storageRef.child(key + ".png")

// Get the data from an ImageView as bytes
        val imageView = binding.imageArea
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 100) {
            binding.imageArea.setImageURI(data?.data)
        }

    }




}