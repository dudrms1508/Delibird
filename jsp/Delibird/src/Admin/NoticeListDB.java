package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class NoticeListDB {

	 private Connection conn=null;
	 private Statement stmt=null;
	 private ResultSet rs = null;
	 private PreparedStatement pstmt = null;


public NoticeListDB() {
	
	
	try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    String connectionUrl = "jdbc:sqlserver://localhost:1433; DataBaseName=DeilBird; user=sa; password=system;";
	    conn = DriverManager.getConnection(connectionUrl);
	    stmt = conn.createStatement();
	}catch(Exception e){
		e.printStackTrace();		
	} 
}
public void closeDB()throws Exception{
	if(stmt != null)
		stmt.close();
	if(conn != null)
		conn.close();
}

private void getResultSet()throws Exception{
	String sql;
	sql = "select 공지번호,제목,날짜 from 공지사항";
	rs = stmt.executeQuery(sql); 
}

public ResultSet getRs() throws Exception {
	getResultSet();
	return rs;
}

public void Write(String title, String body, String date) throws Exception{
	String sql;
	sql = "insert into 공지사항 values(NEXT VALUE  FOR notice_seq,'" + title + "', '" + body + "', '" + date + "');";
	pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
}

}
