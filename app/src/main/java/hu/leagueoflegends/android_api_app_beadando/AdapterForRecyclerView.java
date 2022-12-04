package hu.leagueoflegends.android_api_app_beadando;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import hu.leagueoflegends.android_api_app_beadando.models.ChampionData;

public class AdapterForRecyclerView extends RecyclerView.Adapter<AdapterForRecyclerView.MyViewHolder> {

    private OnItemClickListener mOnItemClickListener;

    private Context mContext;
    private List<ChampionData> champsList;

    public AdapterForRecyclerView(Context mContext, List<ChampionData> champsList, OnItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.champsList = champsList;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.champion_item, parent, false);
        return new MyViewHolder(v, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(champsList.get(position).getTitle());
        holder.champName.setText(champsList.get(position).getName());

        Glide.with(mContext)
                .load("https://ddragon.leagueoflegends.com/cdn/12.22.1/img/champion/" + champsList.get(position).getImages().getFull())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return champsList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        OnItemClickListener onItemClickListener;

        TextView champName;
        TextView title;
        ImageView img;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            champName = itemView.findViewById(R.id.textView2);
            title = itemView.findViewById(R.id.textView3);
            img = itemView.findViewById(R.id.imageView);

            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface  OnItemClickListener{
        void onItemClick(int position);
    }

}
