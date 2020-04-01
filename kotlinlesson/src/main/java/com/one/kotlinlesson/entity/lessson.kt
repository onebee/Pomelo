package com.one.kotlinlesson.entity

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 */
data class Lesson(var data: String?, var content: String?, var state: State?) {


    enum class State {

        PLAYBACK {
            override fun stateName(): String {
                return "有回放"
            }
        },
        LIVE {
            override fun stateName(): String {
                return "正在直播"
            }
        }
        ,
        WAIT {
            override fun stateName(): String {
                return "等待中"
            }
        };

        abstract fun stateName(): String
    }
}