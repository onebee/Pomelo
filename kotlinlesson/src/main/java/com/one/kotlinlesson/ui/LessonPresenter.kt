package com.one.kotlinlesson.ui

import com.google.gson.reflect.TypeToken
import com.one.core.http.EntityCallback
import com.one.kotlinlesson.http.HttpClient
import com.one.kotlinlesson.entity.Lesson
import com.one.kotlinlesson.utils.Utils

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 */
class LessonPresenter(private val activity: LessonActivity) {

    companion object {
        /**
         * 编译器常量
         */
        const val LESSON_PATH = "lessons"
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
        lessons.forEach {
            if (it.state == Lesson.State.PLAYBACK) {
                playbackLessons.add(it)
            }
        }
        activity.showResult(playbackLessons)
    }

}



