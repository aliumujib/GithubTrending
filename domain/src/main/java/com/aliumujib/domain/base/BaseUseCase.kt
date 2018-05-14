package com.aliumujib.domain.base

import com.aliumujib.domain.Params
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by aliumujib on 12/05/2018.
 */

abstract class BaseUseCase <T> (private val disposables: CompositeDisposable = CompositeDisposable()) {

    protected abstract fun getObservable(params: Params): Observable<T>

    /**
     * Executes the current UseCase.
     *
     * @param observer [DisposableObserver] which will be listening to the observable build
     * with [.getObservable].
     */
    fun execute(observer: DisposableObserver<T>, params: Params) {
        val observable = this.getObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        addDisposable(observable.subscribeWith<DisposableObserver<T>>(observer))
    }


    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable?) {
        if (disposable != null) {
            disposables.add(disposable)
        }
    }
}