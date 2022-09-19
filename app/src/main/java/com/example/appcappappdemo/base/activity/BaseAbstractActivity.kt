package com.example.appcappappdemo.base.activity


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appcappappdemo.R
import com.example.appcappappdemo.base.dialog.LoadingDialog
import com.example.appcappappdemo.base.presenter.BasePresenter
import com.example.appcappappdemo.base.view.BaseView
import java.lang.reflect.ParameterizedType


abstract class BaseAbstractActivity<P : BasePresenter<*>> : AppCompatActivity() {


    private var loadingDialog: LoadingDialog? = null
    protected var mPresenter: P? = null
//    private var mUnBinder: Unbinder? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayoutId() != 0) {
            val inflater = LayoutInflater.from(this)
            val baseView = inflater.inflate(getLayoutId(), null)
            setContentView(baseView)
            initPresenter()
//            //BindView
//            mUnBinder = ButterKnife.bind(this)
            init()
        }
    }


    /**
     * MVP模式，这里开始初始化presenter
     */
    private fun initPresenter() {
        if (this is BaseView<*>) {
            //获取一个Presenter
            mPresenter = createPresenter()
            mPresenter?.let { presenter ->
                //获取自己的class，这里的this是子类activity而不是baseActivity
                val aClass = this.javaClass
                //获取父类，这里的type就是BaseActivity
                val type = aClass.genericSuperclass
                if (type is ParameterizedType) {
                    val parameterizedType = type as ParameterizedType
                    //获取泛型类型，这里的genericType就是泛型T
                    val genericType = parameterizedType.actualTypeArguments[0]
                    //这里获取Presenter实现的那些接口
                    val interfaces = presenter.javaClass.interfaces
                    //判断Presenter实现的接口是否有泛型T，本质上就是判断mPresenter是否就是泛型T的实例
                    for (c in interfaces) {
                        if (c == genericType) {
                            presenter.attachView(this as BaseView<*>)
                            return
                        }
                    }
                }
            }
            mPresenter = null
        }
    }


    /**
     * 判断mPresenter是否和Contract.View绑定了，
     * 这个方法其实是Contract.View的方法，这里先实现一个默认的方法，每个子类就不需要单独实现它，详情见BaseView
     */
    fun isAttachedToPresenter(): Boolean {
        return mPresenter != null
    }


    /**
     * 展示/隐藏 loading
     */
    fun setEnableLoading(isEnable: Boolean) {
        if (isEnable) {
            showLoadingDialog()
        } else {
            dismissLoadingDialog()
        }
    }

    private fun showLoadingDialog() {
        try {
            if (loadingDialog == null) {
                if (!this.isFinishing) {
                    loadingDialog = LoadingDialog.createDialog(
                        this,
                        getString(R.string.string_loading),
                        0
                    )
                    loadingDialog?.let {
                        it.setCancelable(false)
                        it.setCancel(false)
                        if (!it.isShowing) {
                            it.show()
                        }
                    }
                }
            } else {
                if (!isFinishing) {
                    loadingDialog?.let {
                        it.setCancel(false)
                        if (!it.isShowing) {
                            it.show()
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun dismissLoadingDialog() {
        if (loadingDialog == null) {
            return
        }
        if (loadingDialog != null && getActivity() != null) {
            loadingDialog?.let {
                it.dismiss()
            }
            loadingDialog = null
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.let { presenter ->
            presenter.detachView()
            mPresenter = null
        }
//        mUnBinder?.let {
//            it.unbind()
//        }
    }


    protected fun displayToast(msg: String?) {
        msg?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }


    protected fun getActivity(): Activity {
        return this
    }

    protected fun getContext(): Context {
        return this
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun init()

    protected abstract fun createPresenter(): P?
}