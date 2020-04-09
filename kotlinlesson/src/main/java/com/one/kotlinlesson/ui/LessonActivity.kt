package com.one.kotlinlesson.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.one.kotlinlesson.BaseView
import com.one.kotlinlesson.R
import com.one.kotlinlesson.entity.Lesson
import com.one.kotlinlesson.utils.firstChild
import kotlinx.android.synthetic.main.activity_lesson.*

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter?>,
        Toolbar.OnMenuItemClickListener {
    private val presenter = LessonPresenter(this)
    private val lessonAdapter = LessonAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = lessonAdapter
        list.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        swipe_refresh_layout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { presenter.fetchData() })
        swipe_refresh_layout.isRefreshing = true
        presenter.fetchData()

        swipe_refresh_layout.firstChild
    }


    fun showResult(lessons: List<Lesson>) {
        lessonAdapter.updateAndNotify(lessons)
        swipe_refresh_layout.isRefreshing = false
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        presenter.showPlayBack()
        return false
    }

    override fun getPresenter(): LessonPresenter? {
        return presenter
    }

}