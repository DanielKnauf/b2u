package knaufdan.android.b2u.dagger.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import knaufdan.android.b2u.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributesMainActivityInjector(): MainActivity
}