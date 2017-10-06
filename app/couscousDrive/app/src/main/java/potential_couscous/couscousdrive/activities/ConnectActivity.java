package potential_couscous.couscousdrive.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import potential_couscous.couscousdrive.R;
import potential_couscous.couscousdrive.utils.CarCom;

public class ConnectActivity extends AppCompatActivity {
    private final static int WIRELESSINO_PORT = 9000;
    private final static int COUSCOUS_PORT = 8888;
    private final static int CONNECTION_TIMEOUT = 3000;

    private EditText mEd_host;
    private Button mButton;
    private CarCom mCarCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        //TODO Could this help ?
        // Not the best solution... Fix this if there is more time.
        // This is solves the networks call on main thread problems
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mEd_host = (EditText) findViewById(R.id.ed_host);
        mButton = (Button) findViewById(R.id.btn_connect);

        SharedPreferences mSharedPrefs = getSharedPreferences("list", MODE_PRIVATE);
        setDefaultValues(mSharedPrefs);

        setupButton(mSharedPrefs, this);
    }

    private void setDefaultValues(SharedPreferences mSharedPrefs) {
        String oldHost = mSharedPrefs.getString("host", null);
        if (oldHost != null) {
            mEd_host.setText(oldHost);
        }
    }

    private void setupButton(final SharedPreferences mSharedPrefs, final Context context) {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String host = mEd_host.getText().toString().trim();
                mSharedPrefs.edit().putString("host", host).apply();
                new AsyncConnectionTask(context).execute(host);
            }
        });
    }

    private class AsyncConnectionTask extends AsyncTask<String, Void, String> {
        private String msg = "def msg";
        private Context mContext;

        private AsyncConnectionTask(Context context) {
            mContext = context;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                msg = params[0];
                String ip = params[0];

                // Close any previously used socket. (Eg. double clicks on connect.)
                if (mCarCom != null && mCarCom.isConnected()) {
                    mCarCom.close();
                }

                Socket wirelessInoSocket = new Socket();
                wirelessInoSocket.connect(
                        new InetSocketAddress(ip, WIRELESSINO_PORT), CONNECTION_TIMEOUT);

                Socket couscousSocket = new Socket();
                couscousSocket.connect(
                        new InetSocketAddress(ip, COUSCOUS_PORT), CONNECTION_TIMEOUT);

                mCarCom = new CarCom(wirelessInoSocket, couscousSocket);

            } catch (IOException e) {
                e.printStackTrace();
                msg = "Can't connect to socket...";
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                msg = "Can't connect to socket...";
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (mCarCom != null && mCarCom.isConnected()) {
                MainActivity.setCarCom(mCarCom);
                finish();
            } else {
                Toast.makeText(mContext, "Not able to connect... See: " + msg, Toast.LENGTH_LONG).show();
            }
        }
    }
}
