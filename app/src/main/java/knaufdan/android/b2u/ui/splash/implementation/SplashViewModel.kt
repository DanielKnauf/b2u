package knaufdan.android.b2u.ui.splash.implementation

import android.os.Handler
import androidx.core.os.postDelayed
import androidx.lifecycle.MutableLiveData
import knaufdan.android.arch.base.component.BindingKey
import knaufdan.android.arch.base.component.IComponent
import knaufdan.android.arch.base.component.IComponentViewModel
import knaufdan.android.arch.base.component.LayoutRes
import knaufdan.android.arch.base.component.text.ITextComponent
import knaufdan.android.arch.base.component.text.ITextComponentFactory
import knaufdan.android.arch.base.component.text.TextConfig
import knaufdan.android.arch.databinding.views.TextGravity
import knaufdan.android.b2u.BR
import knaufdan.android.b2u.R

class SplashViewModel(
    factory: ITextComponentFactory,
    private val onFinish: () -> Unit
) : IComponentViewModel {
    val components: MutableLiveData<MutableList<IComponent<*>>> = MutableLiveData()

    private val firstText: ITextComponent by lazy {
        factory.create(
            TextConfig(
                text = "Thunder and lightning.\nEnter three witches",
                textColor = R.color.splash_text_color,
                marginTop = R.dimen.splash_text_margin,
                textSize = R.dimen.splash_text_size,
                textGravity = TextGravity.CENTER
            )
        )
    }

    private val imageComponent: IComponent<Unit> by lazy {
        object : IComponent<Unit> {
            override fun getLayoutRes(): LayoutRes = R.layout.image

            override fun getBindingKey(): BindingKey = BR.viewModel

            override fun getDataSource() = Unit
        }
    }

    private val secondText: ITextComponent by lazy {
        factory.create(
            TextConfig(
                text = "When shall we three meet again\nIn thunder, lightning, or in rain?",
                textColor = R.color.splash_text_color,
                marginTop = R.dimen.splash_text_margin,
                textSize = R.dimen.splash_text_size,
                textGravity = TextGravity.CENTER
            )
        )
    }

    override fun onAttach() {
        Handler().postDelayed(500) {
            components.value = mutableListOf(firstText)
        }

        Handler().postDelayed(1500) {
            val value = components.value ?: mutableListOf()
            value.add(imageComponent)
            components.value = value
        }

        Handler().postDelayed(2500) {
            val value = components.value ?: mutableListOf()
            value.add(secondText)
            components.value = value
        }

        Handler().postDelayed(
            4000
        ) {
            onFinish()
        }
    }
}
