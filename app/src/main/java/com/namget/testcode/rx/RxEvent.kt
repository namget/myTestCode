package com.namget.testcode.rx

/**
 * Created by Namget on 2019.10.16.
 */
class RxEvent {
    data class aaa(val aa: String)
}

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