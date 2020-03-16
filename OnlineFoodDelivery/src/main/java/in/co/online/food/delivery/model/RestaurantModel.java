package in.co.online.food.delivery.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import in.co.online.food.delivery.bean.RestaurantBean;
import in.co.online.food.delivery.bean.UserBean;
import in.co.online.food.delivery.exception.ApplicationException;
import in.co.online.food.delivery.exception.DatabaseException;
import in.co.online.food.delivery.exception.DuplicateRecordException;
import in.co.online.food.delivery.util.JDBCDataSource;

public class RestaurantModel {

	private static Logger log = Logger.getLogger(RestaurantModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM OF_RESTAURANT");
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

	public Integer nextRestaurentId() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(RestaurantId) FROM OF_RESTAURANT");
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

	public RestaurantBean findByName(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_RESTAURANT WHERE RESTAURANTNAME=?");
		RestaurantBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RestaurantBean();
				bean.setId(rs.getLong(1));
				bean.setRestaurantUserId(rs.getLong(2));
				bean.setRestaurantUserName(rs.getString(3));
				bean.setRestaurantId(rs.getLong(4));
				bean.setRestaurantName(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setCity(rs.getString(7));
				bean.setDescription(rs.getString(8));
				bean.setRating(rs.getString(9));
				bean.setImage(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
				bean.setContectNo(rs.getString(15));

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
	
	
	public RestaurantBean findByNameAndUserId(String login,long Id) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_RESTAURANT WHERE RESTAURANTNAME=? and restaurantUserId=?");
		RestaurantBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setLong(2, Id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RestaurantBean();
				bean.setId(rs.getLong(1));
				bean.setRestaurantUserId(rs.getLong(2));
				bean.setRestaurantUserName(rs.getString(3));
				bean.setRestaurantId(rs.getLong(4));
				bean.setRestaurantName(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setCity(rs.getString(7));
				bean.setDescription(rs.getString(8));
				bean.setRating(rs.getString(9));
				bean.setImage(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
				bean.setContectNo(rs.getString(15));

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

	public RestaurantBean findByPk(long pk) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_RESTAURANT WHERE ID=?");
		RestaurantBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RestaurantBean();
				bean.setId(rs.getLong(1));
				bean.setRestaurantUserId(rs.getLong(2));
				bean.setRestaurantUserName(rs.getString(3));
				bean.setRestaurantId(rs.getLong(4));
				bean.setRestaurantName(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setCity(rs.getString(7));
				bean.setDescription(rs.getString(8));
				bean.setRating(rs.getString(9));
				bean.setImage(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
				bean.setContectNo(rs.getString(15));

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
	
	
	public RestaurantBean findByUserId(long pk) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_RESTAURANT WHERE RESTAURANTUSERID=?");
		RestaurantBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RestaurantBean();
				bean.setId(rs.getLong(1));
				bean.setRestaurantUserId(rs.getLong(2));
				bean.setRestaurantUserName(rs.getString(3));
				bean.setRestaurantId(rs.getLong(4));
				bean.setRestaurantName(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setCity(rs.getString(7));
				bean.setDescription(rs.getString(8));
				bean.setRating(rs.getString(9));
				bean.setImage(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
				bean.setContectNo(rs.getString(15));

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

	public long add(RestaurantBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		RestaurantBean existbean = findByNameAndUserId(bean.getRestaurantName(),bean.getRestaurantUserId());

		if (existbean != null) {
			throw new DuplicateRecordException("Restautant is already exists in This User");
		}

		UserModel model = new UserModel();
		System.out.println("Restaurant user Id======"+bean.getRestaurantUserId());
		UserBean uBean = model.findByPK(bean.getRestaurantUserId());

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO OF_RESTAURANT VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getRestaurantUserId());
			pstmt.setString(3, uBean.getFirstName() + " " + uBean.getLastName());
			pstmt.setLong(4, nextRestaurentId());
			pstmt.setString(5, bean.getRestaurantName());
			pstmt.setString(6, bean.getAddress());
			pstmt.setString(7, bean.getCity());
			pstmt.setString(8, bean.getDescription());
			pstmt.setString(9, bean.getRating());
			pstmt.setString(10, bean.getImage());
			pstmt.setString(11, bean.getCreatedBy());
			pstmt.setString(12, bean.getModifiedBy());
			pstmt.setTimestamp(13, bean.getCreatedDatetime());
			pstmt.setTimestamp(14, bean.getModifiedDatetime());
			pstmt.setString(15, bean.getContectNo());
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

	public void delete(RestaurantBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM OF_RESTAURANT WHERE ID=?");
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
	
	
	public void update(RestaurantBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		RestaurantBean existbean = findByName(bean.getRestaurantName());
		// Check if updated LoginId already exist
		if (existbean != null && !(existbean.getId() == bean.getId())) {
			throw new DuplicateRecordException("Restaurant is already exist");
		}
		
		UserModel model = new UserModel();
		UserBean uBean = model.findByPK(bean.getRestaurantUserId());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE OF_RESTAURANT SET RestaurantUserId=?,RestaurantUserName=?,RestaurantId=?,RestaurantName=?,Address=?,City=?,Description=?,Rating=?,Image=?,"
					+ "CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=?,ContectNo=? WHERE ID=?");
			pstmt.setLong(1, bean.getRestaurantUserId());
			pstmt.setString(2, uBean.getFirstName() + " " + uBean.getLastName());
			pstmt.setLong(3, bean.getRestaurantId());
			pstmt.setString(4, bean.getRestaurantName());
			pstmt.setString(5, bean.getAddress());
			pstmt.setString(6, bean.getCity());
			pstmt.setString(7, bean.getDescription());
			pstmt.setString(8, bean.getRating());
			pstmt.setString(9, bean.getImage());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
			pstmt.setString(14, bean.getContectNo());
			pstmt.setLong(15, bean.getId());
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
	
	
	public List search(RestaurantBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	public List search(RestaurantBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_RESTAURANT WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getRestaurantUserName() != null && bean.getRestaurantUserName().length() > 0) {
				sql.append(" AND RestaurantUserName like '" + bean.getRestaurantUserName() + "%'");
			}
			if (bean.getRestaurantName() != null && bean.getRestaurantName().length() > 0) {
				sql.append(" AND RestaurantName like '" + bean.getRestaurantName() + "%'");
			}
			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" AND City like '" + bean.getCity() + "%'");
			}
			
			if (bean.getRestaurantId() > 0) {
				sql.append(" AND RestaurantId = " + bean.getRestaurantId());
			}
			
			if (bean.getRestaurantUserId() > 0) {
				sql.append(" AND RestaurantUserId = " + bean.getRestaurantUserId());
			}
			

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("user model search  :"+sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RestaurantBean();
				bean.setId(rs.getLong(1));
				bean.setRestaurantUserId(rs.getLong(2));
				bean.setRestaurantUserName(rs.getString(3));
				bean.setRestaurantId(rs.getLong(4));
				bean.setRestaurantName(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setCity(rs.getString(7));
				bean.setDescription(rs.getString(8));
				bean.setRating(rs.getString(9));
				bean.setImage(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
				bean.setContectNo(rs.getString(15));

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
		StringBuffer sql = new StringBuffer("select * from OF_RESTAURANT");
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
				RestaurantBean bean = new RestaurantBean();
				bean.setId(rs.getLong(1));
				bean.setRestaurantUserId(rs.getLong(2));
				bean.setRestaurantUserName(rs.getString(3));
				bean.setRestaurantId(rs.getLong(4));
				bean.setRestaurantName(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setCity(rs.getString(7));
				bean.setDescription(rs.getString(8));
				bean.setRating(rs.getString(9));
				bean.setImage(rs.getString(10));
				bean.setCreatedBy(rs.getString(11));
				bean.setModifiedBy(rs.getString(12));
				bean.setCreatedDatetime(rs.getTimestamp(13));
				bean.setModifiedDatetime(rs.getTimestamp(14));
				bean.setContectNo(rs.getString(15));

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
