package test.google.gosn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestGosn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test5();
	}

	private static void test1() {
		// 为 Users 添加数据
		Users user = new Users();
		user.setId(110);
		user.setUsername("张三");
		user.setPassword("123456");
		// 创建一个 Gson 对象
		Gson gson = new Gson();
		// 把普通的JAVA对象转换为JSON格式
		String result = gson.toJson(user);
		System.out.println("JAVA TO JSON： " + result);

	}

	/**
	 * 复杂对象转换成json
	 */
	private static void test2() {
		PageData data = new PageData();

		// 为 Users 添加数据
		ArrayList<Users> users = new ArrayList<Users>();
		for (int i = 0; i < 3; i++) {
			Users user = new Users();
			user.setId(110);
			user.setUsername("张三_" + i);
			user.setPassword("123456_" + i);
			users.add(user);
			data.setUsers(users);
		}

		// 创建一个 Gson 对象
		Gson gson = new Gson();
		// 把普通的JAVA对象转换为JSON格式
		String result = gson.toJson(data);
		System.out.println("JAVA TO JSON： " + result);
	}

	private static void test3() {
		/**
		 * 其中，名字要和JAVABEAN中的对应起来，因为它是利用反射来完成该功能的
		 * 
		 * 名字不要加引号，VALUE值最好加单引号引号，每个键值对用逗号分割
		 * 
		 */
		String jsonString = "{id: 120, username:'李四', password:'654321'}";
		

		/**
		 * 把普通的JSON格式转换为JAVA简单对象
		 * 
		 * 第一个参数：普通的JSON格式，是字符串
		 * 
		 * 第二个对象：简单的POJO类，应注意该类至少符合POJO定义
		 * 
		 * 返回：它返回的就是这个“简单的POJO类”对象
		 */
		// 创建一个 Gson 对象
		Gson gson = new Gson();
		Users user = gson.fromJson(jsonString, Users.class);
		System.out.println("JSON TO JAVA: " + "user.getId() === " + user.getId() + ", user.getUsername() === "+ user.getUsername() + ", user.getPassword() === " + user.getPassword());
	}
	
	/**
	 * 把复杂json转换成对象
	 */
	private static void test4(){
		String json = "{users:[{id:'110',username:'张三_0',password='123456_0'},{id:'111',username:'张三_1',password='123456_1'},{id:'112',username:'张三_2',password='123456_2'}]}";
		// 创建一个 Gson 对象
		Gson gson = new Gson();
		PageData data = gson.fromJson(json, PageData.class);
		ArrayList<Users> arrayList = data.getUsers();
		if(arrayList != null && arrayList.size() > 0){
			for(int i = 0; i < arrayList.size() ; i++){
				Users user = arrayList.get(i);
				if(user != null){
					System.out.println("" + "user.getId() === " + user.getId() + ", user.getUsername() === "+ user.getUsername() + ", user.getPassword() === " + user.getPassword());
				}
			}
		}
	}
	
	private static void test5() {
		/**
		 * 对于泛型使用方式也是一样的，不过输出结果有对"[]"，表示是个数组
		 */
		List<Users> listUser = new ArrayList<Users>();
		Users user = null;
		for (int i = 0; i < 3; i++) {
			user = new Users();
			user.setId(110);
			user.setUsername("张三_" + i);
			user.setPassword("123456_" + i);
			user.setRegister(new Date());
			listUser.add(user);
		}
		System.out.println();
		// 遍历原始数据
		for (Users u : listUser) {
			System.out.println("JAVA 泛型: " + u.getId() + ", " + u.getUsername() + ", " + u.getPassword());
		}

		// 创建一个 Gson 对象
		Gson gson = new Gson();
		// 转换为JSON
		String s = gson.toJson(listUser);
		System.out.println("JAVA 泛型 TO JSON: " + s);

		/**
		 * 以上的方法基本能满足很多需求，但又一个重要的东西，那就是日期
		 * 
		 * 比如说我想在后端把数据库抓出来的日期给格式化，该如何处理呢？
		 * 
		 */
		Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		System.out.println("\n格式化后: " + gsonBuilder.toJson(user));
	}

}
