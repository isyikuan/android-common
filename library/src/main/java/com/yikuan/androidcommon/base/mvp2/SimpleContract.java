package com.yikuan.androidcommon.base.mvp2;

/**
 * @author yikuan
 * @date 2019/09/18
 */
public class SimpleContract {
    public static class SimplePresenter extends BasePresenter<SimpleView> {
        void doSomething() {
            // TODO: Something.
            getView().showSomething();
        }
    }

    public interface SimpleView {
        void showSomething();
    }

}
