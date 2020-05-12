package chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    static ServerSocket ss;
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dout;

    private JPanel server;
    private JTextField msg_area;
    private JTextField msg_text;
    private JButton msg_send;

    public server() {
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
            ss=new ServerSocket(1201);
            s=ss.accept();
            dis =new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while(msgin.equals("exit")){
                msgin = dis.readUTF();
                msg_area.setText(msg_area.getText()+"\n client");
            }

        }catch(Exception e)
        {
            //handle
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("server");
        frame.setContentPane(new server().server);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }




}
