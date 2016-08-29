import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Demo extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
	try {	
        response.addHeader("Access-Control-Allow-Origin", "*");

		 Class.forName("com.mysql.jdbc.Driver");
		 
	 //creating connection with the database 
        Connection con=DriverManager.getConnection
                       ("jdbc:mysql://localhost/testing","root","123");
        PreparedStatement ps =con.prepareStatement("select * from list");
        ResultSet rs =ps.executeQuery();
        PrintWriter out = response.getWriter();

        JSONArray jarray = new JSONArray();
        JSONObject mainobj = new JSONObject();
        int count = 0;
        while(rs.next()){
            	 JSONObject json = new JSONObject();
				 String name = rs.getString("Name");
				 String Item = rs.getString("Item");
				 int number = rs.getInt("number");
				 json.put("name", name);
				 json.put("ITEM", Item);
				 json.put("number", number);
				 jarray.put(json);				 
			 }
        	out.print(jarray.toString());
		 } catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
