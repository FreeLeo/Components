package com.freeleo.service_component.player

import android.content.Context

interface PlayerService{
    fun startPlayerActivity(context: Context)
    fun getUrl():String
}