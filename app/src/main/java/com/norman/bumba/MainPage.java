package com.norman.bumba;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class MainPage extends ActionBarActivity {
    private WebView mWebView;
    private int State;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        State = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //Webview settings
        mWebView = (WebView) findViewById(R.id.mainWebView);
        //Enable JS
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser

        mWebView.setWebViewClient(new WebViewClient(){
            /*public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }
            public void onPageFinished(WebView view, String url) {

            }*/

            //lock new pages on different activity
            /*public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                if(Uri.parse(url).getHost().endsWith("soweitsogut.site50.net")) {
                    if(Uri.parse(url).getPath().contains("post.php")) {
                            Intent intent = new Intent(view.getContext(), PostActivity.class);
                            intent.putExtra(EXTRA_MESSAGE, url);
                            startActivity(intent);
                            return true;
                    }
                    return false;
                }else{
                    return true;
                }
            }*/
        });
        //disable zoom
        webSettings.setBuiltInZoomControls(false);
        //load files and check for Internet connection
        /*

        if(isNetworkAvailable()) {
            mWebView.loadUrl("http://soweitsogut.site50.net");
        }else{
            mWebView.loadUrl("file:///android_asset/www/index.html");
        }

        */
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
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

    @Override
    public void onStart() {
        if(isNetworkAvailable() && State==0){
            super.onStart();
            mWebView.loadUrl("http://app.moabit.de");
            State = 1;
        }
        super.onStart();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        State = 2;
    }

}
