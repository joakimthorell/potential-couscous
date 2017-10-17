/**
 * This class sends String objects to MOPED.
 */
package potential_couscous.couscousdrive.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class CarCom {
    private static CarCom mCarCom;

    public final String ACC_KEY = "acckey";
    public final String PLATOON_KEY = "platoonkey";
    public final String MANUAL_KEY = "manualkey";

    private Socket mAutoSocket; // Couscous server socket
    private PrintWriter mAutoOut;

    /**
     * This Constructor initiates the Socket.
     *
     * @param autoSocket
     * @throws IOException if not able to establish connection
     */
    private CarCom(Socket autoSocket) throws IOException {
        mAutoSocket = autoSocket;
        init();
    }

    public static CarCom getCarCom(Socket socket) throws IOException {
        if (mCarCom == null) {
            mCarCom = new CarCom(socket);
        }
        return mCarCom;
    }

    public static CarCom getCarCom() {
        if (mCarCom != null) {
            return mCarCom;
        }
        return null;
    }

    private void init() throws IOException {
        mAutoOut = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                mAutoSocket.getOutputStream())), true);
        //System.out.println("autoOut complete");
    }

    /**
     * This method checks if CarCom object is connected to MOPED
     *
     * @return true if connected, otherwise false
     */
    public boolean isConnected() {
        if (mAutoSocket != null && mAutoSocket.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * Closing connection to all Sockets and Printwriters
     *
     * @return true if close was done correct. False if something went wrong.
     */
    public boolean close() {
        try {
            mAutoOut.close();
            mAutoSocket.close();

            mAutoSocket = null;
            mAutoOut = null;

            return true;
        } catch (IOException e) {
            return false;
        }

    }

    /**
     * Sending data to different sockets depending on key.
     *
     * @param key  constant values from CarCom
     * @param data String data will be sent to car
     */
    public void sendData(String key, String data) {
        if (data == null) {
            sendData(key);
        } else {
            mAutoOut.println(data);
        }
    }

    /**
     * This method sends String objects to car
     *
     * @param key constant values from CarCom
     */
    public void sendData(String key) {
        switch (key) {
            case ACC_KEY:
                mAutoOut.println("a");
                break;
            case PLATOON_KEY:
                mAutoOut.println("p");
                break;
            case MANUAL_KEY:
                mAutoOut.println("m");
                break;
            default:
                // nothing should be done here
                System.out.println("Something went wrong sending data from CarCom class");
        }
    }
}