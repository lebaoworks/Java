/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testudp_hello;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Server {
    private DatagramSocket socket;
    
    public Server(int port) throws Exception {
        socket = new DatagramSocket(port);
    }
    
    public void do_hello() throws Exception
    {
        String recv;
        String send;
        byte[] recv_buffer = new byte[1024];
        byte[] send_buffer;
        DatagramPacket recv_packet = new DatagramPacket(recv_buffer, recv_buffer.length);
        DatagramPacket send_packet;
        
        //Wait for any income UDP packet
        Arrays.fill(recv_buffer, (byte)0);
        socket.receive(recv_packet);
        recv = new String(recv_packet.getData());
        System.out.println("[+] Get message from "+recv_packet.getAddress()+":"+recv_packet.getPort()+" : "+recv);
        
        //Send response
        System.out.println("[+] Ask for Client's name.");
        send = "Hello! What's your name?";
        send_buffer = send.getBytes();
        send_packet = new DatagramPacket(send_buffer, send_buffer.length,recv_packet.getAddress(),recv_packet.getPort());
        socket.send(send_packet);
        
        //Recv response
        Arrays.fill(recv_buffer, (byte)0);
        socket.receive(recv_packet);
        recv = new String(recv_packet.getData());
        System.out.println("[+] Recv Client's name: "+recv);
        
        //Send response
        System.out.println("[+] Respond to Client.");
        send = "Nice to meet you, " + new String(recv_packet.getData());
        send_buffer = send.getBytes();
        send_packet = new DatagramPacket(send_buffer, send_buffer.length,recv_packet.getAddress(),recv_packet.getPort());
        socket.send(send_packet);
    }
    public static void main(String[] args) throws Exception {
        Server server = new Server(9999);
        server.do_hello();
    }
}
