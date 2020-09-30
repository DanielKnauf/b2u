package knaufdan.android.b2u.utils

import android.animation.TimeInterpolator
import android.os.Handler
import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.view.animation.LinearInterpolator
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.os.postDelayed
import androidx.databinding.BindingAdapter

@BindingAdapter(
    value = [
        "transition",
        "transitionStateListener"
    ],
    requireAll = false
)
fun ConstraintLayout.transition(
    transition: LayoutTransition,
    transitionStateListener: TransitionStateListener?
) {
    if (transition == LayoutTransition.STAY) {
        return
    }

    val bounds = ChangeBounds().apply {
        interpolator = transition.interpolator ?: LinearInterpolator()

        duration = transition.duration

        addListener(
            object : Transition.TransitionListener {
                override fun onTransitionStart(transition: Transition?) {
                    transitionStateListener?.onTransitionStarted()
                }

                override fun onTransitionPause(transition: Transition?) {
                    transitionStateListener?.onTransitionPaused()
                }

                override fun onTransitionResume(transition: Transition?) {
                    transitionStateListener?.onTransitionResumed()
                }

                override fun onTransitionCancel(transition: Transition?) {
                    transitionStateListener?.onTransitionCanceled()
                }

                override fun onTransitionEnd(transition: Transition?) {
                    transitionStateListener?.onTransitionEnded()
                }
            }
        )
    }

    val executeTransition = {
        ConstraintSet().apply {
            clone(context, transition.targetLayout)

            TransitionManager.beginDelayedTransition(
                this@transition,
                bounds
            )

            applyTo(this@transition)
        }
    }

    transitionStateListener?.onTransitionPrepared()?.run {
        Handler().postDelayed(50) {
            executeTransition()
        }
    } ?: executeTransition()
}

interface TransitionStateListener {
    /**
     * Notifies the setup of a transition before it is started (-> [onTransitionStarted])
     *
     * Note that a small delay of 50ms is added between end of [onTransitionPrepared] and execution of transition,
     * so e.g. changes to the layout can be rendered.
     */
    fun onTransitionPrepared() = Unit

    fun onTransitionStarted() = Unit
    fun onTransitionPaused() = Unit
    fun onTransitionResumed() = Unit
    fun onTransitionCanceled() = Unit
    fun onTransitionEnded() = Unit
}

data class LayoutTransition(
    @LayoutRes val targetLayout: Int,
    val duration: Long = DEFAULT_TRANSITION_DURATION,
    val interpolator: TimeInterpolator? = null
) {
    companion object {
        const val DEFAULT_TRANSITION_DURATION: Long = -1L
        val STAY: LayoutTransition = LayoutTransition(
            targetLayout = 0
        )
    }
}
