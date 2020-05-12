package chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
        try{
            String msgin="";

            s = new Socket("127.0.0.1", 1201); //ip = localhost
            dis =new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while(msgin.equals("exit")){
                msgin = dis.readUTF();
                msg_area.setText(msg_area.getText()+"\n server");
            }

        }catch(Exception e)
        {
            //handle
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("client");
        frame.setContentPane(new client().client);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
