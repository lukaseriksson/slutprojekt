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

            String msgin="SetUpNetwork";
            while(!msgin.equals("exit")){
                msgin = dis.readUTF();
                msg_area.setText(msg_area.getText() + "\n Client : " + msgin);
            }

        }catch(Exception e)
        {
            //handle
        }
    }

    private String SetUpNetwork() throws IOException {
        String msgin = "";
        s = new Socket("127.0.0.1",1201);
        dis =new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        dout.writeUTF("hej");
        return msgin;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("client");
        client s = new client();
        frame.setContentPane(s.client);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        try {
            s.SetUpNetwork();
            s.InputLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
