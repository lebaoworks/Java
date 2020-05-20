/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testudp_hello;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Client {
    private DatagramSocket socket;
    
    public Client(int port) throws Exception {
        socket = new DatagramSocket(port);
    }
    
    public void do_hello(String serv_ip, int serv_port) throws Exception
    {
        InetAddress serv_address = InetAddress.getByName(serv_ip);
        String recv;
        String send;
        byte[] recv_buffer = new byte[1024];
        byte[] send_buffer;
        DatagramPacket recv_packet = new DatagramPacket(recv_buffer, recv_buffer.length);
        DatagramPacket send_packet;
        
        //Send hello
        System.out.println("[+] Send hello to "+serv_ip+":"+serv_port);
        send = "Hello!";
        send_buffer = send.getBytes();
        send_packet = new DatagramPacket(send_buffer, send_buffer.length, serv_address, serv_port);
        socket.send(send_packet);
        
        //Recv response
        Arrays.fill(recv_buffer, (byte)0);
        socket.receive(recv_packet);
        recv = new String(recv_packet.getData());
        System.out.println("[+] Get response from "+recv_packet.getAddress()+":"+recv_packet.getPort()+" : "+ recv);
        
        //Send response
        System.out.print("[*] Input name: ");
        send = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        System.out.println("[+] Send name to "+serv_ip+":"+serv_port);
        send_buffer = send.getBytes();
        send_packet = new DatagramPacket(send_buffer, send_buffer.length, serv_address, serv_port);
        socket.send(send_packet);
        
        //Recv response
        Arrays.fill(recv_buffer, (byte)0);
        socket.receive(recv_packet);
        recv = new String(recv_packet.getData());
        System.out.println("[+] Get response from "+recv_packet.getAddress()+":"+recv_packet.getPort()+" : "+ recv);
        
    }
    public static void main(String[] args) throws Exception {
        Client client = new Client(9998);
        client.do_hello("localhost",9999);
    }
}
