package com.one.kotlinlesson.ui

import com.google.gson.reflect.TypeToken
import com.one.core.http.EntityCallback
import com.one.kotlinlesson.http.HttpClient
import com.one.kotlinlesson.entity.Lesson
import com.one.kotlinlesson.utils.Utils

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 */
class LessonPresenter {


    companion object {
        /**
         * 编译器常量
         */
        const val LESSON_PATH = "lessons"
    }

    private val activity: LessonActivity

    constructor(activity: LessonActivity) {
        this.activity = activity
    }

    private var lessons: List<Lesson> = ArrayList()


    private val type = object : TypeToken<List<Lesson>>() {}.type

    fun fetchData() {

        HttpClient.get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onSuccess(entity: List<Lesson>) {
                this@LessonPresenter.lessons = entity
                activity.runOnUiThread { activity.showResult(lessons); }

            }

            override fun onFailure(message: String?) {

                activity.runOnUiThread {     Utils.toash(message) }

            }

        })
    }

    fun showPlayBack() {
        val playbackLessons = ArrayList<Lesson>()

        for (lesson: Lesson in lessons) {
            if (lesson.state == Lesson.State.PLAYBACK) {
                playbackLessons.add(lesson)

            }
            activity.showResult(lessons)
        }
    }

}



