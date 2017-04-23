package nyc.c4q.jordansmith.newsreader1.mainActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import nyc.c4q.jordansmith.newsreader1.R;
import nyc.c4q.jordansmith.newsreader1.databinding.NewsItemViewBinding;
import nyc.c4q.jordansmith.newsreader1.mainActivity.model.Article;

/**
 * Created by jordansmith on 11/16/16.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<Article> articleList;

    public NewsAdapter(List<Article> articleList){
        this.articleList = articleList;
    }

    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemViewBinding newsItemViewBinding = NewsItemViewBinding.inflate(layoutInflater,parent,false);

        return new NewsAdapter.NewsViewHolder(newsItemViewBinding);
    }

    @Override
    public void onBindViewHolder(final NewsAdapter.NewsViewHolder holder, int position) {

        holder.onBind(articleList.get(position));

//        String articleTitle = articleList.get(position).getTitle();
//        String articleDescription = articleList.get(position).getDescription();
//        String imageUrl = articleList.get(position).getUrlToImage();
//
//        holder.titleTextView.setText(articleTitle);
//        holder.descriptionTextView.setText(articleDescription);
//        Glide.with(holder.imageView.getContext()).load(imageUrl).into(holder.imageView);
//        holder.newsLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String url = articleList.get(holder.getAdapterPosition()).getUrl();
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                holder.newsLinearLayout.getContext().startActivity(i);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return articleList.size();

    }


    public class NewsViewHolder extends RecyclerView.ViewHolder implements NewsContract.View{
        private ImageView imageView;
        NewsItemViewBinding newsItemViewBinding;
        NewsPresenter newsPresenter;


        public NewsViewHolder(NewsItemViewBinding newsItemViewBinding) {
            super(newsItemViewBinding.getRoot());
            this.newsItemViewBinding = newsItemViewBinding;
            imageView = (ImageView) itemView.findViewById(R.id.main_news_image_view);
            newsPresenter = new NewsPresenter();
            newsPresenter.attach(this);


        }

        public void onBind(Article article){
            newsItemViewBinding.setArticle(article);
            newsItemViewBinding.setNewsPresenter(newsPresenter);
            newsItemViewBinding.executePendingBindings();

            Glide.with(imageView.getContext()).load(article.getUrlToImage()).into(imageView);

        }


        @Override
        public void itemClicked() {
            String url = newsItemViewBinding.getArticle().getUrl();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            itemView.getContext().startActivity(i);
        }
    }
}
