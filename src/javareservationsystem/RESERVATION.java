/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javareservationsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CP Razer Gaming
 */
public class RESERVATION {
    //in the reservation table, two forgein keys need to be added
    // 1 for the User
    //alter TABLE reservations ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
    //2 for the Room
    //alter TABLE reservations ADD CONSTRAINT fk_room_number FOREIGN KEY (room_number) REFERENCES rooms(r_number) ON DELETE CASCADE
    // Foreign key between table type and rooms
    //alter TABLE rooms ADD CONSTRAINT fk_type_id FOREIGN KEY (type) REFERENCES type(id) ON DELETE CASCADE
    
    
    
    
    
    
    
    
    
    
    //todo list
    // when a reservation is added the room should be set to reserved YES/No
    //check when adding a reservation if room is reserved or not
    // check if the date of checking in is > than the current date
    //check if the date of checking out is > the date of checking in
    DBConnection dbconnection = new DBConnection();
    ROOMS room = new ROOMS ();
    public boolean addReservation(int user_id, int room_number, String dateIn, String dateOut)
    {
    PreparedStatement st;
    String addQuery = "INSERT INTO `reservations`( `user_id`, `room_number`, `date_in`, `date_out`) VALUES (?,?,?,?)";
    
     
      try {
          st = dbconnection.createConnection().prepareStatement(addQuery);
          st.setInt(1, user_id);
          st.setInt(2, room_number);
          st.setString(3, dateIn);
          st.setString(4, dateOut);
           //function to set room to reserved
           if(room.isRoomReserved(room_number).equals("No"))
           {
           
          if (st.executeUpdate()> 0)
          {
              room.setRoomToReserved(room_number, "YES");
          return true;
          }else
          {
              
          return false;
          }
           }else
           {
                                   JOptionPane.showMessageDialog(null, "This room is occupied", " Room Reserved", JOptionPane.WARNING_MESSAGE);

           return false;
           }

          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
                    return false;

      }
 
      }
    public boolean editReservation(int reservation_Id,int user_id, int room_number,String dateIn, String dateOut)
            {
            PreparedStatement st;
    String editQuery ="UPDATE `reservations` SET `user_id`=?,`room_number`=?,`date_in`=?,`date_out`=? WHERE `id`=?";
    
     
      try {
          st = dbconnection.createConnection().prepareStatement(editQuery);
          st.setInt(1, user_id);
          st.setInt(2, room_number);
          st.setString(3,dateIn);
          st.setString(4,dateOut);
          st.setInt(5, reservation_Id);

         
          return (st.executeUpdate()> 0);
          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
                    return false;

      }
            }
 public boolean removeReservation(int reservation_id)
      {
       PreparedStatement st;
    ResultSet rs;
    String deleteQuery = "DELETE FROM `reservations` WHERE `id`=?";
    
     
      try {
          st = dbconnection.createConnection().prepareStatement(deleteQuery);
           
          st.setInt(1, reservation_id);
          //room number before the deletion of the reservation
          int room_number = getRoomNumberFromReservation(reservation_id);
         
 if (st.executeUpdate()> 0)
          {
              room.setRoomToReserved(room_number, "no");
          return true;
          }else
          {
              
          return false;
          }          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
                    return false;

      }
      
      }

 public void fillReservationsJTable(JTable table){
//Function to display room type in jtable
    {
      PreparedStatement ps;
      ResultSet rs;
      String selectQuery = " SELECT * FROM `reservations`";
      
      try {
          ps = dbconnection.createConnection().prepareStatement(selectQuery);
          
          rs = ps.executeQuery();
          
          DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
          
          Object[] row;
          
          while (rs.next())
          {
              row = new Object[5];
              row[0] = rs.getInt(1);
              row[1] = rs.getInt(2);
              row[2] = rs.getInt(3);
              row[3] = rs.getString(4);
              row[4] = rs.getString(5);
              

              tableModel.addRow(row);
          }
          
          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      }
      
    
}
//function to get the room number from a reservation
public int getRoomNumberFromReservation(int reservation)
{
 PreparedStatement ps;
      ResultSet rs;
      String selectQuery = " SELECT `room_number ` FROM `reservation` WHERE `id` = ?";
      
      try {
          ps = dbconnection.createConnection().prepareStatement(selectQuery);
          
          ps.setInt(1, reservation);
          
          rs = ps.executeQuery();
          
           if(rs.next())
           {
           return rs.getInt(1);
           }else
           {
           return 0;
           }
          
      } catch (SQLException ex) {
          Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
          return 0;
      }


}
}
