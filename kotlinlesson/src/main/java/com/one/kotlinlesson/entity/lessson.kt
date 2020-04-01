package com.one.kotlinlesson.entity

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 */
class Lesson {

    var data: String? = null
    var content: String? = null
    var state: State? = null

    constructor(data: String?, content: String?, state: State?) {
        this.data = data
        this.content = content
        this.state = state
    }


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