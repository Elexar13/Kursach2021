package vo;

public class Advertisement {
    private Integer adId;
    private User user; //userId
    private Integer userId;
    private String city;
    private String type; //rent or sale;
    private String adTitle;
    private Integer price;
    private Integer countOfRoom;
    private String description;
    private String status;
    private String filePath;
    private String isFavorite;

    public Advertisement() {
    }

    public Advertisement(Integer adId, Integer userId, String city, String type, String adTitle, Integer price, Integer countOfRoom, String description, String status, String filePath) {
        this.adId = adId;
        this.userId = userId;
        this.city = city;
        this.type = type;
        this.adTitle = adTitle;
        this.price = price;
        this.countOfRoom = countOfRoom;
        this.description = description;
        this.status = status;
        this.filePath = filePath;
    }

    public Advertisement(Integer userId, String city, String type, String adTitle, Integer price, Integer countOfRoom, String description) {
        this.userId = userId;
        this.city = city;
        this.type = type;
        this.adTitle = adTitle;
        this.price = price;
        this.countOfRoom = countOfRoom;
        this.description = description;
    }

    public Advertisement(String city, String type, Integer price, Integer countOfRoom) {
        this.city = city;
        this.type = type;
        this.price = price;
        this.countOfRoom = countOfRoom;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCountOfRoom() {
        return countOfRoom;
    }

    public void setCountOfRoom(Integer countOfRoom) {
        this.countOfRoom = countOfRoom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filepath) {
        this.filePath = filepath;
    }
}
