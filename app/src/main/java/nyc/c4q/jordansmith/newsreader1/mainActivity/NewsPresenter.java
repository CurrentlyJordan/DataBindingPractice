package nyc.c4q.jordansmith.newsreader1.mainActivity;

/**
 * Created by jordansmith on 4/21/17.
 */

public class NewsPresenter implements NewsContract.Presenter {
    NewsContract.View view;




    @Override
    public void attach(NewsContract.View view) {
        this.view = view;
    }


    @Override
    public void onButtonClicked() {
        view.itemClicked();

    }
}
