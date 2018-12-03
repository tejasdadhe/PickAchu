package com.tejasdadhe.pickachu;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

/**
 * @author Tejas Dadhe
 */
public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {

    private static SparseIntArray mCountList;
    private Context mContext;
    private ArrayList<MediaData> mediaData;
    private SparseBooleanArray mSparseBooleanArray;
    private static int selectedLast;

    MediaAdapter(Context context, ArrayList<MediaData> mediaData) {
        mContext = context;
        mSparseBooleanArray = new SparseBooleanArray(); // this array tells if the corresponding media cell is checked
        mCountList = new SparseIntArray();

        this.mediaData = mediaData;
        selectedLast=0;
    }

    MediaData[] getCheckedItems() {
        ArrayList<MediaData> mTempArry = new ArrayList<>();
        for(int i=0;i<mediaData.size();i++) {
            if(mSparseBooleanArray.get(i)) {
                mTempArry.add(mediaData.get(i));
            }
        }
        return mTempArry.toArray(new MediaData[0]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int checkpoint = getCheckedItems().length+1;
            mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
            int index = (Integer) buttonView.getTag();

            SquareLayout tempView     = (SquareLayout) buttonView.getParent();
            TextView tempTextCount    = (TextView) tempView.getChildAt(2);
            LinearLayout vidIndicator = (LinearLayout) tempView.getChildAt(3);


            if(isChecked)
            {
                if(index>selectedLast) selectedLast = index;                          // Mark the upper limit of index for future operations
                Log.d("Adapter","Last selection is  "+ selectedLast);
                tempTextCount.setVisibility(View.VISIBLE);
                tempTextCount.setText(String.format(Locale.getDefault(),"%d",checkpoint));
                mCountList.put(index, checkpoint);                                    // Put the respective count number of the cell in array for recycling
                mSparseBooleanArray.put(index,true);                                  // Put the respective flag in boolean array as true for selected cell
                if(mediaData.get(index).getMediaType() == MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)
                {
                    vidIndicator.setBackgroundColor(ContextCompat.getColor(mContext, R.color.mediaBorderColor));
                    CheckBox checkBox = (CheckBox) buttonView;
                    RelativeLayout.LayoutParams checkBoxParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    checkBoxParams.setMargins(0,0,0, (int) mContext.getResources().getDimension(R.dimen.checkBoxBottomMargin));
                    checkBox.setLayoutParams(checkBoxParams);
                }
            }
            else
            {
                vidIndicator.setBackgroundColor(ContextCompat.getColor(mContext, R.color.semiTransparentBlack));
                mSparseBooleanArray.put(index,false);                                 // Put the respective flag in boolean array as false for unselected cell
                int c=1;
                for(int i=0; i<=selectedLast; i++)                                    //Iterate from first cell till the last selected cell
                {
                    if(mSparseBooleanArray.get(i))
                    {
                        mCountList.put(i,c++);
                        tempTextCount.setVisibility(View.VISIBLE);
                        tempTextCount.setText(String.format(Locale.getDefault(),"%d",mCountList.get(i)));
                    }
                }
                RecyclerView main = (RecyclerView) buttonView.getParent().getParent();
                if(main!=null)
                {
                    int childcount = main.getChildCount();
                    Log.d("Deselector"," Number of children = "+childcount);
                    for (int i = 0; i < childcount; i++)
                    {
                        View sview = main.getChildAt(i);
                        if (sview instanceof SquareLayout)
                        {
                            View view = ((SquareLayout) sview).getChildAt(1);
                            CheckBox checkBox = (CheckBox) view;
                            TextView count = (TextView) ((SquareLayout) sview).getChildAt(2);
                            if (checkBox.isChecked())                                               //If cell is checked update its count value
                            {
                                int x = (int) checkBox.getTag();
                                count.setVisibility(View.VISIBLE);
                                count.setText(String.format(Locale.getDefault(),"%d",mCountList.get(x)));
                            }
                            else                                                                    //Else remove the count value
                            {
                                count.setText("");
                                count.setVisibility(View.GONE);
                            }
                        } } } } }};





    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_multiphoto_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(mSparseBooleanArray.get(position))
        {
            holder.countText.setVisibility(View.VISIBLE);
            holder.countText.setText(String.format(Locale.getDefault(),"%d",mCountList.get(position)));
            Log.d("Binder",position+" :"+ Integer.toString(mCountList.get(position)));
        }

        Log.e("MediaAdapter","MediaType : "+ mediaData.get(position).getMediaType());
        RequestOptions options = new RequestOptions();
        if(mediaData.get(position).getMediaType() == MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)
        {
            holder.videoIndicator.setVisibility(View.VISIBLE);
            holder.durationText.setText(mediaData.get(position).getMediaDuration());
            Uri uri = Uri.fromFile(new File(mediaData.get(position).getUrlHD()));
            Glide.with(mContext)
                    .load(uri)
                    .thumbnail(0.1f)
                    .apply(options.centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                    .into(holder.imageView);
        }
        else
        {
            Glide.with(mContext)
                    .load(mediaData.get(position).getUrlHD())
                    .thumbnail(0.1f)
                    .apply(options.centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                    .into(holder.imageView);
        }
        holder.checkBox.setTag(position);
        holder.checkBox.setChecked(mSparseBooleanArray.get(position));
        holder.checkBox.setOnCheckedChangeListener(mCheckedChangeListener);

    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    @Override
    public int getItemCount()
    {
        return mediaData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        CheckBox checkBox;
        ImageView imageView;
        LinearLayout videoIndicator;
        TextView countText,durationText;
        VideoView videoView;
        ViewHolder(View view)
        {
            super(view);
            checkBox = view.findViewById(R.id.checkBox);
            imageView = view.findViewById(R.id.imageView);
            countText = view.findViewById(R.id.count);
            durationText = view.findViewById(R.id.videoDuration);
            videoIndicator = view.findViewById(R.id.videoIndicator);
            videoView = view.findViewById(R.id.videoView);
        }
    }




}
