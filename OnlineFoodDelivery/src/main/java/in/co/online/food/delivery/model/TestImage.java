package in.co.online.food.delivery.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.co.online.food.delivery.util.DataUtility;
import in.co.online.food.delivery.util.JDBCDataSource;

public class TestImage {
	
	public void insertImage() {
		try {
			Connection conn=JDBCDataSource.getConnection();
			File file=new File("F:\\Online Food Delevered\\OnlineFoodDelivery\\src\\main\\webapp\\images\\pizza-1.jpg");
			FileInputStream fis=new FileInputStream(file);
			PreparedStatement ps=conn.prepareStatement("insert into OF_image (image) values(?)");
			
			ps.setBinaryStream(1,fis,(int)file.length());
			ps.executeUpdate();
			 
			ps.close();
			fis.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void getImage() {
		try {
			Connection conn=JDBCDataSource.getConnection();
			
			File file=new File("F:\\\\Online Food Delevered\\\\OnlineFoodDelivery\\\\src\\\\main\\\\webapp\\\\images\\\\pizza-1.jpg");
			FileOutputStream fos=new FileOutputStream(file);
			byte b[];
			Blob blob;
			
			PreparedStatement ps=conn.prepareStatement("select * from OF_image"); 
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				blob=rs.getBlob(1);
				b=blob.getBytes(1,(int)blob.length());
				fos.write(b);
			}
			
			ps.close();
			fos.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		
		TestImage im=new TestImage();
		im.getImage();
	
		
	}

}
