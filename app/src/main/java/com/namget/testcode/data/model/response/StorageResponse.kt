package com.namget.testcode.data.model.response

/**
 * Created by Namget on 2019.10.04.
 */
class StorageResponse(
    override var resultMsg: String? = null,
    override var resultCode: String? = null
) : BaseResponse() {

}