package knaufdan.android.b2u.ui.splash

interface ISplashComponentFactory {
    fun create(
        onFinish: () -> Unit
    ): ISplashComponent
}
