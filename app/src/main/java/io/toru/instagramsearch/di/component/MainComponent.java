package io.toru.instagramsearch.di.component;

import javax.inject.Singleton;

import dagger.Component;
import io.toru.instagramsearch.di.module.MainModule;
import io.toru.instagramsearch.main.presenter.MainPresenterImpl;

/**
 * Created by wonyoung on 2017. 5. 23..
 */

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void injectMain(MainPresenterImpl mainPresenter); // 이 함수의 파라메터로 뭔가 넣어 줘야 하는데 이 모듈을 사용할 클래스를 넣어 준다.
}
