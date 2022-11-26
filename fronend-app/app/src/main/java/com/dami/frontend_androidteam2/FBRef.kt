package com.dami.frontend_androidteam2

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FBRef {
    companion object {

        private val database = Firebase.database
        val DateRef = database.getReference("Date")

        val bookmarkRef = database.getReference("bookmark_list")

        val boardRef = database.getReference("board")

    }
    }
