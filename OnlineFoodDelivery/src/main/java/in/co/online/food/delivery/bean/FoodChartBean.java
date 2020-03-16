package in.co.online.food.delivery.bean;

public class FoodChartBean extends BaseBean{
	
	private long userId;
	private String UserName;
	private long foodId;
	private String foodName;
	private long chartId;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public long getFoodId() {
		return foodId;
	}

	public void setFoodId(long foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	

	public long getChartId() {
		return chartId;
	}

	public void setChartId(long chartId) {
		this.chartId = chartId;
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
