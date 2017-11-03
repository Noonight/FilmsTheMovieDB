package com.example.admin.filmsthemoviedb;

import android.support.annotation.MainThread;

import com.example.admin.filmsthemoviedb.api.NetworkManager;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponse;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        Observable.just("Hello")
                .subscribe(System.out::print);
        /*ArrayList<String> array = new ArrayList<>();
        array.add("Vasya");
        array.add("Dima");
        array.add("Artur");
        array.add("Petya");
        array.add("Roma");
        task1(array)
                .subscribe(System.out::println);*/
    }

    public static Observable<Integer> task1(@NotNull List<String> list) {
        return Observable
                .from(list)
                .map(String::toLowerCase)
                .filter(s -> s.contains("r"))
                .map(String::length)
                .asObservable();
    }
}