/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javareservationsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CP Razer Gaming
 */
public class ROOMS {
DBConnection dbconnection = new DBConnection();
    public void fillRooms_Type_JTable(JTable table){
//Function to display room type in jtable
    {
      PreparedStatement ps;
      ResultSet rs;
      String selectQuery = " SELECT * FROM `type`";
      
      try {
          ps = dbconnection.createConnection().prepareStatement(selectQuery);
          
          rs = ps.executeQuery();
          
          DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
          
          Object[] row;
          
          while (rs.next())
          {
              row = new Object[3];
              row [0] = rs.getInt(1);
              row[1] = rs.getString(2);
              row[2] = rs.getString(3);
              

              tableModel.addRow(row);
          }
          
          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      }
      
    
}
     public void fillRoomsJTable(JTable table){
//Function to display all rooms  in jtable
    {
      PreparedStatement ps;
      ResultSet rs;
      String selectQuery = " SELECT * FROM `rooms`";
      
      try {
          ps = dbconnection.createConnection().prepareStatement(selectQuery);
          
          rs = ps.executeQuery();
          
          DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
          
          Object[] row;
          
          while (rs.next())
          {
              row = new Object[4];
              row[0] = rs.getInt(1);
              row[1] = rs.getInt(2);
              row[2] = rs.getString(3);
              row[3] = rs.getString(4);

              tableModel.addRow(row);
          }
          
          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      }
      
    
}
    //Function to fill combox with room type id
    public void fillRooms_Type_JCombobox(JComboBox comboBox){
    {
      PreparedStatement ps;
      ResultSet rs;
      String selectQuery = " SELECT * FROM `type`";
      
      try {
          ps = dbconnection.createConnection().prepareStatement(selectQuery);
          
          rs = ps.executeQuery();
          
          while (rs.next())
          {
             
 
              comboBox.addItem(rs.getInt(1));
          }
          
          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      }
      
    //Function to add a new room
}
public boolean addRoom(int number,int type, String phone)
    {
    PreparedStatement st;
    ResultSet rs;
    String addQuery = "INSERT INTO `rooms`(`r_number`, `type`, `phone`, `reserved`) VALUES (?,?,?,?)";
    
     
      try {
          st = dbconnection.createConnection().prepareStatement(addQuery);
          st.setInt(1, number);
          st.setInt(2, type);
          st.setString(3, phone);
          // when a new room added
          //the reserved column will be set to no
          st.setString(4, "No");

         
          return (st.executeUpdate()> 0);
          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
                    return false;

      }
          
         
            //function to edit selected user
          
      }
      public boolean editRoom(int number, int type, String phone, String isReserved)
            {
            PreparedStatement st;
    ResultSet rs;
    String editQuery ="UPDATE `rooms` SET `type`=?,`phone`=?,`reserved`=? WHERE `r_number`=?";
    
     
      try {
          st = dbconnection.createConnection().prepareStatement(editQuery);
           st.setInt(1, type);
          st.setString(2, phone);
          st.setString(3, isReserved);
          st.setInt(4, number);
         
          return (st.executeUpdate()> 0);
          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
                    return false;

      }
            }
// function to remove selected room
 public boolean removeRoom(int roomNumber)
      {
       PreparedStatement st;
    ResultSet rs;
    String deleteQuery = "DELETE FROM `rooms` WHERE `r_number`=?";
    
     
      try {
          st = dbconnection.createConnection().prepareStatement(deleteQuery);
           
          st.setInt(1, roomNumber);
         
          return (st.executeUpdate()> 0);
          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
                    return false;

      }
      
      }
 public boolean setRoomToReserved(int number, String isReserved)
            {
            PreparedStatement st;
    
    String editQuery ="UPDATE `rooms` SET `reserved`=? WHERE `r_number`=?";
    
     
      try {
          st = dbconnection.createConnection().prepareStatement(editQuery);
 
          st.setString(1, isReserved); 
          st.setInt(2, number);
         
          return (st.executeUpdate()> 0);
          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
                    return false;

      }
            }
 
 
 //function to check if a room is already reserved
 
 public String isRoomReserved(int number)
            {
            PreparedStatement st;
    ResultSet rs;
    String editQuery ="SELECT `reserved` FROM `rooms` WHERE `r_number`=?";
    
     
      try {
          st = dbconnection.createConnection().prepareStatement(editQuery);
 
          st.setInt(1, number);
         rs = st.executeQuery();
          if(rs.next())
          {
          return rs.getString(1);
          }
          else
          {
          return "";
          }
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
                    return "";

      }
            }
 
 
 //create function to check if a room is already reserved
}