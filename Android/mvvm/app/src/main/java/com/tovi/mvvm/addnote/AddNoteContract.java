package com.tovi.mvvm.addnote;

import com.tovi.mvvm.BasePresenter;
import com.tovi.mvvm.BaseView;

import java.util.Map;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
public interface AddNoteContract {

    /**
     * Tell Activity to do something
     */
    interface View extends BaseView<Presenter> {

        void toast(String msg);

        void openSecondActivity(Map map);
    }

    /**
     * Some events occurred in activity
     */
    interface Presenter extends BasePresenter {
        void handlerSubmit();

        void onActivityResume();

        void onActivityPause();
    }
}
