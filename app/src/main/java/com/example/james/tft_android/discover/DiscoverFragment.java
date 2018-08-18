package com.example.james.tft_android.discover;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.james.tft_android.R;
import com.example.james.tft_android.base.BaseFragment;

import butterknife.BindView;

public class DiscoverFragment extends BaseFragment {

    @BindView(R.id.webview)
    WebView webview;

    @Override
    public int getLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void onCreateView() {
//                Glide.with(this)
//                .load("http://ww4.sinaimg.cn/large/610dc034gw1f96kp6faayj20u00jywg9.jpg")
//                .into((ImageView) rootView.findViewById(R.id.iv_test_glide));

        rootView.findViewById(R.id.webview);
        webview.loadUrl("http://www.baidu.com");
        webview.reload();

//        这个时候发现一个问题，启动应用后，自动的打开了系统内置的浏览器，解决这个问题需要为webview设置 WebViewClient，并重写方法：
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


}
