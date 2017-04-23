package nyc.c4q.jordansmith.newsreader1.mainActivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import nyc.c4q.jordansmith.newsreader1.R;
import nyc.c4q.jordansmith.newsreader1.databinding.ActivityMainBinding;
import nyc.c4q.jordansmith.newsreader1.mainActivity.model.Article;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    ProgressBar progressBar;
    MainPresenter presenter;
    RecyclerView recyclerView;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initializeViewsAndPresenter();




    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attach(this);
        activityMainBinding.setPresenter(presenter);

    }

    private void initializeViewsAndPresenter(){
        presenter = new MainPresenter();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        showProgressBar(false);
        recyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);
    }




    @Override
    public void showNews(List<Article> articleList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NewsAdapter(articleList));
    }

    @Override
    public void showProgressBar(boolean show) {
        if(show){
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            progressBar.setVisibility(View.INVISIBLE);
        }
    }



    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }
}
