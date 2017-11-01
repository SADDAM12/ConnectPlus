package jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import util.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.scene.image.Image;
import main.WorkStation;


public class DBManager {

private static DBConnector dbc;

static{
	try {
		dbc=new DBConnector();
	} catch (SQLException e) {
		// TODO Auto-generated catch dblock
		e.printStackTrace();
	}
}

	public static void registerUser(String userName,String email,String password) throws Exception {

		String sql="Insert into logininfo(username,email,password,ip) values (?,?,?,?)";

		PreparedStatement pst=dbc.getPreparedStatement(sql);
		pst.setString(1, userName);
		pst.setString(2, email);
		pst.setString(3, password);
		pst.setString(4, util.getIp());
		pst.executeUpdate();
	}

	public static boolean loginUser(String userName,String password) throws Exception {

		String sql="select password from logininfo where username=?";
		String pass = null;
                //String ip=util.getIp() ;
                String ip="127.0.0.1";
		PreparedStatement pst=dbc.getPreparedStatement(sql);
		pst.setString(1, userName);

		ResultSet rs=	pst.executeQuery();
		if (rs.next()) {
				pass = rs.getString("password");
		}
	if(pass.equals(password))
	{
		String sql2="Update  logininfo Set ip=? Where username=?";
		PreparedStatement pst2=dbc.getPreparedStatement(sql2);
		pst2.setString(1,ip);
		pst2.setString(2,userName );
		pst2.executeUpdate();

		return true;
	}
		else
			return false;

	}

	public static HashMap<String, String> showFriendList() throws SQLException {

		String sql="Select username,ip from logininfo";
		String user = null,ip="127.0.0.1";
	//List<String> userlist = new ArrayList<String>();
		HashMap<String, String> userNip = new HashMap<String, String>();

		PreparedStatement pst=dbc.getPreparedStatement(sql);

		ResultSet rs=	pst.executeQuery();

		while (rs.next()) {
			user = rs.getString("userName");
			ip = rs.getString("ip");
			//userlist.add(user);
			userNip.put(user,ip);
		}
return userNip;
	}

        
        public static void updateUser(String username,String Name,String address,InputStream imgLink) throws Exception {

		String sql="update  userinfo set name=?, address=?,propic=? where username=?";

		PreparedStatement pst=dbc.getPreparedStatement(sql);
		pst.setString(1, Name);
		pst.setString(2, address);
                
                pst.setBlob(3,imgLink );
              
		//pst.setString(3, password);
		pst.setString(4, username);
		pst.executeUpdate();
	}

        public static void getUserinfo(String userName) throws FileNotFoundException, SQLException, IOException{
            
             File file=new File(userName+".jpg");
            FileOutputStream fos=new FileOutputStream(file);
            byte b[];
            Blob blob;
            
            PreparedStatement ps=dbc.getPreparedStatement("select name from userinfo where username=?"); 
            ps.setString(1, userName);
            ResultSet rs=ps.executeQuery();
            
            String lbl =null;
        if(rs.next())
             lbl = rs.getString("name");
            
//            while(rs.next()){
//                
//                blob=rs.getBlob("propic");
//                b=blob.getBytes(1,(int)blob.length());
//                fos.write(b);
//            }
           // Image img =new Image("file:"+userName+".jpg",100,150,true,true);
    
            WorkStation.friendName.setText(lbl);
           // WorkStation.friendsPic.setImage(img);
        }
        
        
}
