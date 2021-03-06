package com.one.kotlinlesson.ui

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.one.kotlinlesson.BaseViewHolder
import com.one.kotlinlesson.R
import com.one.kotlinlesson.entity.Lesson
import com.one.kotlinlesson.ui.LessonAdapter.LessonViewHolder
import java.util.*

class LessonAdapter : RecyclerView.Adapter<LessonViewHolder>() {
    private var list: List<Lesson> = ArrayList()
    fun updateAndNotify(list: List<Lesson>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.onCreate(parent)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    /**
     * 静态内部类
     */
    class LessonViewHolder internal constructor(itemView: View) :
            BaseViewHolder(itemView) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun onBind(lesson: Lesson) {
            setText(R.id.tv_date, lesson.data ?: "日期待定")
            setText(R.id.tv_content, lesson.content)
            val state = lesson.state
            if (state != null) {
                setText(R.id.tv_state, state.stateName())
                val colorRes = when (state) {
                    Lesson.State.PLAYBACK -> R.color.playback
                    Lesson.State.LIVE -> R.color.live
                    Lesson.State.WAIT -> R.color.wait
                }
                val backgroundColor: Int = itemView.context.getColor(colorRes)
                getView<TextView>(R.id.tv_state).setBackgroundColor(backgroundColor)
            }
        }

        companion object {
            fun onCreate(parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false))
            }
        }
    }
}