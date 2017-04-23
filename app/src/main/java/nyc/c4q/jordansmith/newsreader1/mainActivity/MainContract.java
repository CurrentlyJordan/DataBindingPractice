package nyc.c4q.jordansmith.newsreader1.mainActivity;

import java.util.List;

import nyc.c4q.jordansmith.newsreader1.mainActivity.model.Article;

/**
 * Created by jordansmith on 4/11/17.
 */

public interface MainContract {

    interface View{
        void showNews(List<Article> articleList);
        void showProgressBar(boolean show);


    }

    interface Presenter{
        void getNews();
        void detach();
        void attach(MainContract.View view);
        void onButtonClicked();



    }


}
