package knaufdan.android.b2u.dagger

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton
import knaufdan.android.arch.dagger.ArchModule
import knaufdan.android.b2u.dagger.modules.ActivityModule
import knaufdan.android.b2u.dagger.modules.ViewModelModule

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        ArchModule::class
    ]
)
interface B2UComponent : AndroidInjector<B2UApplication>
