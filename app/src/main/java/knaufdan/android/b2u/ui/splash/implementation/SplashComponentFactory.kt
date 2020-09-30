package knaufdan.android.b2u.ui.splash.implementation

import knaufdan.android.arch.base.component.text.ITextComponentFactory
import knaufdan.android.b2u.ui.splash.ISplashComponent
import knaufdan.android.b2u.ui.splash.ISplashComponentFactory

class SplashComponentFactory(
    private val factory: ITextComponentFactory
) : ISplashComponentFactory {
    override fun create(
        onFinish: () -> Unit
    ): ISplashComponent =
        SplashComponent(
            factory = factory,
            onFinish = onFinish
        )
}
