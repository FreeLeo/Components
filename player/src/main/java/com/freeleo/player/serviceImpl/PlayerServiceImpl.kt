package com.freeleo.computer.serviceImpl

import android.content.Context
import android.content.Intent
import com.freeleo.player.PlayerActivity
import com.freeleo.service_component.player.PlayerService

class PlayerServiceImpl : PlayerService{
    override fun startPlayerActivity(context: Context) {
        context.startActivity(Intent(context,PlayerActivity::class.java))
    }

    override fun getUrl(): String {
        return "http://www.xiyouji.com"
    }

}