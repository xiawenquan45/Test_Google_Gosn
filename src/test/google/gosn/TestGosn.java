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
		// Ϊ Users �������
		Users user = new Users();
		user.setId(110);
		user.setUsername("����");
		user.setPassword("123456");
		// ����һ�� Gson ����
		Gson gson = new Gson();
		// ����ͨ��JAVA����ת��ΪJSON��ʽ
		String result = gson.toJson(user);
		System.out.println("JAVA TO JSON�� " + result);

	}

	/**
	 * ���Ӷ���ת����json
	 */
	private static void test2() {
		PageData data = new PageData();

		// Ϊ Users �������
		ArrayList<Users> users = new ArrayList<Users>();
		for (int i = 0; i < 3; i++) {
			Users user = new Users();
			user.setId(110);
			user.setUsername("����_" + i);
			user.setPassword("123456_" + i);
			users.add(user);
			data.setUsers(users);
		}

		// ����һ�� Gson ����
		Gson gson = new Gson();
		// ����ͨ��JAVA����ת��ΪJSON��ʽ
		String result = gson.toJson(data);
		System.out.println("JAVA TO JSON�� " + result);
	}

	private static void test3() {
		/**
		 * ���У�����Ҫ��JAVABEAN�еĶ�Ӧ��������Ϊ�������÷�������ɸù��ܵ�
		 * 
		 * ���ֲ�Ҫ�����ţ�VALUEֵ��üӵ��������ţ�ÿ����ֵ���ö��ŷָ�
		 * 
		 */
		String jsonString = "{id: 120, username:'����', password:'654321'}";
		

		/**
		 * ����ͨ��JSON��ʽת��ΪJAVA�򵥶���
		 * 
		 * ��һ����������ͨ��JSON��ʽ�����ַ���
		 * 
		 * �ڶ������󣺼򵥵�POJO�࣬Ӧע��������ٷ���POJO����
		 * 
		 * ���أ������صľ���������򵥵�POJO�ࡱ����
		 */
		// ����һ�� Gson ����
		Gson gson = new Gson();
		Users user = gson.fromJson(jsonString, Users.class);
		System.out.println("JSON TO JAVA: " + "user.getId() === " + user.getId() + ", user.getUsername() === "+ user.getUsername() + ", user.getPassword() === " + user.getPassword());
	}
	
	/**
	 * �Ѹ���jsonת���ɶ���
	 */
	private static void test4(){
		String json = "{users:[{id:'110',username:'����_0',password='123456_0'},{id:'111',username:'����_1',password='123456_1'},{id:'112',username:'����_2',password='123456_2'}]}";
		// ����һ�� Gson ����
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
		 * ���ڷ���ʹ�÷�ʽҲ��һ���ģ������������ж�"[]"����ʾ�Ǹ�����
		 */
		List<Users> listUser = new ArrayList<Users>();
		Users user = null;
		for (int i = 0; i < 3; i++) {
			user = new Users();
			user.setId(110);
			user.setUsername("����_" + i);
			user.setPassword("123456_" + i);
			user.setRegister(new Date());
			listUser.add(user);
		}
		System.out.println();
		// ����ԭʼ����
		for (Users u : listUser) {
			System.out.println("JAVA ����: " + u.getId() + ", " + u.getUsername() + ", " + u.getPassword());
		}

		// ����һ�� Gson ����
		Gson gson = new Gson();
		// ת��ΪJSON
		String s = gson.toJson(listUser);
		System.out.println("JAVA ���� TO JSON: " + s);

		/**
		 * ���ϵķ�������������ܶ����󣬵���һ����Ҫ�Ķ������Ǿ�������
		 * 
		 * ����˵�����ں�˰����ݿ�ץ���������ڸ���ʽ��������δ����أ�
		 * 
		 */
		Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		System.out.println("\n��ʽ����: " + gsonBuilder.toJson(user));
	}

}
