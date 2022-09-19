package com.example.appcappappdemo.base.dialog


import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.appcappappdemo.R


class LoadingDialog(
    context: Context,
    themeResId: Int
) :
    Dialog(context, themeResId) {


    private var isCancel = true
    fun setCancel(cancel: Boolean) {
        isCancel = cancel
    }

    /**
     * 设置提示信息
     *
     * @param msg
     * @return
     */
    private fun setMessage(msg: String): LoadingDialog? {
        val tvMsg: TextView = loadingDialog!!.findViewById(R.id.id_tv_loading)
        if (tvMsg != null) {
            tvMsg.text = msg
        }
        return loadingDialog
    }

    override fun show() {
        try {
            super.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (loadingDialog == null) {
            return
        }
        val imageView: ImageView = loadingDialog.findViewById(R.id.id_img_loading)
        val animationDrawable = imageView.background as AnimationDrawable
        animationDrawable.start()
        if (hasFocus && window != null) {
            val decorView: View = window!!.decorView
            if (decorView.height === 0 || decorView.width === 0) {
                decorView.requestLayout()
            }
        }
    }

    /**
     * 动态判断 dialog 点击 back 按键是否可以取消
     *
     * @param event
     * @return
     */
    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        return if (!isCancel) {
            false
        } else {
            loadingDialog.dismiss()
            true
        }
    }

    companion object {
        /**
         * processDialog   dialog对象
         * isCancel        是否可以点击back按键退出
         */
        private lateinit var loadingDialog: LoadingDialog

        /**
         * 创建 dialog
         *
         * @param context
         * @param msg
         * @param alpha
         * @return
         */
        fun createDialog(context: Context, msg: String, alpha: Int): LoadingDialog {
            loadingDialog = LoadingDialog(context, R.style.LoadingDialog)
            loadingDialog.setContentView(R.layout.dialog_loading)
            loadingDialog.window!!.attributes.gravity = Gravity.CENTER
            loadingDialog!!.setMessage(msg)
            //去掉遮罩层（全透明）
            if (alpha <= 0) {
                loadingDialog.window!!.setDimAmount(0f)
            }
            return loadingDialog
        }
    }
}
