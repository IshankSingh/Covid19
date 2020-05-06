package developer.exam.live.vi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import developer.exam.live.vi.R;
import developer.exam.live.vi.classes.Constant;
import developer.exam.live.vi.classes.StringFormatter;
import developer.exam.live.vi.utils.NewsDataItem;

public class NewsDataAdapter extends RecyclerView.Adapter<NewsDataAdapter.NewsViewHolder> implements Filterable {

    private Context mContext;
    private ArrayList<NewsDataItem> newsDataList;
    private ArrayList<NewsDataItem> mNewsDataListFilter;

    public NewsDataAdapter(Context mContext, ArrayList<NewsDataItem> newsDataList) {
        this.mContext = mContext;
        this.newsDataList = newsDataList;
        mNewsDataListFilter = new ArrayList<>(newsDataList);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_data_list_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, final int position) {

        String title = newsDataList.get(position).getTitle();
        holder.news_title_TV.setText(title);

        String article = newsDataList.get(position).getArticle();
        final String original_article = article + "\n";
        article = StringFormatter.getNewsArticleModification(article);
        holder.news_article_TV.setText(article);
        String url = Constant.image_url + newsDataList.get(position).getUrl();
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .into(holder.news_imageView);

        // read more operations
        holder.readMoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.news_article_TV.setText(original_article);
                holder.readMoreTextView.setVisibility(View.GONE);
                holder.readLessTextView.setVisibility(View.VISIBLE);
            }
        });

        final String finalArticle = article;
        holder.readLessTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.news_article_TV.setText(finalArticle);
                holder.readMoreTextView.setVisibility(View.VISIBLE);
                holder.readLessTextView.setVisibility(View.GONE);
            }
        });

        holder.news_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "\""+newsDataList.get(position).getTitle()+"\"" + "\n\n" + original_article;
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                sendIntent.setType("text/plain");
                mContext.startActivity(Intent.createChooser(sendIntent, "share with"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsDataList.size();
    }

    @Override
    public Filter getFilter() {
        return newsFilter;
    }

    private Filter newsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String key = constraint.toString().toLowerCase().trim();
            ArrayList<NewsDataItem> filteredList = new ArrayList<>();

            if (key.isEmpty()) {
                filteredList.addAll(mNewsDataListFilter);
            }else {
                for (NewsDataItem item : mNewsDataListFilter) {
                    if (item.getTitle().toLowerCase().contains(key)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            newsDataList.clear();
            newsDataList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView news_imageView, news_share;
        TextView news_title_TV, news_article_TV, readMoreTextView, readLessTextView;

         NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            news_imageView = itemView.findViewById(R.id.news_imageView);
            news_title_TV = itemView.findViewById(R.id.news_title_TV);
            news_article_TV = itemView.findViewById(R.id.news_article_TV);
            readMoreTextView = itemView.findViewById(R.id.news_read_more);
            readLessTextView = itemView.findViewById(R.id.news_read_less);
            news_share = itemView.findViewById(R.id.news_share);
        }
    }
}
