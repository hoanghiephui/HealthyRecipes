package com.food.recipe.cooking.video.ui.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.widget.AppCompatImageView
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.food.recipe.cooking.video.DataBanner
import com.food.recipe.cooking.video.R
import com.freegeek.android.materialbanner.holder.Holder

/**
 * Created by hoanghiep on 10/31/17.
 */
class ImageHolderView : Holder<DataBanner> {
    private var imageView: AppCompatImageView? = null
    private var progressBar: ProgressBar? = null

    override fun createView(context: Context): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_banner, FrameLayout(context), false)
        imageView = view.findViewById(R.id.imageView)
        progressBar = view.findViewById(R.id.progress)
        imageView!!.scaleType = ImageView.ScaleType.CENTER_CROP
        return view
    }

    override fun UpdateUI(context: Context, position: Int, data: DataBanner) {
        val simpleTarget = object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>) {
                progressBar!!.visibility = View.GONE
                imageView!!.setImageBitmap(resource)
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                progressBar!!.visibility = View.GONE
            }
        }
        Glide.with(context)
                .asBitmap()
                .load(data.url)
                .into(simpleTarget)
    }

    companion object {
        private val TAG = ImageHolderView::class.java.simpleName
    }
}