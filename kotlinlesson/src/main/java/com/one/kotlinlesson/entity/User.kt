package com.one.kotlinlesson.entity

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 */
class User :Any{


    /**
     * 该注解让编译器 不在生成 set get 函数了, 可以通过访问属性的方式调用
     */
    @JvmField
    var username :String?=null
    var password:String? = null
    var code:String? = null


    constructor()

    constructor(username:String?,password:String?, code:String?){
        this.username = username
        this.password = password
        this.code = code
    }

}