package nyc.c4q.jordansmith.newsreader1.mainActivity;

/**
 * Created by jordansmith on 4/21/17.
 */

public interface NewsContract {

    interface View{
        void itemClicked();

    }

    interface Presenter{
        void attach(NewsContract.View view);
        void onButtonClicked();
    }
}
