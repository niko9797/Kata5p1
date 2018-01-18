/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author niko
 */
public class KATA5P1 {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        
        String query;
        String mail;
        
        
        Class.forName("org.sqlite.JDBC"); 
        Connection con = DriverManager.getConnection("jdbc:sqlite:/home/niko/Escritorio/KATA5");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from PEOPLE");
        
        while (rs.next()) {
            System.out.println(rs.getInt("Id") + "\t" + rs.getString("Name") + "\t" + rs.getString("Apellidos") + "\t" + rs.getString("Departamento"));
        }
        
        st.execute("create table if not exists MAIL('id' integer primary key autoincrement, 'mail' text not null);");
        
        BufferedReader rd = new BufferedReader(new FileReader(new File("emails.txt")));

        
        while ((mail = rd.readLine()) != null) {
            if (!mail.contains("@")) continue;
            query="insert into MAIL (mail) values('"+mail+"');";
            st.executeUpdate(query);
        }
        System.out.println("Emails added to table");
    }
    
}
