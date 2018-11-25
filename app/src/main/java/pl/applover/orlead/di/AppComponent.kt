package pl.applover.orlead.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import pl.applover.orlead.main.MainApp
import pl.applover.orlead.main.route.RoutePresenter
import pl.applover.orlead.main.vehicle.VehiclePresenter
import javax.inject.Singleton

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
@Singleton
@Component(modules = [BackendModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MainApp): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(presenter: VehiclePresenter)
    fun inject(presenter: RoutePresenter)

}