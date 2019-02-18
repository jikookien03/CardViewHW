package com.example.asus.cardviewhw;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);

        albumList=new ArrayList<>();
        adapter=new AlbumsAdapter(this,albumList);

        RecyclerView.LayoutManager mLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(8),true));
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.cover3).into((ImageView)findViewById(R.id.backdrop));
        }catch (Exception e){
            e.printStackTrace();
        }
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Play !",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout=(AppBarLayout)findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow=false;
            int scrollRange=-1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(scrollRange==-1){
                    scrollRange=appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + i==0){
                    collapsingToolbar.setTitle("BTS");
                    isShow=true;
                }else if(isShow){
                    collapsingToolbar.setTitle(" ");
                    isShow=false;
                }
            }
        });
    }
    private void prepareAlbums(){
        int[] covers=new int[]{
                R.drawable.pic1c,
                R.drawable.pic2c,
                R.drawable.pic3c,
                R.drawable.pic4,
                R.drawable.pic5c,
                R.drawable.pic6c,
                R.drawable.pic7,
                R.drawable.pic8c,
                R.drawable.pic9,
                R.drawable.pic10,
                R.drawable.pic11c,
                R.drawable.pic12,
                R.drawable.pic13

        };
        Album a=new Album("2 cool 4 skool",9,covers[0]);
        albumList.add(a);

        a=new Album("O!RUL8,2?",10,covers[1]);
        albumList.add(a);

        a=new Album("Skool Luv Affair",10,covers[2]);
        albumList.add(a);

        a=new Album("Repackage:Skool Luv Affair",12,covers[3]);
        albumList.add(a);

        a=new Album("Dark & Wild",14,covers[4]);
        albumList.add(a);

        a=new Album("화양연화 pt.1",9,covers[5]);
        albumList.add(a);

        a=new Album("화양연화 pt.2",9,covers[6]);
        albumList.add(a);

        a=new Album("Young Forever",23,covers[7]);
        albumList.add(a);

        a=new Album("WINGS",15,covers[8]);
        albumList.add(a);

        a=new Album("You Never Walk Alone",18,covers[9]);
        albumList.add(a);

        a=new Album("LY 承 'Her'",11,covers[10]);
        albumList.add(a);

        a=new Album("LY 轉 'Tear'",11,covers[11]);
        albumList.add(a);

        a=new Album("LY 結 'Answer'",26,covers[12]);
        albumList.add(a);


    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
