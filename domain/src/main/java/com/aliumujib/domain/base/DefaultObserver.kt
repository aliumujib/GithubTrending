package com.aliumujib.domain.base

import io.reactivex.observers.DisposableObserver

/**
 * Created by aliumujib on 12/05/2018.
 */

class DefaultObserver<T> : DisposableObserver<T>() {
    override fun onNext(t: T) {
        // Intentionally empty.
    }

    override fun onComplete() {
        // Intentionally empty.
    }

    override fun onError(exception: Throwable) {
        // Intentionally empty.
    }
}