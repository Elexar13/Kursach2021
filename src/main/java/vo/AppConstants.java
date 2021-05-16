package vo;

import java.util.HashMap;
import java.util.Map;

public class AppConstants {

    //DB info
	public static String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
	public static String DATABASE_USER = "max";
	public static String DATABASE_PASSWORD = "2187";

	public static Map<String, String> CITY_MAP = new HashMap<>();
	static {
		CITY_MAP.put("niko", "Миколаїв");
		CITY_MAP.put("odessa", "Миколаїв");
		CITY_MAP.put("kiev", "Миколаїв");
		CITY_MAP.put("kharkov", "Миколаїв");
		CITY_MAP.put("dnepr", "Миколаїв");
		CITY_MAP.put("zapor", "Миколаїв");
		CITY_MAP.put("lvov", "Миколаїв");
		CITY_MAP.put("rog", "Миколаїв");
		CITY_MAP.put("vinnitsa", "Миколаїв");
		CITY_MAP.put("kherson", "Миколаїв");
		CITY_MAP.put("chernihiv", "Миколаїв");
		CITY_MAP.put("poltava", "Миколаїв");
		CITY_MAP.put("cherkassi", "Миколаїв");
		CITY_MAP.put("khmel", "Миколаїв");
		CITY_MAP.put("chernivtsi", "Миколаїв");
		CITY_MAP.put("zhitomir", "Миколаїв");
		CITY_MAP.put("summi", "Миколаїв");
		CITY_MAP.put("rivne", "Миколаїв");
		CITY_MAP.put("ternopil", "Миколаїв");
		CITY_MAP.put("krop", "Миколаїв");
	}

	public static Map<String, String> TYPE_MAP = new HashMap<>();
	static {
		TYPE_MAP.put("sale", "Придбати нерухомість");
		TYPE_MAP.put("rent", "Арендувати нерухомість");
	}

	//////Action names
	//UserServlet
	public static final String GET_USER = "getUser";
	public static final String ADD_USER = "addUser";
	public static final String GET_CURRENT_USER = "getCurrentUser";
	public static final String LOG_OUT = "logOut";
	public static final String GET_USER_BY_ID = "getUserById";

	//AdvertisementServlet
	public static final String GET_ALL_ADVERTISEMENT = "getAllAdvertisement";
	public static final String GET_FILTERED_ADVERTISEMENT = "getFilteredAdvertisement";
	public static final String ADD_ADVERTISEMENT_TO_FAVORITES = "addAdvertisementToFavorite";
	public static final String GET_FAVORITES_FOR_CURRENT_USER = "getFavoritesForCurrentUser";
	public static final String GET_FAVOR_BY_USER_ID_AND_AD_ID = "getFavorByIdAndAdId";
	public static final String REMOVE_FROM_FAVORITES_FOR_CURRENT_USER = "removeFromFavoritesForCurrentUser";
	public static final String GET_ADVERTISEMENTS_OF_CURRENT_USER = "getAdvertisementsOfCurrentUser";
}
