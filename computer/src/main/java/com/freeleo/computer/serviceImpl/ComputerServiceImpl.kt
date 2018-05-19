package com.freeleo.computer.serviceImpl

import android.content.Context
import android.content.Intent
import com.freeleo.computer.ArithmeticActivity
import com.freeleo.service_component.computer.ComputerService

class ComputerServiceImpl : ComputerService{
    override fun startArithmeticActivity(context: Context) {
        val intent = Intent(context,ArithmeticActivity::class.java)
        context.startActivity(intent)
    }
}