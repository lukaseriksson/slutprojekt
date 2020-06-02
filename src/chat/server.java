package chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    static ServerSocket ss;
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dout;

    private JPanel server;
    private JTextArea msg_area;
    private JTextField msg_text;
    private JButton msg_send;

    public server() {
        msg_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String msg="";
                    msg=msg_text.getText();
                    dout.writeUTF(msg);
                    msg_text.setText("");
                }catch (Exception e)
                {
                    //handle
                }
            }
        });
    }

    private void InputLoop() {
        try{
            String msgin = "SetUpNetwork";
            while(!msgin.equals("exit")){
                msgin = dis.readUTF();
                msg_area.setText(msg_area.getText() + " \n Server : " + msgin);
            }

        }catch(Exception e)
        {
            //handle
        }
    }

    private String SetUpNetwork() throws IOException {
        String msgin="";
        ss=new ServerSocket(1201);
        s=ss.accept();
        dis =new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        return msgin;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("server");
        server s = new server();
        frame.setContentPane(s.server);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        try {
            s.InputLoop();
            s.SetUpNetwork();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
