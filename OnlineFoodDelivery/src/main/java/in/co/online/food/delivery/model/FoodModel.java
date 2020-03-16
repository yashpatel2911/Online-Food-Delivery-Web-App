package in.co.online.food.delivery.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.online.food.delivery.bean.CategoryBean;
import in.co.online.food.delivery.bean.FoodBean;
import in.co.online.food.delivery.bean.RestaurantBean;
import in.co.online.food.delivery.exception.ApplicationException;
import in.co.online.food.delivery.exception.DatabaseException;
import in.co.online.food.delivery.exception.DuplicateRecordException;
import in.co.online.food.delivery.util.JDBCDataSource;


public class FoodModel {

	private static Logger log = Logger.getLogger(FoodModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM OF_FOOD");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	public Integer nextFoodId() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(FOODID) FROM OF_FOOD");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	
	public FoodBean findByName(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_FOOD WHERE FOODNAME=?");
		FoodBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FoodBean();
				bean.setId(rs.getLong(1));
				bean.setCategoryId(rs.getLong(2));
				bean.setCategoryName(rs.getString(3));
				bean.setFoodId(rs.getLong(4));
				bean.setFoodName(rs.getString(5));
				bean.setFoodPrice(rs.getLong(6));
				bean.setDisscount(rs.getLong(7));
				bean.setFoodDescription(rs.getString(8));
				bean.setFinalPrice(rs.getLong(9));
				bean.setImage(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
				bean.setRestaurantId(rs.getLong(15));
				bean.setRestaurantName(rs.getString(16));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}
	
	public FoodBean findByNameAndCategoryId(String login,long cid,long rId) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_FOOD WHERE FOODNAME=? AND CategoryID=? and restaurantId=?");
		FoodBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setLong(2,cid);
			pstmt.setLong(3,rId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FoodBean();
				bean.setId(rs.getLong(1));
				bean.setCategoryId(rs.getLong(2));
				bean.setCategoryName(rs.getString(3));
				bean.setFoodId(rs.getLong(4));
				bean.setFoodName(rs.getString(5));
				bean.setFoodPrice(rs.getLong(6));
				bean.setDisscount(rs.getLong(7));
				bean.setFoodDescription(rs.getString(8));
				bean.setFinalPrice(rs.getLong(9));
				bean.setImage(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
				bean.setRestaurantId(rs.getLong(15));
				bean.setRestaurantName(rs.getString(16));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}
	
	public FoodBean findByPk(long pk) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_FOOD WHERE ID=?");
		FoodBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FoodBean();
				bean.setId(rs.getLong(1));
				bean.setCategoryId(rs.getLong(2));
				bean.setCategoryName(rs.getString(3));
				bean.setFoodId(rs.getLong(4));
				bean.setFoodName(rs.getString(5));
				bean.setFoodPrice(rs.getLong(6));
				bean.setDisscount(rs.getLong(7));
				bean.setFoodDescription(rs.getString(8));
				bean.setFinalPrice(rs.getLong(9));
				bean.setImage(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
				bean.setRestaurantId(rs.getLong(15));
				bean.setRestaurantName(rs.getString(16));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}
	
	public long add(FoodBean bean) throws ApplicationException, DuplicateRecordException, FileNotFoundException {

		Connection conn = null;
		int pk = 0;

		FoodBean existbean = findByNameAndCategoryId(bean.getFoodName(),bean.getCategoryId(),bean.getRestaurantId());

		if (existbean != null) {
			throw new DuplicateRecordException("Food is already this Category And Restaurant");
		}

		CategoryModel model = new CategoryModel();
		
		CategoryBean uBean = model.findByPk(bean.getCategoryId());
		
		RestaurantModel rmodel = new RestaurantModel();
		
		RestaurantBean rBean = rmodel.findByPk(bean.getRestaurantId());
		
		
		
		bean.setFinalPrice(bean.getFoodPrice()*bean.getDisscount()/100);
		
		

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO OF_FOOD VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getCategoryId());
			pstmt.setString(3, uBean.getCategoryName());
			pstmt.setLong(4, nextFoodId());
			pstmt.setString(5, bean.getFoodName());
			pstmt.setLong(6, bean.getFoodPrice());
			pstmt.setLong(7, bean.getDisscount());
			pstmt.setString(8, bean.getFoodDescription());
			pstmt.setLong(9, bean.getFinalPrice());
			pstmt.setString(10,bean.getImage());
			pstmt.setString(11, bean.getCreatedBy());
			pstmt.setString(12, bean.getModifiedBy());
			pstmt.setTimestamp(13, bean.getCreatedDatetime());
			pstmt.setTimestamp(14, bean.getModifiedDatetime());
			pstmt.setLong(15,bean.getRestaurantId());
			pstmt.setString(16,rBean.getRestaurantName());
		
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}
	
	public void delete(FoodBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM OF_FOOD WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	
	public void update(FoodBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		FoodBean existbean = findByNameAndCategoryId(bean.getFoodName(),bean.getCategoryId(),bean.getRestaurantId());
		// Check if updated LoginId already exist
		if (existbean != null && !(existbean.getId() == bean.getId())) {
			throw new DuplicateRecordException("Food is already exist This Category And Restaurant");
		}
		
		CategoryModel model = new CategoryModel();
		
		CategoryBean uBean = model.findByPk(bean.getCategoryId());
		
		RestaurantModel rmodel = new RestaurantModel();
		
		RestaurantBean rBean = rmodel.findByPk(bean.getRestaurantId());
		bean.setFinalPrice(bean.getFoodPrice()*bean.getDisscount()/100);

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE OF_FOOD SET CategoryId=?,CategoryName=?,FoodId=?,FoodName=?,foodPrice=?,disscount=?,FoodDescription=?,finalPrice=?,Image=?,"
					+ "CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=?,restaurantId=?,restaurantName=? WHERE ID=?");
			pstmt.setLong(1, bean.getCategoryId());
			pstmt.setString(2, uBean.getCategoryName());
			pstmt.setLong(3, bean.getFoodId());
			pstmt.setString(4, bean.getFoodName());
			pstmt.setLong(5, bean.getFoodPrice());
			pstmt.setLong(6, bean.getDisscount());
			pstmt.setString(7, bean.getFoodDescription());
			pstmt.setLong(8, bean.getFinalPrice());
			pstmt.setString(9, bean.getImage());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
			pstmt.setLong(14,bean.getRestaurantId());
			pstmt.setString(15,rBean.getRestaurantName());
			pstmt.setLong(16, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	public List search(FoodBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	public List search(FoodBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_FOOD WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getFoodName() != null && bean.getFoodName().length() > 0) {
				sql.append(" AND FoodName like '" + bean.getFoodName() + "%'");
			}
			if (bean.getCategoryName() != null && bean.getCategoryName().length() > 0) {
				sql.append(" AND CategoryName like '" + bean.getCategoryName() + "%'");
			}
			if (bean.getRestaurantName() != null && bean.getRestaurantName().length() > 0) {
				sql.append(" AND RestaurantName like '" + bean.getRestaurantName() + "%'");
			}
			if (bean.getFoodId() > 0) {
				sql.append(" AND FoodId = " + bean.getFoodId());
			}
			
			if (bean.getCategoryId() > 0) {
				sql.append(" AND CategoryId = " + bean.getCategoryId());
			}
			
			if (bean.getRestaurantId() > 0) {
				sql.append(" AND RestaurantId = " + bean.getRestaurantId());
			}
			

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
		}

		System.out.println("user model search  :"+sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FoodBean();
				bean.setId(rs.getLong(1));
				bean.setCategoryId(rs.getLong(2));
				bean.setCategoryName(rs.getString(3));
				bean.setFoodId(rs.getLong(4));
				bean.setFoodName(rs.getString(5));
				bean.setFoodPrice(rs.getLong(6));
				bean.setDisscount(rs.getLong(7));
				bean.setFoodDescription(rs.getString(8));
				bean.setFinalPrice(rs.getLong(9));
				bean.setImage(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
				bean.setRestaurantId(rs.getLong(15));
				bean.setRestaurantName(rs.getString(16));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}
	
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from OF_FOOD");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		
		System.out.println("sql in list user :"+sql);
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FoodBean bean = new FoodBean();
				bean.setId(rs.getLong(1));
				bean.setCategoryId(rs.getLong(2));
				bean.setCategoryName(rs.getString(3));
				bean.setFoodId(rs.getLong(4));
				bean.setFoodName(rs.getString(5));
				bean.setFoodPrice(rs.getLong(6));
				bean.setDisscount(rs.getLong(7));
				bean.setFoodDescription(rs.getString(8));
				bean.setFinalPrice(rs.getLong(9));
				bean.setImage(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
				bean.setRestaurantId(rs.getLong(15));
				bean.setRestaurantName(rs.getString(16));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}
}
