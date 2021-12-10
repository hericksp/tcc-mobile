package com.example.towersadmin.chat

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.towersadmin.R
import kotlinx.android.synthetic.main.activity_entrance.*
import org.jetbrains.anko.startActivity

class EntranceActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        button.setOnClickListener(this)
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button -> enterChatroom()
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun enterChatroom(){

        val userName = userName.text.toString()
        val roomName = roomname.text.toString()

        if(!roomName.isNullOrBlank()&&!userName.isNullOrBlank()) {
            startActivity<ChatRoomActivity>(
                "userName" to userName,
                "roomName" to roomName
            )
        }else{
            Toast.makeText(this,"Nickname and Roomname should be filled!",Toast.LENGTH_SHORT)
        }
    }
}
