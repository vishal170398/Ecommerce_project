package e1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class StudCon {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/serv_crud", "root", "Jerry1703@");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static int save(student e) {
        int status = 0;
        try {
        	Connection con = StudCon.getConnection();
             
        	PreparedStatement pst = con.prepareStatement("insert into register values(?, ?, ?, ?, ?, ?, ?)"); 
        
            pst.setString(1, e.getFirstname());
            pst.setString(2, e.getLastname());
            pst.setString(3, e.getEmail());
            pst.setString(4, e.getWebsite());
            pst.setString(5, e.getPassword());
            pst.setString(6, e.getRepassword());
            pst.setLong(7, e.getPhone());

            status = pst.executeUpdate();
            
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return status;
    }
    
    public static int checklogin(student e) {
    	int status=0;
    	
    	String in_email= e.getEmail();
		String in_pass= e.getPassword();
		
    	
    	Connection con = StudCon.getConnection();
        
    	try {
			PreparedStatement pst = con.prepareStatement("select email,password from register");
			
			ResultSet rs = pst.executeQuery();
			
			
			
			List<String> email_list = new ArrayList();
			
			List<String> pass_list = new ArrayList();
			
			while(rs.next()) {
				
				email_list.add(rs.getString(1));
				pass_list.add(rs.getString(2));
				
				
			}
			
			int index=0;
			
			String found="";
			
			for(String s:email_list) {
						
				if(s.equals(in_email)) { 
					
					found=pass_list.get(index);
					
					if(found.equals(in_pass)) {
						status=1;//login user
						break;
						
					}else{
						status=2;//wrong pass
						break;
					}
					
				}else {
					status=3;//user not found

				}

				index++;
			}
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	return status;
    }
}
