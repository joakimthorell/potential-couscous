package potential_couscous.couscousdrive.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import potential_couscous.couscousdrive.R;
import potential_couscous.couscousdrive.utils.CarCom;

/**
 * This Activity let's user connect to 2 different servers on the MOPED:
 * WirelessIno for manual driving and Python server for ACC and Platoon
 */
public class ConnectActivity extends AppCompatActivity {
    private final static int WIRELESSINO_PORT = 9000;
    private final static int COUSCOUS_PORT = 8888;
    private final static int CONNECTION_TIMEOUT = 3000;

    private CarCom mCarCom;
    private EditText mHost;
    private Button mConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        // Setup the action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.connect_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Back button
        getSupportActionBar().setTitle(""); // No title

        /*
        // Not the best solution... Fix this if there is more time.
        // This is solves the networks call on main thread problems
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        */

        //Setup host ip EditText field
        mHost = (EditText) findViewById(R.id.ip_address);
        SharedPreferences sp = getSharedPreferences("list", MODE_PRIVATE);
        setDefaultValues(sp); //Set last entered ip
        setupIpEditTextField();

        mConnect = (Button) findViewById(R.id.connect_to_ip);

        setConnectButtonListener(sp, this);
    }

    private void setupIpEditTextField() {
        mHost.setRawInputType(InputType.TYPE_CLASS_NUMBER); //Numpad
        mHost.setCursorVisible(true);
        //Limit number of input characters
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(15); //Set textfield to max 15 characters
        mHost.setFilters(filters);
        //Set cursor at the end of current ip input
        int lenght = mHost.getText().length();
        mHost.setSelection(lenght, lenght);
    }

    private void setDefaultValues(SharedPreferences sp) {
        String oldHost = sp.getString("host", null);
        if (oldHost != null) {
            mHost.setText(oldHost);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startMainActivity();
        return super.onOptionsItemSelected(item);
    }

    private void startMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void setConnectButtonListener(final SharedPreferences sp, final Context context) {
        mConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String host = mHost.getText().toString().trim();
                sp.edit().putString("host", host).apply();
                new AsyncConnectionTask(context).execute(host);
            }
        });
    }

    private class AsyncConnectionTask extends AsyncTask<String, Void, String> {
        private Context mContext;

        private AsyncConnectionTask(Context context) {
            mContext = context;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String ip = params[0];

                closeSockets();
                Socket wirelessInoSocket = connectNewSocket(ip, WIRELESSINO_PORT, CONNECTION_TIMEOUT);
                Socket couscousSocket = connectNewSocket(ip, COUSCOUS_PORT, CONNECTION_TIMEOUT);

                mCarCom = new CarCom(wirelessInoSocket, couscousSocket);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private Socket connectNewSocket(String ip, int port, int timeout) throws IOException, IllegalArgumentException {
            Socket newSocket = new Socket();
            newSocket.connect(new InetSocketAddress(ip, port), timeout);
            return newSocket;
        }

        /**
         * Close any previously used socket. (Eg. double clicks on connect.)
         */
        private void closeSockets() {
            if (isCarCom()) {
                mCarCom.close();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (isCarCom()) {
                MainActivity.setCarCom(mCarCom);
                startMainActivity();
                finish();
            } else {
                Toast.makeText(mContext, "Not able to connect...", Toast.LENGTH_LONG).show();
            }
        }

        private boolean isCarCom() {
            return mCarCom != null && mCarCom.isConnected();
        }
    }
}
