package knaufdan.android.b2u.dagger.modules

import dagger.Module
import dagger.Provides
import knaufdan.android.b2u.datepicker.IDatePickerService
import knaufdan.android.b2u.datepicker.implementation.DatePickerService
import javax.inject.Singleton

@Module
class B2UModule {

    @Singleton
    @Provides
    fun provideDatePickerService(datePickerService: DatePickerService): IDatePickerService =
        datePickerService
}
