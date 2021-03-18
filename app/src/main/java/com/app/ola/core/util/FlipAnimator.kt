package com.app.ola.core.util

import android.R
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.view.View




/**
 * Created by daniel on 10/03/2017.
 */

object FlipAnimator {
    private val TAG = FlipAnimator::class.java.simpleName
    private var buttonIn: AnimatorSet? = null
    private var topOut: AnimatorSet? = null
    private var buttonOut: AnimatorSet? = null
    private var topIn: AnimatorSet? = null

    /**
     * Performs flip animation on two views
     */
    fun flipView(context: Context, back: View, front: View, showFront: Boolean) {
        buttonIn = AnimatorInflater.loadAnimator(context, com.app.ola.R.animator.card_flip_button_in) as AnimatorSet
        topOut = AnimatorInflater.loadAnimator(context, com.app.ola.R.animator.card_flip_top_out) as AnimatorSet
        buttonOut = AnimatorInflater.loadAnimator(context, com.app.ola.R.animator.card_flip_button_out) as AnimatorSet
        topIn = AnimatorInflater.loadAnimator(context, com.app.ola.R.animator.card_flip_top_in) as AnimatorSet

        val showFrontAnim = AnimatorSet()
        val showBackAnim = AnimatorSet()

        buttonIn!!.setTarget(back)
        topOut!!.setTarget(front)
        showFrontAnim.playTogether(buttonIn, topOut)

        buttonOut!!.setTarget(back)
        topIn!!.setTarget(front)
        showBackAnim.playTogether(topIn, buttonOut)

        if (showFront) {
            showFrontAnim.start()
        } else {
            showBackAnim.start()
        }
    }
}