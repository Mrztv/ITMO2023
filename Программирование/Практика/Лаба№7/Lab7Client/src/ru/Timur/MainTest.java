/*    */ package ru.Timur;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.net.InetAddress;
/*    */ import java.net.InetSocketAddress;
/*    */ import java.net.SocketAddress;
/*    */ import java.net.UnknownHostException;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.channels.DatagramChannel;
/*    */ import ru.Timur.Command.AddCommand;
/*    */ import ru.Timur.Command.Validation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MainTest
/*    */ {
/*    */   public static void main(String[] args) {
/*    */     try {
/*    */       while (true) {
/* 25 */         AddCommand addCommand = new AddCommand(new Validation());
/* 26 */         addCommand.execute();
/* 27 */         ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 28 */         ObjectOutputStream oos = new ObjectOutputStream(baos);
/* 29 */         oos.writeObject(addCommand);
/* 30 */         oos.close();
/* 31 */         baos.close();
/*    */ 
/*    */ 
/*    */         
/* 35 */         ByteBuffer buf = ByteBuffer.wrap(baos.toByteArray());
/*    */ 
/*    */         
/* 38 */         int port = 65125;
/*    */         
/* 40 */         InetAddress host = InetAddress.getLocalHost();
/* 41 */         SocketAddress address = new InetSocketAddress(host, port);
/*    */         
/* 43 */         DatagramChannel dc = DatagramChannel.open();
/* 44 */         dc.configureBlocking(false);
/*    */         
/* 46 */         dc.send(buf, address);
/*    */       
/*    */       }
/*    */ 
/*    */     
/*    */     }
/* 52 */     catch (UnknownHostException e) {
/* 53 */       System.out.println(e.toString());
/* 54 */     } catch (IOException e) {
/* 55 */       System.out.println(e.toString());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\timur\Documents\ИТМО 23-24\Программирование\Практика\Лаба№7\Lab7Client\Lab7Client.jar!\ru\Timur\MainTest.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */