package knaufdan.android.b2u.ui.flickeringpoints.implementation

import knaufdan.android.b2u.ui.flickeringpoints.IFlickeringPointsComponent
import knaufdan.android.b2u.ui.flickeringpoints.IFlickeringPointsComponentFactory

class FlickeringPointsComponentFactory : IFlickeringPointsComponentFactory {
    override fun create(): IFlickeringPointsComponent = FlickeringPointsComponent()
}
