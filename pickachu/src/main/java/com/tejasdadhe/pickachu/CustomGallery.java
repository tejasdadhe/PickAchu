package com.tejasdadhe.pickachu;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CustomGallery extends RelativeLayout {
    Context context;
    public CustomGallery(Context context) {
        super(context);
        this.context = context;
        inflate(context, R.layout.custom_gallery, this);
        populateMediaFromGallery();
    }

    private MediaAdapter mediaAdapter;

    public MediaData[] doneSelection()
    {
        return mediaAdapter.getCheckedItems();
    }


    private void populateMediaFromGallery() {
        ArrayList<MediaData> mediaData = loadMediaFromNativeGallery();
        initializeRecyclerView(mediaData);
    }

    private ArrayList<MediaData> loadMediaFromNativeGallery() {

        //from here
        ArrayList<MediaData> mediaData = new ArrayList<>();


        // Get relevant columns to be used later.
        String[] projection = {
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.MEDIA_TYPE,
                MediaStore.Files.FileColumns.MIME_TYPE,
                MediaStore.Files.FileColumns.TITLE,
                MediaStore.Video.Media.DURATION
        };

        // Return only video and image metadata.
        String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

        Uri queryUri = MediaStore.Files.getContentUri("external");
        CursorLoader cursorLoader = new CursorLoader(
                context,
                queryUri,
                projection,
                selection,
                null, // Selection args (none).
                MediaStore.Files.FileColumns.DATE_ADDED + " DESC" // Sort order.
        );

        Cursor mediaCursor = cursorLoader.loadInBackground();

        //upto here



        for (int i = 0; i < mediaCursor.getCount(); i++)
        {
            mediaCursor.moveToPosition(i);
            int dataColumnIndex = mediaCursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
            int typeColumnIndex = mediaCursor.getColumnIndex(MediaStore.Files.FileColumns.MEDIA_TYPE);
            int durationColumnIndex = mediaCursor.getColumnIndex(MediaStore.Video.Media.DURATION);
            MediaData m = new MediaData();
            m.setMediaType(Integer.toString(mediaCursor.getInt(typeColumnIndex)));
            if( mediaCursor.getInt(typeColumnIndex) == MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO )
            {
                String durarion = String.format(Locale.getDefault(),
                                                 "%d:%02d",
                                                TimeUnit.MILLISECONDS.toMinutes(mediaCursor.getInt(durationColumnIndex)),
                                                (TimeUnit.MILLISECONDS.toSeconds(mediaCursor.getInt(durationColumnIndex))%60)
                );
                m.setMediaDuration(durarion);
            }
            m.setUrlHD(mediaCursor.getString(dataColumnIndex));
            mediaData.add(m);
        }
        return mediaData;
    }

    private void initializeRecyclerView(ArrayList<MediaData> mediaData) {
        mediaAdapter = new MediaAdapter(context, mediaData);
        GridLayoutManager LayoutManager = new GridLayoutManager(context,3);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ItemOffsetDecoration(getContext(), R.dimen.offset));
        recyclerView.setAdapter(mediaAdapter);
    }

    public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
        private int mItemOffset;
        ItemOffsetDecoration(int itemOffset) {
            mItemOffset = itemOffset;
        }
        ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
            this(context.getResources().getDimensionPixelSize(itemOffsetId));
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
        }
    }
}
