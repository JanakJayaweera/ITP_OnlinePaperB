
package DBAccess;

import DB.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DBAccess {
    private javax.swing.JComboBox ComboBox;
    private javax.swing.JTextField manufacturertxt;
    private javax.swing.JTextField woodtxt;
    private javax.swing.JTextField pricetxt;
    private javax.swing.JTextField qtytxt;
    private javax.swing.JTextField datetxt;
    
    int qty,id;
    String manufacturer,model,wood,price,date;
    
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public DBAccess(){
    //Default constructor
    }
    
    public DBAccess(javax.swing.JComboBox pComboBox){
    //Constructor for getManufacturers()
        con = ConnectionManager.connect();
        ComboBox = pComboBox;
        
        
    
    }
    
    public void getManufacturers(){
        
        try{
            String sql1 = "SELECT Man_Id FROM piano_manufacturers";
            pst = con.prepareStatement(sql1);
            rs = pst.executeQuery();
            
            while(rs.next()){
                String manID = rs.getString("Man_Id");
                
                ComboBox.addItem(manID);
        
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    
    }
    public DBAccess(String pmanufacturer,String pmodel,String pwood,String pprice,int pqty,String pdate){
    //Constructor for addPiano()
    
    con= ConnectionManager.connect();
        manufacturer = pmanufacturer;
        model= pmodel;
        wood= pwood;
        price = pprice;
        qty = pqty;
        date = pdate;
        
        addPiano();
    
    
    }
    
    public boolean addPiano(){
        
        
        int result =0;
        try{
           String sql1 = "SELECT Man_Id FROM piano_manufacturers WHERE Manufacturer = '"+manufacturer+"'";
           pst = con.prepareStatement(sql1);
           rs =pst.executeQuery();
           rs.next();
           int manuID = Integer.parseInt(rs.getString(1));
           
           String sql2 = "INSERT INTO piano(Man_Id,Model,Date_Added,Wood,Price,Qty) VALUES('"+manuID+"','"+model+"','"+date+"','"+wood+"','"+price+"','"+qty+"')";
           pst = con.prepareStatement(sql2);
           pst.execute();
           result = pst.executeUpdate();
        }
        catch(Exception e){
                System.out.println(e);
        }
        if(result>0){
            return true;
        }
        else{
            return false;
        }
    
    
    }
   
  
    
    public void getPianoModelNumber(){
        con= ConnectionManager.connect();
        
        try{
            String sql3 = "SELECT Model_No FROM piano";
            pst = con.prepareStatement(sql3);
            rs = pst.executeQuery();
            
            while(rs.next()){
            
                ComboBox.setSelectedItem(rs.getString(1));
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    
    
    }
    
    public void getManufactureName(int manID){
    
        try{
            String sql4 = "SELECT Manufacturer FROM piano_manufacturers WHERE Man_Id= '"+manID+"'";
            pst = con.prepareStatement(sql4);
            rs = pst.executeQuery();
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    
    }
    public DBAccess(int pid,javax.swing.JTextField pmanufacturertxt,javax.swing.JTextField pwoodtxt,javax.swing.JTextField ppricetxt,javax.swing.JTextField pqtytxt,javax.swing.JTextField pdatetxt){
    //Constructor for getPianoDetails(int id)
        con = ConnectionManager.connect();
        id= pid;
        manufacturertxt = pmanufacturertxt;
        woodtxt = pwoodtxt;
        pricetxt = ppricetxt;
        qtytxt = pqtytxt;
        datetxt = pdatetxt;
        
        getPianoDetails(id);
    
    }
    public void getPianoDetails(int id){
    
        try{
            String sql5 = "SELET * FROM piano WHERE Model_No = '"+id+"'";
            pst = con.prepareStatement(sql5);
            rs = pst.executeQuery();
            
            rs.next();
            manufacturertxt.setText(rs.getString(2));
            woodtxt.setText(rs.getString(5));
            pricetxt.setText(rs.getString(6));
            qtytxt.setText(rs.getString(7));
            datetxt.setText(rs.getString(4));
        }
        catch(Exception e){
            System.out.println(e);
        }
    
    }
    
}
