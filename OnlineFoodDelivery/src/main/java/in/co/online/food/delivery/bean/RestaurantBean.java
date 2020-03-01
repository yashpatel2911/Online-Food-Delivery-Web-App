package in.co.online.food.delivery.bean;

public class RestaurantBean extends  BaseBean {

	
	private String restaurantUserName;
	private long restaurantUserId;
	private long restaurantId;
	private String restaurantName;
	private String address;
	private String city;
	private String description;
	private String rating;
	private String image;
	private String contectNo;
	
	
	
	
	
	
	
	public String getContectNo() {
		return contectNo;
	}

	public void setContectNo(String contectNo) {
		this.contectNo = contectNo;
	}

	public String getRestaurantUserName() {
		return restaurantUserName;
	}

	public void setRestaurantUserName(String restaurantUserName) {
		this.restaurantUserName = restaurantUserName;
	}

	public long getRestaurantUserId() {
		return restaurantUserId;
	}

	public void setRestaurantUserId(long restaurantUserId) {
		this.restaurantUserId = restaurantUserId;
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getKey() {
		return id+"";
	}
	public String getValue() {
		return restaurantName;
	}

}
