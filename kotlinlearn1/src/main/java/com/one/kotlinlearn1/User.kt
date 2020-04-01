package com.one.kotlinlearn1

/**
 * @author  diaokaibin@gmail.com on 2020/3/6.
 */
class User :Any{

    @JvmField
    var username: String? = null


    var age: Int = 9

    constructor()

    constructor(username:String?,age:Int){
        this.username=username
        this.age=age
    }


}