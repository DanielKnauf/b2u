package knaufdan.android.b2u.ui.flickeringpoints.implementation

import androidx.lifecycle.MutableLiveData
import knaufdan.android.arch.base.component.IComponentViewModel
import knaufdan.android.arch.databinding.views.FadingDirection
import java.util.Timer
import java.util.TimerTask

class FlickeringPointsViewModel : IComponentViewModel {
    val fadeInLeft: MutableLiveData<FadingDirection> = MutableLiveData(FadingDirection.In())
    val fadeInRight: MutableLiveData<FadingDirection> = MutableLiveData(FadingDirection.Out())

    private var handler: Timer? = null

    override fun onAttach() {
        super.onAttach()

        handler?.cancel()

        handler = Timer().apply {
            scheduleAtFixedRate(
                object : TimerTask() {
                    override fun run() {
                        fadeInLeft.postValue(fadeInLeft.value.getOpposite())

                        fadeInRight.postValue(fadeInRight.value.getOpposite())
                    }
                },
                FLICKERING_DURATION,
                FLICKERING_DURATION
            )
        }
    }

    override fun onDetach() {
        handler?.cancel()

        super.onDetach()
    }

    companion object {
        private const val FLICKERING_DURATION: Long = 900

        private fun FadingDirection?.getOpposite(): FadingDirection? =
            when (this) {
                is FadingDirection.In -> FadingDirection.Out()
                is FadingDirection.Out -> FadingDirection.In()
                else -> null
            }
    }
}
