package com.freeleo.computer.applicationLike

import com.freeleo.componentlib.applicationLike.IApplicationLike
import com.freeleo.componentlib.router.Router
import com.freeleo.computer.serviceImpl.PlayerServiceImpl
import com.freeleo.service_component.computer.ComputerService
import com.freeleo.service_component.player.PlayerService

class PlayerAppLike: IApplicationLike {
    var router = Router.get()
    override fun onCreate() {
        router.addService(PlayerService::javaClass.name,PlayerServiceImpl())
    }

    override fun onStop() {
        router.removeService(PlayerService::javaClass.name)
    }
}