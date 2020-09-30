package knaufdan.android.b2u.ui.input.implementation

import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import androidx.core.os.postDelayed
import androidx.lifecycle.MutableLiveData
import knaufdan.android.arch.base.component.IComponent
import knaufdan.android.arch.base.component.IComponentViewModel
import knaufdan.android.arch.base.component.text.ITextComponent
import knaufdan.android.arch.base.component.text.ITextComponentFactory
import knaufdan.android.arch.base.component.text.TextConfig
import knaufdan.android.arch.databinding.livedata.add
import knaufdan.android.arch.databinding.livedata.addAll
import knaufdan.android.arch.databinding.livedata.clear
import knaufdan.android.arch.databinding.livedata.remove
import knaufdan.android.arch.databinding.views.TextGravity
import knaufdan.android.b2u.R
import knaufdan.android.b2u.datepicker.DayOfMonth
import knaufdan.android.b2u.datepicker.IDatePickerService
import knaufdan.android.b2u.datepicker.Month
import knaufdan.android.b2u.datepicker.Year
import knaufdan.android.b2u.datepicker.today
import knaufdan.android.core.preferences.ISharedPrefService

class InputViewModel(
    private val datePickerService: IDatePickerService,
    factory: ITextComponentFactory,
    private val sharedPrefs: ISharedPrefService,
    private val onDatePicked: (DayOfMonth, Month, Year) -> Unit
) : IComponentViewModel {
    val text = MutableLiveData<String>("Input")
    val components = MutableLiveData<List<IComponent<*>>>()
    private var chosenDate: Triple<DayOfMonth, Month, Year>
        get() = sharedPrefs.getString(key = "chosenDate").toLocalDate()
        set(value) = sharedPrefs.put(
            key = "chosenDate",
            value = "${value.first}:${value.second}:${value.third}"
        )
    private val chooseDateText: MutableLiveData<Spannable> =
        MutableLiveData(SpannableString("Choose Date") as Spannable)

    private val input: ITextComponent by lazy {
        factory.create(
            TextConfig(
                text = chooseDateText,
                textColor = R.color.colorPrimary,
                textGravity = TextGravity.CENTER,
                onTextClicked = { onChooseDateClicked() },
                marginTop = R.dimen.splash_text_margin,
                marginBottom = R.dimen.splash_text_margin
            )
        )
    }

    private val text2: ITextComponent by lazy {
        factory.create(
            TextConfig(
                text = "text2",
                textColor = R.color.colorAccent,
                textGravity = TextGravity.CENTER,
                marginTop = R.dimen.splash_text_margin,
                marginBottom = R.dimen.splash_text_margin
            )
        )
    }

    private val text3: ITextComponent by lazy {
        factory.create(
            TextConfig(
                text = "text3",
                textColor = R.color.colorPrimaryDark,
                textGravity = TextGravity.CENTER,
                marginTop = R.dimen.splash_text_margin,
                marginBottom = R.dimen.splash_text_margin
            )
        )
    }

    init {
        chooseDateText.value = SpannableString(
            "${chosenDate.first} | ${chosenDate.second + 1} | ${chosenDate.third}"
        )

        onDatePicked(chosenDate.first, chosenDate.second, chosenDate.third)
    }

    override fun onAttach() {
        components.value = mutableListOf(input)
    }

    private fun onChooseDateClicked() {
        datePickerService.showDatePicker(
            date = chosenDate,
            onDatePicked = { d, m, y ->
                chosenDate = Triple(
                    first = d,
                    second = m,
                    third = y
                )

                chooseDateText.value = SpannableString(
                    "${chosenDate.first} | ${chosenDate.second + 1} | ${chosenDate.third}"
                )

                onDatePicked(d, m, y)

                Handler().postDelayed(
                    1000
                ) {
                    components.value = mutableListOf(
                        input,
                        text3,
                        text2,
                        text2
                    )
                }

                Handler().postDelayed(
                    2000
                ) {
                    components.value = mutableListOf(
                        input,
                        text2,
                        text2,
                        text3
                    )
                }

                Handler().postDelayed(
                    6000
                ) {
                    components.clear()

                    components.addAll(
                        text3,
                        text2,
                        input
                    )

                    components.remove(2)
                }

/*
                Handler().postDelayed(
                    4500
                ) {
                    components.value = mutableListOf(
                        input,
                        text3,
                        text2
                    )
                }


                Handler().postDelayed(
                    7000
                ) {
                    components.value = mutableListOf(
                        input,
                        text3,
                        text3,
                        text2,
                        text3,
                        text2
                    )
                }

                Handler().postDelayed(
                    9000
                ) {
                    components.value = mutableListOf(
                        input,
                        text3
                    )
                }*/
            }
        )
    }

    companion object {
        private fun String.toLocalDate(): Triple<DayOfMonth, Month, Year> =
            if (this.isBlank()) today
            else this.split(":").run {
                val dateItems = this.mapNotNull { item -> item.toIntOrNull() }

                if (dateItems.size < 3) {
                    return@run today
                }

                Triple(
                    first = dateItems[0],
                    second = dateItems[1],
                    third = dateItems[2]
                )
            }
    }
}
