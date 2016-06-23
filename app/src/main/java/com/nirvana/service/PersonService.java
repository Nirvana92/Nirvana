package com.nirvana.service;

import com.nirvana.entity.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Nirvana on 2016/6/22.
 */
public interface PersonService {

    @GET("persons")
    Observable<List<Person>> getPersons();

}
