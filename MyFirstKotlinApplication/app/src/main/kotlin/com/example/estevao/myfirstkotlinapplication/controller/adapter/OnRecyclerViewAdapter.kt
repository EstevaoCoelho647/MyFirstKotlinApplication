package com.example.estevao.myfirstkotlinapplication.controller.adapter

import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/**
 * Created by estevao on 09/09/16.
 */
fun RecyclerView.setOnItemClickListener(listener: OnItemClickListener) {
    this.addOnItemTouchListener(ClickDetectorItemTouchListener(this, listener))
}

class ClickDetectorItemTouchListener(val recyclerView: RecyclerView, val listener: OnItemClickListener) : RecyclerView.OnItemTouchListener, GestureDetector.SimpleOnGestureListener() {
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }

    val gestureDetector: GestureDetectorCompat = GestureDetectorCompat(recyclerView.context, this)

    override fun onInterceptTouchEvent(recyclerView: RecyclerView?, e: MotionEvent?) = gestureDetector.onTouchEvent(e)

    override fun onTouchEvent(recyclerView: RecyclerView?, e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {

        if (e != null) {

            val view = recyclerView.findChildViewUnder(e.x, e.y)
            val position = recyclerView.getChildAdapterPosition(view)
            val id = recyclerView.getChildItemId(view)

            listener.onItemClick(recyclerView, view, position, id)
        }

        return false
    }
}

interface OnItemClickListener {
    fun onItemClick(recyclerView: RecyclerView, v: View, position: Int, id: Long)
}