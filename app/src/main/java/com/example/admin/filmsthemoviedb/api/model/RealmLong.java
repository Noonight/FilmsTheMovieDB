package com.example.admin.filmsthemoviedb.api.model;


import io.realm.RealmObject;

public class RealmLong extends RealmObject {
    Long genreIds;

    public RealmLong(Long genreIds) {
        this.genreIds = genreIds;
    }
    public RealmLong() {

    }
}
