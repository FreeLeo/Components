package com.freeleo.computer.applicationLike

import com.freeleo.componentlib.applicationLike.IApplicationLike
import com.freeleo.componentlib.router.Router
import com.freeleo.computer.serviceImpl.ComputerServiceImpl
import com.freeleo.service_component.computer.ComputerService

class ComputerAppLike: IApplicationLike {
    var router = Router.get()
    override fun onCreate() {
        router.addService(ComputerService::class.java.name,ComputerServiceImpl())
    }

    override fun onStop() {
        router.removeService(ComputerService::class.java.name)
    }
}