package knaufdan.android.b2u.dagger.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import knaufdan.android.b2u.MainFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributesMainFragmentInjector(): MainFragment
}