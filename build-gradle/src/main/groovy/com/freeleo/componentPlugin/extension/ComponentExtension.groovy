package com.freeleo.componentPlugin.extension

/**
 * 所谓Groovy脚本的Extension，实际上就是类似于Gradle的配置信息，在主项目使用自定义的Gradle插件时，可以在主项目的build.gradle脚本中通过Extension来传递一些配置、参数。
 * 创建一个Extension，只需要创建一个Groovy类即可
 */
public class ComponentExtension{
    /**
     * 是否自动注册组件，true则会使用字节码插入的方式自动注册代码
     * false的话，需要手动使用反射的方式来注册
     */
    boolean isRegisterCompoAuto = false

    /**
     * 当前组件的applicationName，用于字节码插入。
     * 当isRegisterCompoAuto==true的时候是必须的
     */
    String applicationName
}