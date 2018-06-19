package com.hieupham.data.repository;

import com.hieupham.data.data.DataProvider;
import com.hieupham.data.source.Mapper;
import com.hieupham.data.source.TransactionRepositoryImpl;
import com.hieupham.data.source.local.TransactionLocalDataSource;
import com.hieupham.data.source.remote.TransactionRemoteDataSource;
import com.hieupham.data.source.remote.api.response.TransactionResponse;
import com.hieupham.data.source.remote.api.response.TransactionsResponse;
import com.hieupham.data.utils.common.Constant;
import com.hieupham.domain.entity.CompositeTransaction;
import com.hieupham.domain.entity.CompositeTransactions;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

/**
 * Created by hieupham on 6/6/18.
 * Follow naming convention : [UnitOfWork_StateUnderTest_ExpectedBehavior]
 */

public class TransactionRepositoryTest extends RepositoryTest {

    @Mock
    TransactionLocalDataSource localDataSource;

    @Mock
    TransactionRemoteDataSource remoteDataSource;

    @Mock
    Mapper mapper;

    @InjectMocks
    TransactionRepositoryImpl repository;

    @Test
    public void testGetTransactions_NoError_DataIsReturn() {

        final CompositeTransactions expectedResult = DataProvider.compositeTransactions1();
        final TransactionsResponse expectedResponse = DataProvider.transactionsResponse1();
        final long blockNumber = DataProvider.blockData1().getNumber();
        when(remoteDataSource.getTransactions(blockNumber, Constant.LIMITED_RESULT)).thenReturn(
                Single.just(expectedResponse));
        when(localDataSource.save(expectedResponse)).thenReturn(Completable.complete());
        when(mapper.map()).thenCallRealMethod();
        Maybe<CompositeTransactions> streamGetTransactions =
                repository.getTransactions(blockNumber);

        TestObserver<CompositeTransactions> observer = new TestObserver<>();
        streamGetTransactions.subscribe(observer);

        observer.assertValue(expectedResult);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void testGetTransactions_NetworkError_DataIsReturn() {

        final CompositeTransactions expectedResult = DataProvider.compositeTransactions1();
        final TransactionsResponse expectedResponse = DataProvider.transactionsResponse1();
        final long blockNumber = DataProvider.blockData1().getNumber();
        final SocketTimeoutException expectedException = DataProvider.timeoutException();
        when(remoteDataSource.getTransactions(blockNumber, Constant.LIMITED_RESULT)).thenReturn(
                Single.error(expectedException));
        when(localDataSource.getTransactions(blockNumber, Constant.LIMITED_RESULT)).thenReturn(
                Maybe.just(expectedResponse));
        when(mapper.map()).thenCallRealMethod();
        Maybe<CompositeTransactions> streamGetTransactions =
                repository.getTransactions(blockNumber);

        TestObserver<CompositeTransactions> observer = new TestObserver<>();
        streamGetTransactions.subscribe(observer);

        observer.assertValue(expectedResult);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void testGetTransactions_DatabaseError_ExceptionIsThrown() {

        final long blockNumber = DataProvider.blockData1().getNumber();
        final SocketTimeoutException networkException = DataProvider.timeoutException();
        final SQLException expectedException = DataProvider.sqlException();
        when(remoteDataSource.getTransactions(blockNumber, Constant.LIMITED_RESULT)).thenReturn(
                Single.error(networkException));
        when(localDataSource.getTransactions(blockNumber, Constant.LIMITED_RESULT)).thenReturn(
                Maybe.error(expectedException));
        when(mapper.map()).thenCallRealMethod();
        Maybe<CompositeTransactions> streamGetTransactions =
                repository.getTransactions(blockNumber);

        TestObserver<CompositeTransactions> observer = new TestObserver<>();
        streamGetTransactions.subscribe(observer);

        observer.assertError(expectedException.getClass());
        observer.assertErrorMessage(expectedException.getMessage());
        observer.assertNotComplete();
        observer.assertTerminated();
    }

    @Test
    public void testGetTransaction_NoError_DataIsReturn() {

        final CompositeTransaction expectedResult = DataProvider.compositeTransaction1();
        final TransactionResponse expectedResponse = DataProvider.transactionResponse1();
        final String id = DataProvider.transaction1().getId();
        when(remoteDataSource.getTransaction(id)).thenReturn(Single.just(expectedResponse));
        when(localDataSource.save(expectedResponse)).thenReturn(Completable.complete());
        when(mapper.map()).thenCallRealMethod();
        Maybe<CompositeTransaction> streamGetTransaction = repository.getTransaction(id);

        TestObserver<CompositeTransaction> observer = new TestObserver<>();
        streamGetTransaction.subscribe(observer);

        observer.assertValue(expectedResult);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void testGetTransaction_NetworkError_DataIsReturn() {

        final CompositeTransaction expectedResult = DataProvider.compositeTransaction1();
        final TransactionResponse expectedResponse = DataProvider.transactionResponse1();
        final String id = DataProvider.transaction1().getId();
        final SocketTimeoutException expectedException = DataProvider.timeoutException();
        when(remoteDataSource.getTransaction(id)).thenReturn(Single.error(expectedException));
        when(localDataSource.getTransaction(id)).thenReturn(Maybe.just(expectedResponse));
        when(mapper.map()).thenCallRealMethod();
        Maybe<CompositeTransaction> streamGetTransaction = repository.getTransaction(id);

        TestObserver<CompositeTransaction> observer = new TestObserver<>();
        streamGetTransaction.subscribe(observer);

        observer.assertValue(expectedResult);
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void testGetTransaction_SQLError_ExceptionIsThrown() {
    }

    /*...*/
}
