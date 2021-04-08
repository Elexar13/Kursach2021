package vo;

public class Apartment {
	private int apartmentId;
	private Address address;
	private int userId;
	private String forSale;
	private String forRent;
	private String status;

	public Apartment(Address address, int userId, String forSale, String forRent, String status) {
		this.address = address;
		this.userId = userId;
		this.forSale = forSale;
		this.forRent = forRent;
		this.status = status;
	}

	public int getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(int apartmentId) {
		this.apartmentId = apartmentId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getForSale() {
		return forSale;
	}

	public void setForSale(String forSale) {
		this.forSale = forSale;
	}

	public String getForRent() {
		return forRent;
	}

	public void setForRent(String forRent) {
		this.forRent = forRent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
