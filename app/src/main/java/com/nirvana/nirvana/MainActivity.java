package com.nirvana.nirvana;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.nirvana.entity.Person;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.button)
    Button button;
    @Bind(R.id.resultTxt)
    TextView resultTxt;

    private Subscriber subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onClick() {
        getPersons();
    }

    private void getPersons() {

        subscriber = new Subscriber<List<Person>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                resultTxt.setText(e.getMessage());
            }

            @Override
            public void onNext(List<Person> persons) {
                resultTxt.setText(persons.toString());
            }
        };

        /*
        String baseUrl = "http://192.168.1.131:8080/study/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PersonService personService = retrofit.create(PersonService.class);
        Call<List<Person>> call = personService.getPersons();

        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                List<Person> persons = response.body();
                //resultTxt.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                resultTxt.setText(t.getMessage());
            }
        });
        */
    }
}
