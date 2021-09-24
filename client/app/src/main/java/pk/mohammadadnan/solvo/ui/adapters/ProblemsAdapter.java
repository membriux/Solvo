package pk.mohammadadnan.solvo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pk.mohammadadnan.solvo.R;
import pk.mohammadadnan.solvo.Utils;
import pk.mohammadadnan.solvo.models.Problem;


public class ProblemsAdapter extends RecyclerView.Adapter<ProblemsAdapter.ViewHolder> {

    private ArrayList<Problem> mData;
    private LayoutInflater mInflater;
    private ClickListener mClickListener;
    private InterestListener mInterestListener;

    // data is passed into the constructor
    public ProblemsAdapter(Context context, ArrayList<Problem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.problem_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(mData.get(position).getTitle());
        holder.detailsTxt.setText(mData.get(position).getDescription());
//        holder.timeTxt.setText(Utils.getString(mData.get(position).getTime()));
//        holder.countTxt.setText(Utils.getString(mData.get(position).getCount()));
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setValues(ArrayList<Problem> mData){
        this.mData = mData;
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, detailsTxt,timeTxt,countTxt;
        ToggleButton interestBtn;
        View view;

        ViewHolder(View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.item_title);
            detailsTxt = itemView.findViewById(R.id.item_details);
            timeTxt = itemView.findViewById(R.id.item_time);
            countTxt = itemView.findViewById(R.id.item_count);
            interestBtn = itemView.findViewById(R.id.item_interest);

            interestBtn.setOnClickListener(view -> {
                if (mInterestListener != null) mInterestListener.onInterest(view, getAdapterPosition());
            });

            itemView.setOnClickListener(view -> {
                if (mClickListener != null) mClickListener.onClick(view, getAdapterPosition());
            });

        }

    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id).getTitle();
    }

    // allows clicks events to be caught
    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    // allows clicks events to be caught
    public void setInterestListener(InterestListener interestListener) {
        this.mInterestListener = interestListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ClickListener {
        void onClick(View view, int position);
    }

    // parent activity will implement this method to respond to click events
    public interface InterestListener {
        void onInterest(View view, int position);
    }


}
