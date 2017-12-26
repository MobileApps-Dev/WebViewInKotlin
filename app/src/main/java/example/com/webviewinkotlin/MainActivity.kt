package example.com.webviewinkotlin

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

@Suppress("OverridingDeprecatedMember")
class MainActivity : AppCompatActivity() {
    lateinit var btn_preview: Button
    lateinit var btn_next: Button
    lateinit var btn_go: Button
    lateinit var edt_url: EditText
    lateinit var web_view: WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_preview = findViewById(R.id.btn_preview) as Button
        btn_next = findViewById(R.id.btn_next) as Button
        btn_go = findViewById(R.id.btn_go) as Button
        edt_url = findViewById(R.id.edt_url) as EditText
        web_view = findViewById(R.id.web_view) as WebView

        web_view.webViewClient = MyWebViewClient()

        btn_preview.setOnClickListener({
            if (web_view.canGoBack()) {
                web_view.goBack()
            } else {
                Toast.makeText(this@MainActivity, "No History", Toast.LENGTH_SHORT).show()
            }
        })

        btn_next.setOnClickListener({
            if (web_view.canGoForward()) {
                web_view.goForward()
            } else {
                Toast.makeText(this@MainActivity, " History", Toast.LENGTH_SHORT).show()
            }
        })

        btn_go.setOnClickListener({
            web_view.loadUrl("https://" + edt_url.text.toString())
        })
    }

    class MyWebViewClient : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }
    }
}
