package com.hieupham.data.source.remote.api.service;

import com.hieupham.data.source.remote.api.response.InfoResponse;
import com.hieupham.data.source.remote.api.response.TransactionResponse;
import com.hieupham.data.source.remote.api.response.TransactionsResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hieupham on 5/14/18.
 */

public interface BitmarkApi {

    @GET("txs")
    Single<TransactionsResponse> getTransactions(@Query("block_number") long blockNumber,
            @Query("to") String to, @Query("limit") int limit, @Query("asset") boolean asset,
            @Query("block") boolean block);

    @GET("txs/{id}")
    Single<TransactionResponse> getTransaction(@Path("id") String id, @Query("asset") boolean asset,
            @Query("block") boolean block);

    @GET("info")
    Single<InfoResponse> getInfo();
}
