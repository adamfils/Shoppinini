package com.adamapps.shoppinini;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.srx.widget.TabBarView;


public class Home extends AppCompatActivity {

    int[] images = {R.drawable.ic_shopping_basket_black_24dp,
            R.drawable.ic_shopping_basket_black_24dp,R.drawable.ic_shopping_basket_black_24dp,
    R.drawable.ic_shopping_basket_black_24dp};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        toolbar.showOverflowMenu();

        TabBarView tabBarView = (TabBarView) findViewById(R.id.tabview);
        tabBarView.setMainBitmap(R.drawable.ic_action_new);
        tabBarView.bindBtnsForPage(0,R.drawable.ic_action_labels,
                R.drawable.ic_action_edit
        ,R.drawable.ic_action_share);
        tabBarView.bindBtnsForPage(1,R.drawable.ic_action_labels,
                R.drawable.ic_action_labels
                ,0);
        tabBarView.bindBtnsForPage(2,R.drawable.ic_action_labels,
                R.drawable.ic_action_labels
                ,0);
        tabBarView.bindBtnsForPage(3,R.drawable.ic_action_labels,
                0
                ,R.drawable.ic_action_labels);
        tabBarView.initializePage(0);
        tabBarView.setOnTabBarClickListener(new TabBarView.OnTabBarClickListener() {
            @Override
            public void onMainBtnsClick(int i, int[] ints) {
                Toast.makeText(Home.this, "First = "+i, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onMainBtnsClick(int i) {
                Toast.makeText(Home.this, ""+i, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLeftBtnClick(int i) {

            }

            @Override
            public void onRightBtnClick(int i) {

            }
        });

    }
    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();



            return super.onOptionsItemSelected(item);
        }
}
