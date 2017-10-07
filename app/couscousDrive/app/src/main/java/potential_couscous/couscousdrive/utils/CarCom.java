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
    public final String mACC_KEY = "acckey";
    public final String mPLATOON_KEY = "platoonkey";
    public final String mMANUAL_KEY = "manualkey";

    private Socket mManualSocket; // WirelessIno socket.
    private PrintWriter mManualOut; // Couscous server socket

    private Socket mAutoSocket;
    private PrintWriter mAutoOut;

    /**
     * This Constructor will initiate the Sockets and throws exception
     * if not able to establish connection.
     *
     * @param manualSocket
     * @param autoSocket
     * @throws IOException
     */
    public CarCom(Socket manualSocket, Socket autoSocket) throws IOException {
        mManualSocket = manualSocket;
        mAutoSocket = autoSocket;
        init();
    }

    private void init() throws IOException {
        mManualOut = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                mManualSocket.getOutputStream())), true);
        System.out.println("manualOut complete");

        mAutoOut = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                mAutoSocket.getOutputStream())), true);
        System.out.println("autoOut complete");
    }

    /**
     * This method checks if CarCom object is connected to MOPED
     *
     * @return true if connected, otherwise false
     */
    public boolean isConnected() {
        if (mManualSocket != null && mManualSocket.isConnected() &&
                mAutoSocket != null && mAutoSocket.isConnected()) {
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
            mManualOut.close();
            mManualSocket.close();
            mAutoOut.close();
            mAutoSocket.close();

            mManualSocket = null;
            mAutoSocket = null;
            mAutoOut = null;
            mManualOut = null;

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
        System.out.println(data);
        if (key.equals(mACC_KEY) || key.equals(mPLATOON_KEY)) {
            sendData(key);
            return;
        }
        if (key.equals(mMANUAL_KEY)) {
            mManualOut.println(data);
        }
    }

    /**
     * This method sends String objects to car
     *
     * @param key constant values from CarCom
     */
    public void sendData(String key) {
        switch (key) {
            case mACC_KEY:
                mAutoOut.println("a");
                break;
            case mPLATOON_KEY:
                mAutoOut.println("p");
                break;
            case mMANUAL_KEY:
                mAutoOut.println("m");
                break;
            default:
                // nothing should be done here
                System.out.println("Something went wrong sending data from CarCom class");
        }
    }
}
