package com.example.appcappappdemo.utils

object ParamUtils {

    fun getPayMoneyFromSp() = SpUtils.get(Constants.PARAM_PAY_MONEY, Constants.DEFAULT_PAY_MONEY)

    fun getRefundMoneyFromSp() =
        SpUtils.get(Constants.PARAM_REFUND_MONEY, Constants.DEFAULT_REFUND_MONEY)

    fun getMchIdFromSp() = SpUtils.get(Constants.PARAM_MCH_ID, Constants.DEFAULT_MCH_ID)

    fun getPrivateKeyFromSp() =
        SpUtils.get(Constants.PARAM_PRIVATE_KEY, Constants.DEFAULT_PRIVATE_KEY)

    fun getYinLianFromSp() = SpUtils.get(Constants.PARAM_YIN_LIAN, Constants.DEFAULT_YIN_LIAN)

    fun getBaseUrlFromSp() = SpUtils.get(Constants.PARAM_BASE_URL, Constants.DEFAULT_BASE_URL)


    fun putPayMoneyToSp(value: String) {
        SpUtils.save(Constants.PARAM_PAY_MONEY, value)
    }

    fun putRefundMoneyToSp(value: String) {
        SpUtils.save(Constants.PARAM_REFUND_MONEY, value)
    }

    fun putMchIdToSp(value: String) {
        SpUtils.save(Constants.PARAM_MCH_ID, value)
    }

    fun putPrivateKeyToSp(value: String) {
        SpUtils.save(Constants.PARAM_PRIVATE_KEY, value)
    }

    fun putYinLianToSp(value: String) {
        SpUtils.save(Constants.PARAM_YIN_LIAN, value)
    }

    fun putBaseUrlToSp(value: String) {
        SpUtils.save(Constants.PARAM_BASE_URL, value)
    }
}