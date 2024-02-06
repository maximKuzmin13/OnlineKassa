package ru.kassi.onlinekassa.presentation.kassiFragment.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class SpacesItemDecoration(private val space: Int) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.bottom = space
        if (parent.getChildLayoutPosition(view) % 2 == 0) {
            outRect.left = space
            outRect.right = space
        } else {
            outRect.right = space
        }
    }
}