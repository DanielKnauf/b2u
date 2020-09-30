package knaufdan.android.b2u.ui.running.implementation

import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import knaufdan.android.arch.base.component.IComponent
import knaufdan.android.arch.base.component.IComponentViewModel
import knaufdan.android.arch.base.component.text.ITextComponent
import knaufdan.android.arch.base.component.text.ITextComponentFactory
import knaufdan.android.arch.base.component.text.TextConfig
import knaufdan.android.arch.databinding.views.TextGravity
import knaufdan.android.b2u.datepicker.DayOfMonth
import knaufdan.android.b2u.datepicker.Month
import knaufdan.android.b2u.datepicker.Year
import knaufdan.android.b2u.datepicker.daysBetween
import knaufdan.android.b2u.datepicker.today
import knaufdan.android.b2u.ui.flickeringpoints.IFlickeringPointsComponent
import knaufdan.android.b2u.ui.flickeringpoints.IFlickeringPointsComponentFactory

class RunningViewModel(
    factory: ITextComponentFactory,
    flickeringPointsFactory: IFlickeringPointsComponentFactory,
    endDate: Triple<DayOfMonth, Month, Year>,
    private val onFinished: () -> Unit
) : IComponentViewModel, LifecycleObserver {
    val components = MutableLiveData<List<IComponent<*>>>()
    private val text: MutableLiveData<Spannable> = MutableLiveData()
    private val days = today.daysBetween(endDate)

    private val textComponent: ITextComponent by lazy {
        factory.create(
            TextConfig(
                text = text,
                textGravity = TextGravity.CENTER
            )
        )
    }

    private val flickeringPointsComponent: IFlickeringPointsComponent by lazy {
        flickeringPointsFactory.create()
    }

    private var handler: CountDownTimer? = null

    init {
        components.value = listOf(
            flickeringPointsComponent,
            textComponent
        )
        text.value = SpannableString(days.toString())
    }

    override fun onAttach() {
        super.onAttach()

        handler?.cancel()
        displayDays(days)
    }

    override fun onDetach() {
        handler?.cancel()

        super.onDetach()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumed() {
        displayDays(days)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        handler?.cancel()
    }

    private fun displayDays(days: Int) {
        var currentDays = days + 1
        handler = object : CountDownTimer((currentDays.toLong() * 1000), 1000) {
            override fun onFinish() {
                this@RunningViewModel.onFinished()
            }

            override fun onTick(millisUntilFinished: Long) {
                currentDays--
                text.value = SpannableString("$currentDays")
            }
        }.start()
    }
}
