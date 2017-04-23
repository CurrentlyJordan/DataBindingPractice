package nyc.c4q.jordansmith.newsreader1.mainActivity;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import nyc.c4q.jordansmith.newsreader1.mainActivity.model.Article;
import nyc.c4q.jordansmith.newsreader1.mainActivity.model.News;
import nyc.c4q.jordansmith.newsreader1.mainActivity.util.NewsService;
import nyc.c4q.jordansmith.newsreader1.mainActivity.util.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by jordansmith on 4/11/17.
 */

public class MainPresenter implements MainContract.Presenter {



    MainContract.View view;
    String api_key = "589ffd42ff994295802a4ccef02aa18e";
    private final String BASE_URL = "https://newsapi.org";



    @Override
    public void getNews() {
        view.showProgressBar(true);
        NewsService newsService = RetrofitHelper.getRetrofit(BASE_URL, NewsService.class);

        Call<News> httpRequest = newsService.getNews("the-verge","top",api_key);
        httpRequest.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Article> articleList = response.body().getArticles();
                        view.showProgressBar(false);
                        view.showNews(articleList);




                    } else {
                        Log.d(TAG, "Error" + response.errorBody().string());
                    }
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d("failure", "no connection");

            }
        });

    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void attach(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void onButtonClicked() {
        getNews();
    }


}
