package com.example.admin.filmsthemoviedb.mvp.base;


import org.jetbrains.annotations.NotNull;

public abstract class BasePresenter<V> {

    private V view;

    public void attachView(@NotNull V attachedView) {
        view = attachedView;
        if (setupDone())
            updateView();
    }

    public void detachView() {
        view = null;
    }

    protected boolean setupDone() {
        return view != null;
    }

    protected abstract void updateView();

    protected V getView() {
        return view;
    }
}
