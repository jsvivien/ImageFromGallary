package com.example.tmh.imagefromgallay;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mListPath;
    private RecyclerView.LayoutManager mLayoutManager;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview_image);
        mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mListPath = getAllImagePath();
        mAdapter = new Adapter(mListPath);
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<String> getAllImagePath() {
        Uri mUri;
        Cursor mCursor;
        int mColumn_index_data;
        ArrayList<String> mListOfAllImages = new ArrayList<>();
        String absolutePathOfImage = null;
        mUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        mCursor = getContentResolver().query(mUri, projection, null, null, null);
        mColumn_index_data = mCursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        while (mCursor.moveToNext()) {
            absolutePathOfImage = mCursor.getString(mColumn_index_data);
            mListOfAllImages.add(absolutePathOfImage);
        }
        return mListOfAllImages;
    }

}
