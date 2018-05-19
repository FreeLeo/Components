package com.freeleo.componentlib.router

import com.freeleo.componentlib.applicationLike.IApplicationLike

class Router {
    companion object {
        val components = HashMap<String,IApplicationLike>()

        fun get(): Router{
            return Instance.router
        }

        fun registerComponent(componentName: String){
            if(!components.containsKey(componentName)){
                val applicationLike = reflectApplicationLike(componentName)
                components[componentName] = applicationLike
                applicationLike.onCreate()
            }
        }

        fun unregisterComponent(componentName: String){
            if(components.containsKey(componentName)){
                components[componentName]?.onStop()
                components.remove(componentName)
            }
        }

        private fun reflectApplicationLike(className: String): IApplicationLike{
            val clazz = Class.forName(className)
            return clazz.newInstance() as IApplicationLike
        }
    }

    private object Instance{
        val router: Router = Router()
    }

    private val services = HashMap<String,Any>()

    fun addService(serviceName: String, any: Any){
        services[serviceName] = any
    }

    fun removeService(serviceName: String?){
        services.remove(serviceName)
    }

    fun getService(serviceName: String): Any?{
        return services[serviceName]
    }
}