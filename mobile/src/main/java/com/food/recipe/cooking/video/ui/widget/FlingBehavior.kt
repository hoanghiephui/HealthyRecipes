package com.food.recipe.cooking.video.ui.widget

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

/**
 * Created by hoanghiep on 10/31/17.
 */
class FlingBehavior : AppBarLayout.Behavior {
    private var isPositive: Boolean = false

    constructor() {}

    constructor(paramContext: Context, paramAttributeSet: AttributeSet) : super(paramContext, paramAttributeSet) {}

    override fun onNestedFling(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View,
                               velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        var mVelocityY = velocityY
        var mConsumed = consumed
        if (mVelocityY > 0 && !isPositive || mVelocityY < 0 && isPositive) {
            mVelocityY = mVelocityY * -1
        }
        if (target is RecyclerView && mVelocityY < 0) {
            val firstChild = target.getChildAt(0)
            val childAdapterPosition = target.getChildAdapterPosition(firstChild)
            mConsumed = childAdapterPosition > TOP_CHILD_FLING_THRESHOLD
        } else if (target is RecyclerView && mVelocityY < 0) {
            val firstChild = target.getChildAt(0)
            val childAdapterPosition = target.getChildAdapterPosition(firstChild)
            mConsumed = childAdapterPosition > TOP_CHILD_FLING_THRESHOLD
        }
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, mVelocityY, mConsumed)
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        isPositive = dy > 0
    }

    companion object {
        private val TOP_CHILD_FLING_THRESHOLD = 3
    }
}