package com.example.admin.filmsthemoviedb.mvp.view.home;


import android.os.Bundle;

import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponseBody;
import com.example.admin.filmsthemoviedb.mvp.base.BaseView;

import java.util.ArrayList;

public interface PopularMovieView extends BaseView {

    void setData(ArrayList<MoviePopularResponseBody> data);

    int getPage();
}
