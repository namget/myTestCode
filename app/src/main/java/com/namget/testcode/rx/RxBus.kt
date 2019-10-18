package com.namget.testcode.rx

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject


/**
//데이터 전달
RxBus.publish(RxEvent.EventAddPerson(etPersonName.text.toString()))
finish()

//disposable add
personDisposable = RxBus.listen(RxEvent.EventAddPerson::class.java).subscribe {
adapter.addPerson(person = it.personName)
}

//destory에 등록
override fun onDestroy() {
super.onDestroy()
if (!personDisposable.isDisposed) personDisposable.dispose()
}
 */

//해당 타입의 클래스만을 listen 하게 한다.

object RxBus {
    val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)

    inline fun <reified EVENT> subscribe(crossinline subscriber: (event: EVENT) -> Unit): Disposable =
        publisher.ofType(EVENT::class.java).subscribe { event -> subscriber.invoke(event) }
}