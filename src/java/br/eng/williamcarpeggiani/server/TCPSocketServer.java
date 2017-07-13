/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.server;

import br.eng.williamcarpeggiani.controller.DateConverterMBean;
import br.eng.williamcarpeggiani.dao.ControllOfFeedingDAO;
import br.eng.williamcarpeggiani.dao.DeviceDAO;
import br.eng.williamcarpeggiani.dao.RegisterOfFeedingDAO;
import br.eng.williamcarpeggiani.model.ControllOfFeeding;
import br.eng.williamcarpeggiani.model.Device;
import br.eng.williamcarpeggiani.model.RegisterOfFeeding;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author William
 *
 */
public class TCPSocketServer {

    //Variables declaration
    private final int port;
    private final List<PrintStream> clients;

    //Constructor
    public TCPSocketServer(int porta) {
        this.port = porta;
        this.clients = new ArrayList<>();
    }

    //The Main infinite method.
    public void start() throws IOException {

        try {
            //Create server on port
            ServerSocket server = new ServerSocket(this.port);
            System.out.println("TCP Socket Server listening on port: ["
                    + this.port + "]");

            while (true) {
                //Waiting client connect...
                Socket cliente = server.accept();
                //when accept to print info about connection and ip of client
                System.out.println("Accept new connection with client: ["
                        + cliente.getInetAddress().getHostAddress() + "]");

                //Add ps of client in Array
                PrintStream ps = new PrintStream(cliente.getOutputStream());
                this.clients.add(ps);

                //Create treatment Thread on clients
                TreatsClientThread tc = new TreatsClientThread(cliente, this);
                new Thread(tc).start();
            }

        } catch (SocketException s) {
            System.out.println("Socket error: " + s.getMessage());
        } finally {

        }

    }

    //Treatment Class of clients - Thread
    public class TreatsClientThread implements Runnable {

        private final Socket client;
        private final TCPSocketServer server;
        private final BufferedReader input;
        private final String receiveMessage = "";

        //Treatment Received Data
        String[] separatedData = null;
        String serial = "";
        int sign = 0;
        float credits = 0;
        float sensorSilo = 0;
        float sensorDish = 0;
        String statusFeeding = "";
        String keepAlive = "";

        public TreatsClientThread(Socket client, TCPSocketServer server)
                throws IOException {
            this.client = client;
            this.server = server;
            input = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
        }

        @Override
        //Main method with thread Safe
        public synchronized void run() {

            echoClient();
            //keepAlive

        }

        //Receive data of Client and send back client
        public void echoClient() {
            try {

                //Read stream of client
                InputStream in = client.getInputStream();
                BufferedInputStream stream = new BufferedInputStream(in);
                byte[] buffer = new byte[1024];
                OutputStream out = client.getOutputStream();
                int length = 0;
                while ((length = stream.read(buffer, 0, 1024)) != -1) {
                    //echo
                    //out.write(buffer, 0, length);
                    String msgDecode = new String(buffer, "UTF-8");
                    System.out.print("Received message for TCP Server: ");
                    System.out.print(msgDecode);
                    System.out.println("\n----------------------\n");

                    treatmentReceivedData(msgDecode, out);

                }
                out.flush();
                client.close();

            } catch (IOException ex) {
                Logger.getLogger(TCPSocketServer.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }

        //Treatment of received data
        public void treatmentReceivedData(String data, OutputStream out) {

            data = data.replace("[", "").replace("]", "");
            System.out.println("Remove Characters: " + data);

            try {

                separatedData = data.split(";");

                for (int i = 0; i < separatedData.length; i++) {
                    System.out.println("Buffer [" + i + "] : " + separatedData[i] + " .");
                }

                if (separatedData.length == 7) {

                    serial = separatedData[0];
                    sign = Integer.parseInt(separatedData[1]);
                    credits = Float.parseFloat(separatedData[2]);
                    sensorSilo = Float.parseFloat(separatedData[3]);
                    sensorDish = Float.parseFloat(separatedData[4]);
                    statusFeeding = separatedData[5];
                    keepAlive = separatedData[6];

                    //Verifi if exists device in system
                    Device tempDevice = new Device();
                    DeviceDAO deviceDAO = new DeviceDAO();
                    tempDevice = deviceDAO.readSerial(serial);

                    if (tempDevice != null && tempDevice.getSerial().equals(serial)) {

                        System.out.println("Device Registered!.......................");

                        //Read the Feeding status
                        ControllOfFeeding tempControll = new ControllOfFeeding();
                        ControllOfFeedingDAO controllDAO = new ControllOfFeedingDAO();
                        tempControll = controllDAO.read(tempDevice.getId_device());

                        if (tempControll.isStatus() == true && statusFeeding.equals("NotFeeding")) {

                            //Feeding Data Response
                            System.out.println("Realized Fedding... and wait response of the Device!");
                            statusFeeding = "YetFeeding";

                        } else if (tempControll.isStatus() == true && statusFeeding.equals("YetFeeding")) {

                            //Confirm Feeding and Create Register
                            System.out.println("Feeding Complete. Creating Register...!");

                            //Update changes the Device
                            tempDevice.setSign(sign);
                            tempDevice.setCredits(credits);
                            tempDevice.setSensorSilo(sensorSilo);
                            tempDevice.setSensorDish(sensorDish);
                            deviceDAO.update(tempDevice);
                            System.out.println("Device Info Updated!");

                            //Create Feeding Register.
                            RegisterOfFeeding tempRegister = new RegisterOfFeeding();
                            RegisterOfFeedingDAO registerDAO = new RegisterOfFeedingDAO();
                            String date = new DateConverterMBean().getDateSystem();
                            Timestamp timeStamp = Timestamp.valueOf(date);
                            tempRegister.setDate(timeStamp);
                            tempRegister.setStatusFeeding(statusFeeding);
                            tempRegister.setIp(client.getInetAddress().getHostAddress());
                            tempRegister.setDevice(tempDevice);
                            registerDAO.create(tempRegister);
                            System.out.println("Feeding register created!");

                            //Update changes the Control Deviced
                            tempControll.setStatus(false);
                            statusFeeding = "NotFeeding";
                            controllDAO.update(tempControll);
                            System.out.println("Controll Feedgin Updated!");

                        } else {

                            //Update Connected
                            tempDevice.setStatusConnection(true);
                            //Return KeepAlive
                            keepAlive = "Connected";

                        }

                    } else {

                        System.out.println("The Device can not be registered! The connected Closed!");
                        //Desconnect Device
                    }

                } else {

                    System.out.println("Error in received data. Buffer inconsistent: " + separatedData);
                    System.out.println("Buffer size: [" + separatedData.length + "]");

                }

                //KeepAlive and Send Response
                Device tempDevice = new Device();
                DeviceDAO deviceDAO = new DeviceDAO();
                tempDevice = deviceDAO.readSerial(serial);
                tempDevice.setStatusConnection(true);
                deviceDAO.update(tempDevice);
                keepAlive = "Connected";

                //Response Buffer
                String newString = "[" + keepAlive + ";" + statusFeeding + "]";
                byte[] responseBuffer = newString.getBytes();
                out.write(responseBuffer);
                

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) throws IOException {
        //Init Server
        new TCPSocketServer(12345).start();
    }

}
