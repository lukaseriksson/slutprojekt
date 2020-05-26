package chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class client {
    static ServerSocket ss;
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dout;

    private JButton msg_send;
    private JTextArea msg_area;
    private JTextField msg_text;
    private JPanel client;

    public client() {
        msg_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String msg = "";
                    msg = msg_text.getText();
                    dout.writeUTF(msg);
                    msg_text.setText("");
                }catch (Exception e)
                {
                    //handle
                }
            }
        });
        InputLoop();
    }

    private void InputLoop() {
        try{

            String msgin="SetUpNetwork";
            while(msgin.equals("exit")){
                msgin = dis.readUTF();
                msg_area.setText(msg_area.getText()+"\n server");
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
        JFrame frame = new JFrame("client");
        frame.setContentPane(new client().client);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
