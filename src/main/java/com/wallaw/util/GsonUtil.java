package com.wallaw.util;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/***
 * eee
 * @see https://code.google.com/p/google-gson/
 * @see https://github.com/google/gson/blob/master/UserGuide.md
 * @author wangqingsong
 * 
 */
public class GsonUtil {
	//modifier static, GSON 默认把所有transient 和 static 修饰的变量给排除在外
	private static final int MODIFIER_STATIC = 8;

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final Gson NAMING_GSON = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
	
	public static final Gson INCLUDE_TRANSIENT_GSON = new GsonBuilder().
			excludeFieldsWithModifiers(MODIFIER_STATIC).create();//这里只将static的排除在外，这样就可以序列化transient修饰的变量

	public static final Gson NORMAL_GSON = new Gson();

	/***
	 * 把Json转化为对象
	 * 
	 * @param <T>
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> T getObjectFromJson(String json, Class<T> classOfT) {
		return NORMAL_GSON.fromJson(json, classOfT);
	}

	/**
	 * 反序列化成数组
	 * @param json="[{}]"格式
	 * @param classOfT 类型
	 */
//	public static <T> ArrayList<T> getObjectsFromJson(String json, Class<T> classOfT) {
//		Type type = new TypeToken<ArrayList<JsonObject>>() {}.getType();
//		ArrayList<JsonObject> jsonObjs = new Gson().fromJson(json, type);
//		ArrayList<T> listOfT = new ArrayList<>();
//		for (JsonObject jsonObj : jsonObjs) {
//			listOfT.add(new Gson().fromJson(jsonObj, classOfT));
//		}
//		return listOfT;
//	}

	/***
	 * 把Json转化为对象(带有DateFormat:"yyyy-MM-dd HH:mm:ss") 与getObjectFromJson区别:
	 * Date字段 : getObjectFromJson :"yyyy-MM-dd HH:mm:ss.0"
	 * getDateFormatObjectFromJson:"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param <T>
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> T getDateFormatObjectFromJson(String json, Class<T> classOfT) {
		return NAMING_GSON.fromJson(json, classOfT);
	}

	/**
	 * 将对象实例转化到Gson字符串; 如果在此class中某些属性表明了@Expose; 则此属性不会被Gson实例化到json字符串中;
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJson(Object obj) {
		return NAMING_GSON.toJson(obj);
	}

	/***
	 * 同步固定的版本信息,对对象进行json序列化;
	 * 
	 * @param appVersion
	 * @param obj
	 * @return
	 */
	public static String getJson(double appVersion, Object obj) {
		final Gson gson = getVersionGson(appVersion);
		return gson.toJson(obj);
	}

	/****
	 * 通过版本号获取Gson;
	 * 
	 * @param appVersion
	 * @return
	 */
	public static Gson getVersionGson(double appVersion) {
		Gson VERSION_NAMING_GSON = new GsonBuilder().setDateFormat(DATE_FORMAT).setVersion(appVersion).create();
		return VERSION_NAMING_GSON;
	}
	
	public static <T>T getObjectWithTransientFeild(String json, Type typeOfT){
		return INCLUDE_TRANSIENT_GSON.fromJson(json, typeOfT);
	}
	
	public static String toJsonWithTransientFeild(Object obj, Type typeOfT){
		return INCLUDE_TRANSIENT_GSON.toJson(obj, typeOfT);
	}

	// public static void main(String[] dsada){
	// Map<String,String> guigePriceMap = getObjectFromJson("", Map.class);
	// System.out.println(guigePriceMap);
	// }
}
