package knaufdan.android.b2u.dagger.modules

import dagger.Module
import dagger.Provides
import knaufdan.android.arch.base.component.text.ITextComponentFactory
import knaufdan.android.b2u.datepicker.IDatePickerService
import knaufdan.android.b2u.ui.flickeringpoints.IFlickeringPointsComponentFactory
import knaufdan.android.b2u.ui.flickeringpoints.implementation.FlickeringPointsComponentFactory
import knaufdan.android.b2u.ui.input.IInputComponentFactory
import knaufdan.android.b2u.ui.input.implementation.InputComponentFactory
import knaufdan.android.b2u.ui.running.IRunningComponentFactory
import knaufdan.android.b2u.ui.running.implementation.RunningComponentFactory
import knaufdan.android.b2u.ui.splash.ISplashComponentFactory
import knaufdan.android.b2u.ui.splash.implementation.SplashComponentFactory
import knaufdan.android.core.preferences.ISharedPrefService
import javax.inject.Singleton

@Module
class ComponentFactoryModule {
    @Provides
    @Singleton
    fun provideInputComponentFactory(
        datePickerService: IDatePickerService,
        sharedPrefService: ISharedPrefService,
        factory: ITextComponentFactory
    ): IInputComponentFactory = InputComponentFactory(
        datePickerService = datePickerService,
        sharedPrefService = sharedPrefService,
        factory = factory
    )

    @Provides
    @Singleton
    fun provideRunningComponentFactory(
        textFactory: ITextComponentFactory,
        flickeringPointsComponentFactory: IFlickeringPointsComponentFactory
    ): IRunningComponentFactory =
        RunningComponentFactory(
            textFactory = textFactory,
            flickeringPointsComponentFactory = flickeringPointsComponentFactory
        )

    @Provides
    @Singleton
    fun provideSplashComponentFactory(
        factory: ITextComponentFactory
    ): ISplashComponentFactory = SplashComponentFactory(
        factory = factory
    )

    @Provides
    @Singleton
    fun provideFlickeringPointsComponentFactory(): IFlickeringPointsComponentFactory =
        FlickeringPointsComponentFactory()
}
