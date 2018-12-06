package ir.orangehat.movieinfo.application.ui.home;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ir.orangehat.movieinfo.bussines.model.Movie;
import ir.orangehat.movieinfo.bussines.networking.api.Resource;
import ir.orangehat.movieinfo.bussines.repository.MovieRepository;

/**
 * HomeViewModel
 */

class HomeViewModel extends AndroidViewModel {
    private final MutableLiveData<String> currencyBase =new MutableLiveData<>();


    private final MutableLiveData<Resource> status;
    private MovieRepository movieRepository;
    /**this method to get listen on the updated value from view
            then perform action
    **/
    //public final LiveData <T> mCurrencyData=Transformations.switchMap(currencyBase,(mBase)-> movieRepository.getMovies(mBase));

    HomeViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application.getApplicationContext());
        movieRepository.getMovies();
        status=movieRepository.getState();



    }

    LiveData<List<Movie>> getMovies() {
        return movieRepository.getMovies();
    }

    public MutableLiveData<Resource> getState() {
        return status;
    }


    public void setCurrencyBase(String mBase){
        currencyBase.setValue(mBase);
    }
}
