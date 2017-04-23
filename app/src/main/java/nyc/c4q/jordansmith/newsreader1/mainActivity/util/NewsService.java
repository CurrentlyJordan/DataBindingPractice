package nyc.c4q.jordansmith.newsreader1.mainActivity.util;

import nyc.c4q.jordansmith.newsreader1.mainActivity.model.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jordansmith on 11/16/16.
 */

public interface NewsService {

    @GET("/v1/articles")
    Call<News> getNews(@Query("source") String source, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);
}
