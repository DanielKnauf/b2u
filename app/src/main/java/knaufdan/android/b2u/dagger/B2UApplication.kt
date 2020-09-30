package knaufdan.android.b2u.dagger

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import knaufdan.android.core.IContextProvider
import knaufdan.android.core.preferences.ISharedPrefService
import javax.inject.Inject

class B2UApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    internal lateinit var contextProvider: IContextProvider

    @Inject
    internal lateinit var sharedPrefService: ISharedPrefService

    override fun onCreate() {
        super.onCreate()
        DaggerB2UComponent
            .create()
            .inject(this)

        contextProvider.setContext(applicationContext)

        sharedPrefService.configure {
            setLocation("knaufdan.android.b2u")
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}
