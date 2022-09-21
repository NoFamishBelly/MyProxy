package com.example.appcappappdemo.base.activity


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.appcappappdemo.R
import com.example.appcappappdemo.base.dialog.LoadingDialog
import com.example.appcappappdemo.base.presenter.BasePresenter
import com.example.appcappappdemo.base.view.BaseView
import com.example.appcappappdemo.utils.KotlinUtils
import java.lang.reflect.ParameterizedType


abstract class BaseAbstractActivity<P : BasePresenter<*>> : AppCompatActivity() {


    private var loadingDialog: LoadingDialog? = null
    protected var mPresenter: P? = null

    private lateinit var mToolBarBase: Toolbar
    private lateinit var mToolBarTitle: TextView
    private lateinit var mToolBarRightLl: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayoutId() != 0) {
            configView()
            initPresenter()
            init()
        }
    }


    private fun configView() {
        val inflater = LayoutInflater.from(this)
        val baseView = inflater.inflate(getLayoutId(), null)
        if (notUseToolBar()) {
            //不使用统一toolBar
            setContentView(baseView)
        } else {
            //使用统一toolBar
            val lpParent = LinearLayout(this)
            lpParent.orientation = LinearLayout.VERTICAL
            val toolbarView = inflater.inflate(R.layout.base_tool_bar, null)

            initToolBar(toolbarView)
            val screenWith = KotlinUtils.getScreenWidth(this)
            toolbarView.layoutParams =
                LinearLayout.LayoutParams(screenWith, ViewGroup.LayoutParams.WRAP_CONTENT)

            lpParent.addView(toolbarView)
            lpParent.addView(baseView)
            setContentView(lpParent)
        }
    }


    fun initToolBar(toolbarView: View) {
        mToolBarBase = toolbarView.findViewById(R.id.toolbar_base)
        mToolBarTitle = toolbarView.findViewById(R.id.toolbar_title)
        mToolBarRightLl = toolbarView.findViewById(R.id.toolbar_right_fl)


        supportActionBar?.let {
            it.hide()
        }

//        mToolBarBase?.let {
//            setSupportActionBar(it)
//        }
//        supportActionBar?.let {
//            it.setDisplayShowTitleEnabled(false)
//            it.elevation = 1f
//        }
    }


    protected fun notUseToolBar() = false


    protected fun setToolBarTitle(title: String) {
        mToolBarTitle?.let {
            it.text = title
        }
    }


    protected fun getToolBarRightLayout() = mToolBarRightLl


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

    /**
     * 展示 loading
     */
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

    /**
     * 隐藏 loading
     */
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
    }


    /**
     * 展示toast
     */
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