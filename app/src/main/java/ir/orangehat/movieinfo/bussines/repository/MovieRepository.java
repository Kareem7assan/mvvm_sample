package ir.orangehat.movieinfo.bussines.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.widget.Toast;

import java.util.List;

import ir.orangehat.movieinfo.bussines.model.Movie;
import ir.orangehat.movieinfo.bussines.model.SearchResult;
import ir.orangehat.movieinfo.bussines.networking.api.MovieApi;
import ir.orangehat.movieinfo.bussines.networking.api.Resource;
import ir.orangehat.movieinfo.bussines.persistance.database.MovieDatabaseHelper;
import retrofit2.Response;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * MovieRepository
 */

public class MovieRepository extends BaseRepository {

    private final Context context;
    private  MutableLiveData<Resource>  state;
    private MovieApi movieApi;
    private MovieDatabaseHelper movieDatabaseHelper;


    public MovieRepository(Context context) {
        this.context=context;
        state=new MutableLiveData<>();
        movieApi = getRetrofitHelper().getService(MovieApi.class);
        movieDatabaseHelper = new MovieDatabaseHelper(context);

    }

    public LiveData<List<Movie>> getMovies() {
        state.setValue(Resource.loading(null));
        Single<Response<SearchResult>> resultObservable = movieApi.getMovieList();
        resultObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Response<SearchResult>>() {
            @Override
            public void call(Response<SearchResult> searchResult) {

                if (!searchResult.isSuccessful()) {
                    Toast.makeText(context, searchResult.code()+"", Toast.LENGTH_SHORT).show();
                    state.postValue(Resource.error(searchResult.code()+"",null));
                } else {
                  //  movieDatabaseHelper.save(searchResult.body().getSearch());
                   // state.postValue(ApiStates.success());
                    state.postValue(Resource.success(searchResult.body().getSearch()));
                }
            }
        }, throwable -> state.postValue(Resource.error(throwable.getMessage(),null))/*state.postValue(ApiStates.error_server(throwable,throwable.getMessage()))*/);


        return movieDatabaseHelper.getAll();
    }

    public MutableLiveData<Resource> getState() {
        return state;
    }
}
