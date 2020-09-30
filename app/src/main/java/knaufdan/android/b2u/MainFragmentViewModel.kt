package knaufdan.android.b2u

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import knaufdan.android.arch.base.component.BindingKey
import knaufdan.android.arch.base.component.IComponent
import knaufdan.android.arch.base.component.LayoutRes
import knaufdan.android.arch.mvvm.implementation.AndroidBaseViewModel
import knaufdan.android.b2u.datepicker.DayOfMonth
import knaufdan.android.b2u.datepicker.Month
import knaufdan.android.b2u.datepicker.Year
import knaufdan.android.b2u.datepicker.today
import knaufdan.android.b2u.ui.input.IInputComponent
import knaufdan.android.b2u.ui.input.IInputComponentFactory
import knaufdan.android.b2u.ui.running.IRunningComponentFactory
import knaufdan.android.b2u.ui.splash.ISplashComponent
import knaufdan.android.b2u.ui.splash.ISplashComponentFactory
import knaufdan.android.b2u.utils.LayoutTransition
import knaufdan.android.b2u.utils.TransitionStateListener
import javax.inject.Inject

/**
 * TODO:
 * - save current state here : Running, Choosing
 * - handle button click according to state
 */
class MainFragmentViewModel @Inject constructor(
    inputFactory: IInputComponentFactory,
    splashFactory: ISplashComponentFactory,
    private val runningFactory: IRunningComponentFactory
) : AndroidBaseViewModel(), TransitionStateListener {
    val components = MutableLiveData<List<IComponent<*>>>()
    val transition: MutableLiveData<LayoutTransition> = MutableLiveData(LayoutTransition.STAY)
    val buttonText: MutableLiveData<String> = MutableLiveData("Start")
    val buttonEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    private val inputComponent: IInputComponent by lazy { inputFactory.create(this::onDatePicked) }
    private val splashComponent: ISplashComponent by lazy { splashFactory.create(this::onSplashFinished) }
    private var chosenDate: Triple<DayOfMonth, Month, Year> = today

    override fun onFirstStart(bundle: Bundle?) {
        super.onFirstStart(bundle)
        components.value = listOf(splashComponent)
    }

    fun onButtonClicked() {
        transition.value = when (transition.value) {
            runningTimerTransition -> setupTimerTransition
            else -> runningTimerTransition
        }
    }

    override fun onTransitionEnded() {
        if (transition.value == setupTimerTransition) {
            buttonText.postValue("Start")
            components.value = listOf(inputComponent)
            return
        }

        if (transition.value == runningTimerTransition) {
            buttonText.postValue("S")
            components.value = listOf(runningFactory.create(chosenDate, this::onRunningFinished))
        }
    }

    override fun onTransitionPrepared() {
        buttonText.value = ""
        components.value = listOf(empty)
    }

    private fun onDatePicked(
        dayOfMonth: DayOfMonth,
        month: DayOfMonth,
        year: Year
    ) {
        chosenDate = Triple(
            first = dayOfMonth,
            second = month,
            third = year
        )

        buttonEnabled.postValue(chosenDate != today)
    }

    private fun onRunningFinished() {
        transition.value = setupTimerTransition
    }

    private fun onSplashFinished() {
        components.value = listOf(inputComponent)
    }

    companion object {
        private val empty: IComponent<Unit> = object : IComponent<Unit> {
            override fun getLayoutRes(): LayoutRes = R.layout.empty

            override fun getBindingKey(): BindingKey = BR.viewModel

            override fun getDataSource() = Unit
        }

        private val runningTimerTransition = LayoutTransition(
            targetLayout = R.layout.fragment_main_alt,
            duration = 1000L
        )

        private val setupTimerTransition = LayoutTransition(
            targetLayout = R.layout.fragment_main,
            duration = 1000L
        )
    }
}
