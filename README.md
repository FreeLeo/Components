# Android - 组件化

这个教程怎么写？决定直接通过讲述该项目来表达。</br>

直接上图,项目结果图：</br>
<img width="477" height="537" src="https://github.com/FreeLeo/Components/blob/master/screenshots/1.png"/>

该项目中有7个module  
主module:  
----app  
application  
----computer  
----player  
library  
----basic-component  
----lib-component  
----service-component  
  
可以看出该项目下，有3个可执行程序，如图：  
<img src="https://github.com/FreeLeo/Components/blob/master/screenshots/2.png"/>  
player、computer即为组件，`组件是可以独立开发测试发布的`  
  </br>
  </br>
组件如何实现独立调试？  
通过在组件工程下的gradle.properties文件中设置一个isRunAlone的变量来区分不同的场景，单独调试所必须的AndroidManifest.xml、application、入口activity等类定义在src/main/runalone下面，如：  
```
if(isRunAlone.toBoolean()){    
 apply plugin: 'com.android.application'
}else{  
 apply plugin: 'com.android.library'
}
```
  </br>
  </br>
组件彻底解耦？  
查看app/build.gradle，并没有出现complie project(':computer')或complie project(':player')，  
这说明，在开发期间，app中无法调用computer或player中的任何代码，这就实现了`组件间彻底解耦`。  
  </br>
  </br>
组件间如何调用呢？  
面向接口编程，app、computer、player均依赖service-component，查看该module会发现，其中包含ComputerService、PlayerService接口，表明对外暴露的功能接口，分别在组件computer、player中去实现。下一步，在app中引用ComputerService、PlayerService，便实现了通信。  
 </br>
 </br>
组件的加载与卸载？  
ComputerServiceImpl、PlayerServiceImpl，提供了具体的实现类之后，需要在组件加载的时候把实现类注册到Router中，Router中有一个hashmap变量，用于存放实现类。  
使用IApplicationLike.java表示组件的加载与卸载过程，即生命周期。同样，各组件提供具体实现类。  
```
interface IApplicationLike {
    fun onCreate() //加载
    fun onStop()   //卸载
}
```  
Router中还包含val components = HashMap<String,IApplicationLike>()，用于存放已注册过的组件。  
组件注册有两种方式，1在dex生成之前插入字节码，2手动调用registerComponent()
使用反射查找相应组件的IApplicationLike，代码如下：
```
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
```  
  </br>
  </br>
组件module如何加载到app项目中？ </br> 
原理仍然是implementation project，时机放在gradle assemble。  
  </br>
  </br>
  </br>
#Gradle
由于gradle很复杂，这里仅简要说明下  
gradle是groovy语言，其中包含3种对象：Gradle、Project、Settings。  
android中每个module都是一个project，使用settings实现multi-project，每个project下有很多task，如apply、clean等，assemble也是task，负责整合这些project，所以我们只需要在执行assemble task的时候，执行implementation project即可。这里又涉及到如何在gradle中自定义plugin，在plugin中做上述事情。  
  </br>
  </br>
  </br>
参考：  
* https://blog.csdn.net/innost/article/details/48228651  
* https://docs.gradle.org/current/dsl/  
* https://blog.csdn.net/eclipsexys/article/details/50973205  
* https://blog.csdn.net/mhhyoucom/article/details/79000072  
* https://www.jianshu.com/p/3ed9f4c87990  
* https://www.jianshu.com/p/1b1d77f58e84  
  </br>
  </br>
组件化好处：  
* 分而治之、降低耦合、彻底解耦、架构清晰  
* 每个业务组件都可以根据BU需求完成独立app发布
* 开发中使开发者更加轻松，开发中加快功能开发调试的速度
* 业务组件整体删除添加替换变得非常轻松，减少工程中的代码资源等冗余文件
* 业务降级，业务组件在促销高峰期间可以业务为单元关闭，保证核心业务组件的顺利执行
