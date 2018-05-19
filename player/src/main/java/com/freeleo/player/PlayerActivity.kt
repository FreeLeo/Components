package com.freeleo.player

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.player_activity_player.*

class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_activity_player)
        var videoBean = VideoBean("西游记","http://www.xiyouji.com")
        playerTv.text = playerTv.text.toString() + "\n" + videoBean.name + "\n" + videoBean.url
    }
}
