package com.wf.news.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wf.news.R;
import com.wf.news.bean.NewsListItemBean;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Recycler extends ActionBarActivity {

    Context context = this;
    @Bind(R.id.mSwipeRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.mRecyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.mDrawerLayout) DrawerLayout mDrawerLayout;
    @Bind(R.id.mTextView) TextView mTextView;

    @OnClick(R.id.mTextView) void nameless(){
        mDrawerLayout.closeDrawers();
        startActivity(new Intent(this, HelloNDK.class));
    }

    LinearLayoutManager mLayoutManager;
    private ArrayList<NewsListItemBean> mapList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        ButterKnife.bind(this);

        mRecyclerView.setHasFixedSize(true);    // true if adapter changes cannot affect the size of the RecyclerView.
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        for (int i = 0; i < 10; i++){
            NewsListItemBean n = new NewsListItemBean();
            n.setTitle("title" + i);
            n.setDate(i + " hours ago");
            n.setAbstract_("abstract " + i);
            n.setLabel("new " + i);
            mapList.add(n);
        }

        final MyAdapter mAdapter = new MyAdapter(this, mapList);
        mRecyclerView.setAdapter(mAdapter);

        /*Intent intent = new Intent(this, NetworkService.class);
        intent.putExtra("url", "");
        startService(intent);*/

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                Log.d("onDrawerSlide", "slideOffset: " + slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        Toast.makeText(Recycler.this, "finished", Toast.LENGTH_SHORT).show();
                        NewsListItemBean n = new NewsListItemBean();
                        n.setTitle("title" + mapList.size());
                        mapList.add(0, n);
                        mAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler_view_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class mAdapter extends BaseAdapter{

        private Context context;
        private ArrayList<HashMap<String, Object>> mapList;

        public mAdapter(Context context, ArrayList<HashMap<String, Object>> mapList){
            this.context = context;
            this.mapList = mapList;
        }

        @Override
        public int getCount() {
            return mapList.size();
        }

        @Override
        public Object getItem(int position) {
            return mapList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){    // inflate if null, reuse otherwise
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_news, null);
                convertView.setTag(holder);
            }
            return null;
        }
    }

    class ViewHolder{
        ImageView TucaoItemBackground;
        TextView TucaoTitle;
        TextView TucaoContent;
        TextView CommentNum;
        TextView LikeNum;
    }

    class ReqCmpltBrodcast extends BroadcastReceiver{
        public String Action = "com.wf.news.Network.ReqCmpltBrodcast";
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("onReceive", "ReqCmpltBrodcast");
            if(intent.getStringExtra("msg").equals("RequestCompleted")){
                Toast.makeText(Recycler.this, "RequestCompleted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
