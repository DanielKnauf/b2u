package knaufdan.android.b2u.dagger.modules

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import knaufdan.android.b2u.utils.DatePickerService
import knaufdan.android.b2u.utils.IDatePickerService

@Module
class B2UModule {

    @Singleton
    @Provides
    fun provideDatePickerService(datePickerService: DatePickerService): IDatePickerService =
        datePickerService
}
