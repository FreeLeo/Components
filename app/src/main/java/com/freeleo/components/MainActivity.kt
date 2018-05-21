package com.freeleo.components

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.freeleo.componentlib.router.Router
import com.freeleo.service_component.computer.ComputerService
import com.freeleo.service_component.player.PlayerService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var router = Router.get()
    companion object {
        const val COMPONENT_COMPUTER_NAME = "com.freeleo.computer.applicationLike.ComputerAppLike"
        const val PLAYER_COMPUTER_NAME = "com.freeleo.player.applicationLike.PlayerAppLike"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerComponent()
        setupView()
    }

    /**
     * 手动加载组件方式
     */
    private fun registerComponent(){
        Router.registerComponent(COMPONENT_COMPUTER_NAME)
        Router.registerComponent(PLAYER_COMPUTER_NAME)
    }

    private fun setupView(){
        enterComputerBtn.setOnClickListener({
            var computerService: ComputerService? = router.getService(ComputerService::class.java.name) as? ComputerService
            computerService?.startArithmeticActivity(this)
        })

        playerBtn.setOnClickListener({
            var playerService: PlayerService? = router.getService(PlayerService::class.java.name) as? PlayerService
            playerService?.startPlayerActivity(this)
        })

        urlBtn.setOnClickListener({
            var playerService: PlayerService? = router.getService(PlayerService::class.java.name) as? PlayerService
            Toast.makeText(this,playerService?.getUrl(),Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterComponent()
    }

    /**
     * 手动卸载组件
     */
    private fun unregisterComponent(){
        Router.unregisterComponent(COMPONENT_COMPUTER_NAME)
        Router.unregisterComponent(PLAYER_COMPUTER_NAME)
    }
}
