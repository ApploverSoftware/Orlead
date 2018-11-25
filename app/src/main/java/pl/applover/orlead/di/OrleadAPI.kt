package pl.applover.orlead.di

import io.reactivex.Single
import okhttp3.ResponseBody
import pl.applover.orlead.model.RequestVehicle
import pl.applover.orlead.model.Vehicle
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
interface OrleadAPI {
    @POST("vehicles")
    fun postVehicle(@Body vehicle: RequestVehicle): Single<Response<ResponseBody>>

    @GET("vehicles")
    fun getVehicle(@Query("plate_number") plate_number: String): Single<Response<ResponseBody>>
}