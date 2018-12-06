package ir.orangehat.movieinfo.bussines.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * SearchResult
 */

public class SearchResult {

    @SerializedName("Search")
    private ArrayList<Movie> search;

    private String totalResults;

    @SerializedName("Response")
    private String response;

    public ArrayList<Movie> getSearch() {
        return search;
    }

    public void setSearch(ArrayList<Movie> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String isResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
