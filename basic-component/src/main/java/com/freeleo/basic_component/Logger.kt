package com.freeleo.basic_component

import android.util.Log

class Logger {
    companion object {
        fun i(tag: String,str: String){
            Log.i(tag,str)
        }

        fun v(tag: String,str: String){
            Log.v(tag,str)
        }

        fun d(tag: String,str: String){
            Log.d(tag,str)
        }

        fun e(tag: String,str: String){
            Log.e(tag,str)
        }
    }
}