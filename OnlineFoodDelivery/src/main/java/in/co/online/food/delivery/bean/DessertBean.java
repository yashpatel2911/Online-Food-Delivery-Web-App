package in.co.online.food.delivery.bean;

public class DessertBean extends BaseBean {

	private long restaurantId;
	private String restaurantName;
	private long dessertId;
	private String dessertName;
	private String dessertDescription;
	private String image;
	
	private long foodPrice;
	private long disscount;
	private long finalPrice;
		
	public long getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(long foodPrice) {
		this.foodPrice = foodPrice;
	}

	public long getDisscount() {
		return disscount;
	}

	public void setDisscount(long disscount) {
		this.disscount = disscount;
	}

	public long getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(long finalPrice) {
		this.finalPrice = finalPrice;
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

	public long getDessertId() {
		return dessertId;
	}

	public void setDessertId(long dessertId) {
		this.dessertId = dessertId;
	}

	public String getDessertName() {
		return dessertName;
	}

	public void setDessertName(String dessertName) {
		this.dessertName = dessertName;
	}

	public String getDessertDescription() {
		return dessertDescription;
	}

	public void setDessertDescription(String dessertDescription) {
		this.dessertDescription = dessertDescription;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String getKey() {
		return id+"";
	}

	@Override
	public String getValue() {
		return dessertName;
	}

}
