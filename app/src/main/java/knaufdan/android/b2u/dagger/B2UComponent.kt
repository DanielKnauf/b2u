package knaufdan.android.b2u.dagger

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import knaufdan.android.arch.dagger.ArchModule
import knaufdan.android.b2u.dagger.modules.ActivityModule
import knaufdan.android.b2u.dagger.modules.FragmentModule
import knaufdan.android.b2u.dagger.modules.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        ArchModule::class
    ]
)
interface B2UComponent : AndroidInjector<B2UApplication>