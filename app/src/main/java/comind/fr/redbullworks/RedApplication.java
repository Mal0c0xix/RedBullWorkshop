package comind.fr.redbullworks;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Pascal on 24/03/2017.
 *
 */

public class RedApplication extends Application {


    private RequestQueue requestQueue;
    public static final String BASE_URL = "http://192.168.1.98:8080/RedBullWorks_war_exploded/";

    @Override
    public void onCreate() {
        super.onCreate();

        // Normal app init code...
        requestQueue = Volley.newRequestQueue(this);
    }

    public static RequestQueue getRequestQueue(Context context)
    {
        RedApplication application = (RedApplication) context.getApplicationContext();
        return application.requestQueue;
    }

}
