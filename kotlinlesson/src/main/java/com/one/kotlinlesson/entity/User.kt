package com.one.kotlinlesson.entity


/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 */
data class User  constructor(var username:String?,var password:String?, var code:String?)


val u = User("kotlin","passswrod","123")
val uscopy = u.copy()

fun main(){

}