package com.one.kotlinlearn1.smaple

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 */
class User constructor(){

    var username : String? = null
    var password :String? = null
    var code :String? = null


    constructor(username:String?,password:String?,code:String?) : this(){
        this.username = username
        this.password = password
        this.code = code
    }

}