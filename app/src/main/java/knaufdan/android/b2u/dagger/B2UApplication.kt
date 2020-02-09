package knaufdan.android.b2u.dagger

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import knaufdan.android.core.IContextProvider

class B2UApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    internal lateinit var contextProvider: IContextProvider

    override fun onCreate() {
        super.onCreate()
        DaggerB2UComponent
            .create()
            .inject(this)

        contextProvider.setContext(applicationContext)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}
