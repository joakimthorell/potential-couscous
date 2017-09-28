package potential_couscous.couscousdrive;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectActivity extends AppCompatActivity {

    private final static int CONNECTION_TIMEOUT = 3000;

    private EditText mEd_host = null;
    private EditText mEd_port = null;
    private Button mButton = null;
    private Socket mSocket = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        mEd_host = (EditText) findViewById(R.id.ed_host);
        mEd_port = (EditText) findViewById(R.id.ed_port);
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
        String oldPort = mSharedPrefs.getString("port", null);
        if (oldPort != null) {
            mEd_port.setText(oldPort);
        }
    }

    private void setupButton(final SharedPreferences mSharedPrefs, final Context context) {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String host = mEd_host.getText().toString().trim();
                String port = mEd_port.getText().toString().trim();

                mSharedPrefs.edit().putString("host", host).commit();
                mSharedPrefs.edit().putString("port", port).commit();

                new AsyncConnectionTask(context).execute(host, port);
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
                msg = params[0] + ":" + params[1];

                /*
                Close any previously used socket
                (Eg. double clicks on connect.)
                 */
                if (mSocket != null && !mSocket.isClosed()) {
                    mSocket.close();
                }

                    mSocket = new Socket();
                    mSocket.connect(new InetSocketAddress(params[0],
                                    Integer.parseInt(params[1])),
                            CONNECTION_TIMEOUT);


            } catch (NumberFormatException e) {
                e.printStackTrace();
                msg = "Invalid port value (" + params[1] + "), type an integer between 0 and 65535";
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                msg = "Invalid port value (" + params[1] + "), type an integer between 0 and 65535";
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (mSocket != null && mSocket.isConnected()) {
                MainActivity.setSocket(mSocket);
                finish();
            } else {
                Toast.makeText(mContext, "Not able to connect. see: " + msg, Toast.LENGTH_LONG).show();
            }
        }
    }

}
