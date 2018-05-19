package com.freeleo.components

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.freeleo.componentlib.router.Router
import com.freeleo.service_component.computer.ComputerService
import com.freeleo.service_component.player.PlayerService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var router = Router.get()
    companion object {
        const val COMPONENT_COMPUTER_NAME = "com.freeleo.computer.applicationLike.ComputerAppLike"
        const val PLAYER_COMPUTER_NAME = "com.freeleo.player.applicationLike.PlayerAppLike"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerComponent();
        setupView()
    }

    /**
     * 手动加载组件方式
     */
    fun registerComponent(){
        Router.registerComponent(COMPONENT_COMPUTER_NAME)
        Router.registerComponent(PLAYER_COMPUTER_NAME)
    }

    fun setupView(){
        enterComputerBtn.setOnClickListener({
            var computerService: ComputerService? = router.getService(ComputerService::javaClass.name) as? ComputerService
            computerService?.startArithmeticActivity(this)
        })

        playerBtn.setOnClickListener({
            var playerService: PlayerService? = router.getService(PlayerService::javaClass.name) as? PlayerService
            playerService?.startPlayerActivity(this)
        })

        urlBtn.setOnClickListener({
            var playerService: PlayerService? = router.getService(PlayerService::javaClass.name) as? PlayerService
            Toast.makeText(this,playerService?.getUrl(),Toast.LENGTH_SHORT).show()
        })
    }

    override fun onStop() {
        super.onStop()
        unregisterComponent()
    }

    /**
     * 手动卸载组件
     */
    fun unregisterComponent(){
        Router.unregisterComponent(COMPONENT_COMPUTER_NAME)
        Router.unregisterComponent(PLAYER_COMPUTER_NAME)
    }
}
