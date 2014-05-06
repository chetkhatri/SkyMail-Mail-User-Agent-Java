/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skymail;

/**
 *
 * @author chetan
 */


import java.io.DataInputStream;

import java.io.IOException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

public class module {
    Connection con;
    Statement st;
    ResultSet rs;
    
    DefaultTableModel model;
    public String USER_ID;
    public String USER_PWD;
public   int unread=0;
public static int attachText=0;
public static int internetCon=0;
public static int proFlag=0;
protected void conn()
    {
        try
        {
        Class.forName("java.sql.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbskymail","root","admin");
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Error in Connection");
        }
    }
   protected void checkacc()
    {
        try
        {
            st=con.createStatement();
            rs=st.executeQuery("select count(*) as id from dbskymail.tbaccount");
            rs.last();
            //System.out.println(rs.getInt("id"));
            if(rs.getInt("id")==0)
            {
         
                JOptionPane.showMessageDialog(null,"There is no Email Account found\nPlease add new account","Error",JOptionPane.ERROR_MESSAGE);
                dlAccount da=new dlAccount(new javax.swing.JFrame(), true);
                 da.setVisible(true);
               
            }
            else if(rs.getInt("id")>0)
            {
                
                newCompose nc=new newCompose();
                nc.setVisible(true); 
            }

            st.close();
            rs.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
   protected void skyAuthentic(String user,String pwd)
   {
       USER_ID=user;
       USER_PWD=pwd;
   }
   //Setting Default Account to jComboBox
   protected void setDefAcc(javax.swing.JComboBox cmb)
   {
       try
       {
           st=con.createStatement();
           rs=st.executeQuery("select acc_mail from dbskymail.tbaccount where status="+ 1 +"");
           rs.last();
           cmb.setSelectedItem(rs.getString("acc_mail"));
       }catch(Exception ex){}
   }
   //Set Disable to Default Button
   protected void setDisDef(javax.swing.JButton btn,String str)
   {
       try
       {
           st=con.createStatement();
           rs=st.executeQuery("select acc_mail from dbskymail.tbaccount where status="+ 1 +"");
           rs.last();
           
           if(str.equals(rs.getString("acc_mail")+" (Default)"))
           {
             
               btn.setEnabled(false);
           }
           else
           {
               btn.setEnabled(true);
           }
       }
       catch(Exception ex){}
   }
   protected void populateMail(javax.swing.JComboBox cmb)
   {
       try
       {
           st=con.createStatement();
           rs=st.executeQuery("select acc_mail from dbskymail.tbaccount");
           while(rs.next())
           {
               cmb.addItem(rs.getString("acc_mail"));
               
           }
           st.close();
           rs.close();
       }catch(Exception ex)
       {
       JOptionPane.showMessageDialog(null, ex);
       }
   }
   protected void accDtl(javax.swing.JTable dtl)
   {    model=(DefaultTableModel)dtl.getModel();
       try
       {    if(model.getRowCount()>0)
            {
          while(model.getRowCount()>0)
           {
               model.removeRow(0);
           }
            }
           st=con.createStatement();
           rs=st.executeQuery("select acc_mail,acc_type from dbskymail.tbaccount");
           while(rs.next())
           {
               model.addRow(new Object[]{rs.getString("acc_mail"),rs.getString("acc_type")});
           }
           
           
           
       }catch(Exception ex)
       {
            JOptionPane.showMessageDialog(null, ex);
       }
   }
  protected void getMail(String type,int port,String inHost,String accMail,String userPwd,javax.swing.JTable tbDtl,javax.swing.JLabel lblUnread,javax.swing.JLabel lblTotal,String flg,String folder,int row,javax.swing.JEditorPane jEditor,javax.swing.JProgressBar progress)
  { 
    
     model=(DefaultTableModel)tbDtl.getModel();
       try
     {
        
        Properties pros=System.getProperties();
        pros.setProperty("mail.store.protocol", type+"s");
        if(type.equals("IMAP"))
        {
           
            pros.put("mail.imap.host", inHost);     
            pros.put("mail.imap.user", accMail);
            pros.put("mail.imap.socketFactory", port);
            pros.put("mail.imap.socketFactory.class", "java.net.ssl.SSLSocketFactory");
            pros.put("mail.imap.port", port);
           
        }
        else if(type.equals("POP3"))
        {
        
        pros.put("mail.pop3.host", inHost);     
        pros.put("mail.pop3.user",accMail);
        pros.put("mail.pop3.socketFactory", port);
        pros.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        pros.put("mail.pop3.port", port);
        }
        Session session=Session.getDefaultInstance(pros,null); 
        Store store;
        System.out.println("Out Store"+"Type is"+type);
        if(inHost.equals("imap.gmail.com"))
        {
            System.out.println("it's imaps");
            store=session.getStore("imaps");
        }
         else
        {
            store=session.getStore(type.toLowerCase());
        }
	
		store.connect(inHost,accMail,userPwd);
                
		Folder inbox=store.getFolder(folder);
                
                System.out.println("Total Messages:  " + inbox.getMessageCount());
                
                lblUnread.setText("Unread :"+inbox.getUnreadMessageCount());
                lblTotal.setText("Total :"+inbox.getMessageCount());
                
                
                
		inbox.open(Folder.READ_WRITE);
                Message message[] = null;
                
                FlagTerm ft;
                
         switch (flg) {
             case "All":
//                 message=inbox.getMessages();
                 ft=new FlagTerm(new Flags(Flags.Flag.SEEN),false);
                 message=inbox.search(ft);
                 
               
                 
                 
                 if(message!=null)
                 {
          if(model.getRowCount()>0)
            {
          while(model.getRowCount()>0)
           {
               model.removeRow(0);
           }
            }
          
          
          
                     // for(int i=0;i<=message.length-1;i++)
                     for(int i=message.length-1;i>=0;i--)
		{
		    
                       model.addRow(new Object[]{"<html><b>"+message[i].getFrom()[0].toString()+"</b></html>","<html><b>"+message[i].getSubject()+"</b></html>","<html><b>"+message[i].getSentDate()+"</b></html>","<html><b>"+message[i].getSize()+"</b></html>"});
                       unread=1;
		
		}
                      
                 }
                 ft=new FlagTerm(new Flags(Flags.Flag.SEEN),true);
                 message=inbox.search(ft);
                 
                 break;
             case "Recent":
                 ft=new FlagTerm(new Flags(Flags.Flag.RECENT),true);
                 message=inbox.search(ft);
                 break;
             case "Seen":
                 ft=new FlagTerm(new Flags(Flags.Flag.SEEN),true);
                 message=inbox.search(ft);
                 break;
             case "Answered":
                 ft=new FlagTerm(new Flags(Flags.Flag.ANSWERED),true);
                 message=inbox.search(ft);
                 break;
              case "Deleted":
                 ft=new FlagTerm(new Flags(Flags.Flag.DELETED),true);
                 message=inbox.search(ft);
                 break;   
               case "Draft":
                 ft=new FlagTerm(new Flags(Flags.Flag.DRAFT),true);
                 message=inbox.search(ft);
                 break;    
               case "Unread":
                  System.out.println("Unread "+row);
                 // row+=1;
                  ft=new FlagTerm(new Flags(Flags.Flag.SEEN),false);
                  message=inbox.search(ft);
                  
                  if(message.length==0)
                 {
                     
                  
                  javax.mail.Message msg = inbox.getMessage(row);
                  
                 
                  
                  System.out.println(msg.getContent());
                    
                  String contType=msg.getContentType().toString();
                  System.out.println("CONTENT-TYPE: " + msg.getContentType());
                  
                  String attachments="";
                  
                   if(contType.contains("multipart"))
                   {
                        // jEditor.setContentType("text/html");
                       
                       Multipart multiPart = (Multipart) msg.getContent();
                       
                       int noOfPart=multiPart.getCount();
                       for(int k=0;k<noOfPart;k++)
                       {
                           MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(k);
                          
                         
                           
                           if(Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) 
                           {
                               System.out.println("Attachments read");   
                               String fileName = part.getFileName();
                               
                               JFileChooser fsave=new JFileChooser();
                              fsave.setDialogTitle("Save Attachments");
                              File file=new File("D:/SkyMail/"+fileName);
                              fsave.setCurrentDirectory(file);
                              fsave.setSelectedFile(file);
                              int userSelect=fsave.showSaveDialog(null);
                              
                              if(userSelect==JFileChooser.APPROVE_OPTION)
                               {
                                   attachments+=fileName+",";
                                   part.saveFile(fsave.getCurrentDirectory()+File.separator +fileName);
                                   JOptionPane.showMessageDialog(null, "Attachment Saved");
                               }
                             
                                 if(part.getContentType().contains("text/html"))
                               {
                                   System.out.println("Attachs Text/Html");
                                   jEditor.setContentType("text/html");
                                   jEditor.setText(part.getContent().toString());
                                 
                                   try
                                   {
                                   jEditor.setText(jEditor.getDocument().getText(0, jEditor.getDocument().getLength()));
                                   }catch(Exception ex){}
                                 
                               }
                               else 
                               {   System.out.println(part.getContentType().toString());
                                   System.out.println("Attachs Text/Plain");
                                   jEditor.setContentType("text/plain");
                                    try{
                                     writePart(msg,jEditor);
                                        }catch(Exception ex){}
                                    
                               }
                              
                               
                           }
                           
                           else
                           {
                               
                             
                           {    if(part.getContentType().contains("text/html"))
                               {
                                   System.out.println("Text/Html");
                                   jEditor.setContentType("text/html");
                                   jEditor.setText(part.getContent().toString());
                                 
                                   try
                                   {
                                   jEditor.setText(jEditor.getDocument().getText(0, jEditor.getDocument().getLength()));
                                   }catch(Exception ex){}
                                 
                               }

                           }
                               
                               
                           }
                           
                       }
                   }
                   else if(contType.contains("text/plain"))
                   {
                       jEditor.setContentType("text/plain");
                      jEditor.setText(msg.getContent().toString());
                   }
                   else if(contType.contains("text/html"))
                   {
                       jEditor.setContentType("text/html");
                      jEditor.setText(msg.getContent().toString());
                   }
                    
                     
                     
                 }
                 else
                 {
                    System.out.println("Unread msg!");
                     for(int i=0;i<=message.length-1;i++)
		{
                   Message msg=message[i];
                   
                   String attachments=""; 
                    String contType=message[i].getContentType().toString();
                   if(contType.contains("multipart"))
                   {
                        
                       
                       Multipart multiPart = (Multipart) message[i].getContent();
                       int noOfPart=multiPart.getCount();
                       for(int k=0;k<noOfPart;k++)
                       {
                           MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(k);
                           if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                               System.out.println("Attachments unread");   
                               String fileName = part.getFileName();
                               
                               JFileChooser fsave=new JFileChooser();
                              fsave.setDialogTitle("Save Attachments");
                              File file=new File("D:/SkyMail/"+fileName);
                              fsave.setCurrentDirectory(file);
                              fsave.setSelectedFile(file);
                              int userSelect=fsave.showSaveDialog(null);
                              
                              if(userSelect==JFileChooser.APPROVE_OPTION)
                               {
                                   attachments+=fileName+",";
                                   part.saveFile(fsave.getCurrentDirectory()+File.separator +fileName);
                                   JOptionPane.showMessageDialog(null, "Attachment Saved");
                               }
                              
                               
                               if(part.getContentType().contains("text/html"))
                               {
                                   System.out.println("Attachs Text/Html");
                                   jEditor.setContentType("text/html");
                                   jEditor.setText(part.getContent().toString());
                                 
                                   try
                                   {
                                   jEditor.setText(jEditor.getDocument().getText(0, jEditor.getDocument().getLength()));
                                   }catch(Exception ex){}
                                 
                               }
                               else 
                               {   System.out.println(part.getContentType().toString());
                                   System.out.println("Attachs Text/Plain");
                                   jEditor.setContentType("text/plain");
                                   try{
                                     writePart(msg,jEditor);
                                        }catch(Exception ex){}
                       
                                   
                               }
                           }
                           		
                           else
                           {    if(part.getContentType().contains("text/html"))
                               {
                                   System.out.println("Text/Html");
                                   jEditor.setContentType("text/html");
                                   jEditor.setText(part.getContent().toString());
                                 
                                   try
                                   {
                                   jEditor.setText(jEditor.getDocument().getText(0, jEditor.getDocument().getLength()));
                                   }catch(Exception ex){}
                                 
                               }

                           }
                            
                           
                           
                          
                           
                       }

                   }
                    else if(contType.contains("text/plain"))
                   {
                       jEditor.setContentType("text/plain");
                      jEditor.setText(msg.getContent().toString());
                   }
                   else if(contType.contains("text/html"))
                   {
                       jEditor.setContentType("text/html");
                      jEditor.setText(msg.getContent().toString());
                   }
                
                   
                    
                }
                   
                    
                 
                    
                    
                     
                 }
                
                   
                   break; 
                 





         }
         if(unread==0)
         {
             if(model.getRowCount()>0)
            {
          while(model.getRowCount()>0)
           {
               model.removeRow(0);
           }
            }
             
         //    for(int i=0;i<=message.length-1;i++)
             
             int max=message.length;
             progress.setMaximum(max);
             int p=1;
                 for(int i=message.length-1;i>=0;i--)
		{   
                       p+=1;
                        
                        progress.setValue(p);
                        model.addRow(new Object[]{message[i].getFrom()[0].toString(),message[i].getSubject(),message[i].getSentDate(),message[i].getSize()});
                }
                 
                if(proFlag==0)
                {
                    progress.setValue(0);
                    proFlag=1;
                }
         }
         else if(unread==1)
         {
            // for(int i=0;i<=message.length-1;i++)
                for(int i=message.length-1;i>=0;i--)
		{
                    
                    model.addRow(new Object[]{message[i].getFrom()[0].toString(),message[i].getSubject(),message[i].getSentDate(),message[i].getSize()});
                 
		}
         }
       
             
		}
		catch (NoSuchProviderException e) {
			JOptionPane.showMessageDialog(null, e);
			//retrieveMsg("tbinbox", tbDtl);
		}
		catch (MessagingException e) {
			//JOptionPane.showMessageDialog(null, e);
                        if(checkInternet()==false)
                        {
                            JOptionPane.showMessageDialog(null, "Please Connect Internet","Error",JOptionPane.ERROR_MESSAGE);
                            internetCon=1;
                            retrieveMsg("tbinbox", tbDtl);
                        }
                        else if(checkInternet()==true)
                        {
                            JOptionPane.showMessageDialog(null, "Invalid Host Name","Error",JOptionPane.ERROR_MESSAGE);
                        }
                            
			
		}
                catch(IOException e)
                {
                    JOptionPane.showMessageDialog(null, e);
                    
                }
  }
  
  
  protected void getMail(String type,int port,String inHost,String accMail,String userPwd,javax.swing.JTable tbDtl,javax.swing.JLabel lblUnread,javax.swing.JLabel lblTotal,String flg,String folder,int row,javax.swing.JProgressBar progress)
  {
     model=(DefaultTableModel)tbDtl.getModel();
       try
     {
         
        Properties pros=System.getProperties();
        pros.setProperty("mail.store.protocol", type+"s");
        if(type.equals("IMAP"))
        {
           
            pros.put("mail.imap.host", inHost);     
            pros.put("mail.imap.user", accMail);
            pros.put("mail.imap.socketFactory", port);
            pros.put("mail.imap.socketFactory.class", "java.net.ssl.SSLSocketFactory");
            pros.put("mail.imap.port", port);
           
        }
        else if(type.equals("POP3"))
        {
        
        pros.put("mail.pop3.host", inHost);     
        pros.put("mail.pop3.user",accMail);
        pros.put("mail.pop3.socketFactory", port);
        pros.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        pros.put("mail.pop3.port", port);
        }
        Session session=Session.getDefaultInstance(pros,null); 
        Store store;
        System.out.println("Out Store"+"Type is"+type);
        if(inHost.equals("imap.gmail.com"))
        {
            System.out.println("it's imaps");
            store=session.getStore("imaps");
        }
         else
        {
            store=session.getStore(type.toLowerCase());
        }
		
                
		
		store.connect(inHost,accMail,userPwd);
                
		Folder inbox=store.getFolder(folder);
                
                
                System.out.println("Total Messages:  " + inbox.getMessageCount());
//                System.out.println("New Messages:  " + inbox.getNewMessageCount());
//                System.out.println("Unread Messages: " + inbox.getUnreadMessageCount());
//                
                lblUnread.setText("Unread :"+inbox.getUnreadMessageCount());
                lblTotal.setText("Total :"+inbox.getMessageCount());
                
                
                
		inbox.open(Folder.READ_WRITE);
                Message message[] = null;
                
                FlagTerm ft;
                
         switch (flg) {
             case "All":
//                 message=inbox.getMessages();
                 ft=new FlagTerm(new Flags(Flags.Flag.SEEN),false);
                 message=inbox.search(ft);
                 
               
                 
                 
                 if(message!=null)
                 {
          if(model.getRowCount()>0)
            {
          while(model.getRowCount()>0)
           {
               model.removeRow(0);
           }
            }
                      //for(int i=0;i<=message.length-1;i++)
          for(int i=message.length-1;i>=0;i--)
		{
		      
                       model.addRow(new Object[]{"<html><b>"+message[i].getFrom()[0].toString()+"</b></html>","<html><b>"+message[i].getSubject()+"</b></html>","<html><b>"+message[i].getSentDate()+"</b></html>","<html><b>"+message[i].getSize()+"</b></html>"});
                       unread=1;
		
		}
                      
                 }
                 ft=new FlagTerm(new Flags(Flags.Flag.SEEN),true);
                 message=inbox.search(ft);
                 break;
             case "Recent":
                 ft=new FlagTerm(new Flags(Flags.Flag.RECENT),true);
                 message=inbox.search(ft);
                 break;
             case "Seen":
                 ft=new FlagTerm(new Flags(Flags.Flag.SEEN),true);
                 message=inbox.search(ft);
                 break;
             case "Answered":
                 ft=new FlagTerm(new Flags(Flags.Flag.ANSWERED),true);
                 message=inbox.search(ft);
                 break;
              case "Deleted":
                 ft=new FlagTerm(new Flags(Flags.Flag.DELETED),true);
                 message=inbox.search(ft);
                 break;   
               case "Draft":
                 ft=new FlagTerm(new Flags(Flags.Flag.DRAFT),true);
                 message=inbox.search(ft);
                 break;    
               
               case "Delete":
                   javax.mail.Message msg = inbox.getMessage(row);
                   
                   msg.setFlag(Flags.Flag.DELETED, true);
//                   System.out.println("Deleted Successfully");
                   JOptionPane.showMessageDialog(null, "Deleted Successfully");
                   inbox.close(false);
                   store.close();
                   
                   model.removeRow(model.getRowCount()-row);
               break;
               
               
               
         }
         if(unread==0)
         {
             if(model.getRowCount()>0)
            {
          while(model.getRowCount()>0)
           {
               model.removeRow(0);
           }
            }
             
             int max=message.length;
             progress.setMaximum(max);
             int p=1;
             
             
//             for(int i=0;i<=message.length-1;i++)
             for(int i=message.length-1;i>=0;i--)
		{   
                    p+=1;
                    progress.setValue(p);
                       model.addRow(new Object[]{message[i].getFrom()[0].toString(),message[i].getSubject(),message[i].getSentDate(),message[i].getSize()});
                 
		}
             progress.setValue(0);
             
         }
         else if(unread==1)
         {
//             for(int i=0;i<=message.length-1;i++)
             for(int i=message.length-1;i>=0;i--)
		{
                               
                 
                    model.addRow(new Object[]{message[i].getFrom()[0].toString(),message[i].getSubject(),message[i].getSentDate(),message[i].getSize()});
                 
		}
         }
       
             
		}
		catch (NoSuchProviderException e) {
			JOptionPane.showMessageDialog(null, e);
			
		}
		catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, e);
			
		}
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                    
                }
  }
   
  
 
 protected void searchMail(String type,int port,String inHost,String accMail,String userPwd,javax.swing.JTable tbDtl,javax.swing.JLabel lblUnread,javax.swing.JLabel lblTotal,String folder,final String  keyword)
  {
     model=(DefaultTableModel)tbDtl.getModel();
       try
     {
         
        Properties pros=System.getProperties();
        pros.setProperty("mail.store.protocol", type+"s");
        if(type.equals("IMAP"))
        {
           
            pros.put("mail.imap.host", inHost);     
            pros.put("mail.imap.user", accMail);
            pros.put("mail.imap.socketFactory", port);
            pros.put("mail.imap.socketFactory.class", "java.net.ssl.SSLSocketFactory");
            pros.put("mail.imap.port", port);
           
        }
        else if(type.equals("POP3"))
        {
        
        pros.put("mail.pop3.host", inHost);     
        pros.put("mail.pop3.user",accMail);
        pros.put("mail.pop3.socketFactory", port);
        pros.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        pros.put("mail.pop3.port", port);
        }
        Session session=Session.getDefaultInstance(pros,null); 
        Store store;
        System.out.println("Out Store"+"Type is"+type);
        if(inHost.equals("imap.gmail.com"))
        {
            System.out.println("it's imaps");
            store=session.getStore("imaps");
        }
         else
        {
            store=session.getStore(type.toLowerCase());
        }
		
                
		
		store.connect(inHost,accMail,userPwd);
                
		Folder inbox=store.getFolder(folder);
                
		inbox.open(Folder.READ_WRITE);
                Message message[] = null;
                
                SearchTerm searchCondition = new SearchTerm() {
				@Override
				public boolean match(Message message) {
					try {
						if (message.getSubject().contains(keyword)) {
							return true;
						}
					} catch (MessagingException ex) {
						ex.printStackTrace();
					}
					return false;
				}
			};
            
                message = inbox.search(searchCondition); 
            
         if(model.getRowCount()>0)
            {
          while(model.getRowCount()>0)
           {
               model.removeRow(0);
           }
            }
             

             for(int i=message.length-1;i>=0;i--)
		{
                       model.addRow(new Object[]{message[i].getFrom()[0].toString(),message[i].getSubject(),message[i].getSentDate(),message[i].getSize()});
                 
		}
             
          SearchTerm searchCondition1 = new SearchTerm() {
				@Override
				public boolean match(Message message) {
					try {
						if (message.getFrom()[0].toString().matches("(.*)"+keyword+"(.*)")) {
							return true;
						}
					} catch (MessagingException ex) {
						ex.printStackTrace();
					}
					return false;
				}
			};
            
                message = inbox.search(searchCondition1); 
               
             for(int i=message.length-1;i>=0;i--)
		{
                       model.addRow(new Object[]{message[i].getFrom()[0].toString(),message[i].getSubject(),message[i].getSentDate(),message[i].getSize()});
                 
		}
		}
		catch (NoSuchProviderException e) {
			JOptionPane.showMessageDialog(null, e);
			
		}
		catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, e);
			
		}
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                    
                }
  }
 
 //Retrieve Mails Data from local Database
 public void retrieveMsg(String folder,javax.swing.JTable tbData)
 {  
    System.out.println("IN Retrieve");
     model=(DefaultTableModel)tbData.getModel(); 
     try
     {
         if(model.getRowCount()>0)
        {
          while(model.getRowCount()>0)
           {
               model.removeRow(0);
           }
        }
         
         st=con.createStatement();
         rs=st.executeQuery("select * from "+folder);
         while(rs.next())
         {
             model.addRow(new Object[]{rs.getString("msg_from"),rs.getString("msg_sub"),rs.getString("msg_dte"),rs.getString("msg_size")});
         }
         rs.close();
         st.close();
     }catch(Exception ex){}
 } 
 
 public String htmlBroker(String htText)
        {
            String str="";
            String[] str1=htText.split("<html><b>");
            for(int i=0;i<str1.length;i++)
            str=str1[i];
            String[] str2=str.split("</b></html>");
            for(int i=0;i<str2.length;i++)
            str=str2[i];
            return str;
        }  
 public String replyBroker(String str)
        {
            String[] str1=str.split("<");
            for(int i=0;i<str1.length;i++)
               str=str1[i];
            String[] str2=str.split(">");
            for(int i=0;i<str2.length;i++)
                    str=str2[i];
            return str;
        }

    public void writePart(Part p,javax.swing.JEditorPane textMsg) throws Exception {
         
         if (p.isMimeType("text/plain")) {
        
         if(attachText!=1)
         {
         
         textMsg.setContentType("text/plain");
         textMsg.setText((String) p.getContent());
         attachText=1;    
         }
      }
        //check if the content has attachment
      else if (p.isMimeType("multipart/*")) {
        
         Multipart mp = (Multipart) p.getContent();
         int count = mp.getCount();
         for (int i = 0; i < count; i++)
         { 
            
             writePart(mp.getBodyPart(i),textMsg);
            
             
         }
      } 
    }
    
    //Email Validation 
    public boolean validEmail(String str)
    {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        boolean flag;
        if(str.matches(EMAIL_REGEX)==true)
        {
            flag=true;
        }
        else
        {
            flag=false;
        }
        return flag;
    }
    //Check Intenet Connectivity 
    public boolean checkInternet()
    {
  boolean connectivity;
try {
    URL url = new URL("http://google.co.in");
    URLConnection conn = url.openConnection();
    conn.connect();
    connectivity = true;
} catch (Exception e) {
    connectivity = false;
}
    if(connectivity==true)
    {
        return true;
    }
    else
    {
        return false;
    }
    }
  
}
