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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CP Razer Gaming
 */
public class USER {//user class
  DBConnection dbconnection = new DBConnection();
    
//function to add a user
        public boolean addUser(String fname, String lname, String phone, String email)
        {
        PreparedStatement st;
        ResultSet rs;
        String addQuery = "INSERT INTO `user`(`first_name`, `last_name`, `phone`, `email`) VALUES (?,?,?,?)";


          try {
              st = dbconnection.createConnection().prepareStatement(addQuery);
               st.setString(1, fname);
              st.setString(2, lname);
              st.setString(3, phone);
              st.setString(4, email);

              return (st.executeUpdate()> 0);

          } catch (SQLException ex) {
              Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
                        return false;

          }


                //function to edit selected user

          }
    
      public boolean editUser(int id,String fname, String lname, String phone, String email)
            {
            PreparedStatement st;
    ResultSet rs;
    String editQuery = "UPDATE `user` SET `first_name`=?,`last_name`=?,`phone`=?,`email`=? WHERE `id`=?";
    
     
      try {
          st = dbconnection.createConnection().prepareStatement(editQuery);
           st.setString(1, fname);
          st.setString(2, lname);
          st.setString(3, phone);
          st.setString(4, email);
          st.setInt(5, id);
         
          return (st.executeUpdate()> 0);
          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
                    return false;

      }
            }
          //function to remove user
      public boolean removeUser(int id)
      {
       PreparedStatement st;
    ResultSet rs;
    String deleteQuery = "DELETE FROM `user` WHERE `id`=?";
    
     
      try {
          st = dbconnection.createConnection().prepareStatement(deleteQuery);
           
          st.setInt(1, id);
         
          return (st.executeUpdate()> 0);
          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
                    return false;

      }
      
      }
      
      public void fillUserJTable(JTable table)
      {
      PreparedStatement ps;
      ResultSet rs;
      String selectQuery = " SELECT * FROM `user`";
      
      try {
          ps = dbconnection.createConnection().prepareStatement(selectQuery);
          
          rs = ps.executeQuery();
          
          DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
          
          Object[] row;
          
          while (rs.next())
          {
              row = new Object[5];
              row [0] = rs.getInt(1);
              row[1] = rs.getString(2);
              row[2] = rs.getString(3);
              row[3] = rs.getString(4);
              row[4] = rs.getString(5);

              tableModel.addRow(row);
          }
          
          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      }
      
      
    }
    
    //function to edit selected user
    
    
    //function to remove user
    
    
    //function to fill the jtable

