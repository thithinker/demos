package zgl.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

public class JsonTest {
	public static void main(String[] args) {
		
		//new JsonTest().jsonRead();
		
		/*String[] strs = {"123", "333"};
		JSONArray jsons = new JSONArray();
		for(int i = 0; i < strs.length; i++){
			JSONObject obj = new JSONObject();
			obj.put("id", strs[i]);
			jsons.put(obj);
		}
		System.out.println(jsons.toString());*/
		
		String str = "[{'birthday':'1953-09-29','gender':'F','name':'Bojan'},{'birthday':'1958-10-31','gender':'M','name':'Prasadram'},{'birthday':"
				+ "'1958-05-21','gender':'M','name':'Yinghua'}]";
		JSONArray jsonArray = new JSONArray(str);
		String first_name = "";
		String birth_date = "";
		String gender = "";
		for(int i = 0; i < jsonArray.length(); i++){
			JSONObject obj = jsonArray.getJSONObject(i);
			first_name += obj.getString("name") + ", ";
			birth_date += obj.getString("birthday") + ", ";
			gender += obj.getString("gender") + ", ";
			
		}
		System.out.println(first_name + " " + birth_date + " " + gender);
		
		JSONObject obj = new JSONObject("{\"id\":\"10023\"}");
		System.out.println(obj.getString("id"));
		
	}
	
	public void json1(){
		JSONObject jObject = new JSONObject("{'name':zgl, 'age':25}");
		String name = jObject.getString("name");
		int age = jObject.getInt("age");
		System.out.println("name: " + name + "\nage: " + age);
	}
	
	public void json2(){
		String arr = "[{'name': 'cally', 'age': 25}, {'name':xiaoxiao, 'age': 10}]";
		JSONArray jArray = new JSONArray(arr);
		for(int i = 0; i < jArray.length(); i++){
			String name1 = jArray.getJSONObject(i).getString("name");
			int age1 = jArray.getJSONObject(i).getInt("age");
			System.out.println("name = " + name1 + " age = " + age1);
		}
	}
	
	public void json3(){
		String str = "{'name': zgl, 'age':25, 'book':['book1', 'book2']}";
		JSONObject jObject = new JSONObject(str);
		for(int i = 0; i < jObject.getJSONArray("book").length(); i++){
			System.out.println("book: " + jObject.getJSONArray("book").getString(i));
		}
		
	}
	
	/**
	 * JSONStringer可以用来快速构建一个JSON格式的文本，并转换成String，可以写入文件
	 * 		JSONStringer是JSONWriter的子类
	 */
	public void jsonWrite(){
		JSONStringer jsonStringer = new JSONStringer();
		String str = jsonStringer.object().key("name").value("zgl").key("age").value(25).endObject().toString();
		System.out.println(str);
	}
	
	public void jsonWrite2(){
		JSONStringer jsonStringer = new JSONStringer();
		JSONObject obj2 = new JSONObject();
		JSONObject obj3 = new JSONObject();
		JSONObject obj4 = new JSONObject();
		obj4.put("title", "book1").put("price", "$11");
		obj3.put("book", obj4);
		obj3.put("author", new JSONObject().put("name", "author1"));
		
		JSONObject obj5 = new JSONObject();
		JSONObject obj6 = new JSONObject();
		obj6.put("title", "book2").put("price", "$22");
		obj5.put("book", obj6);
		obj5.put("author", new JSONObject().put("name", "author2"));
		
		JSONArray obj7 = new JSONArray();
		obj7.put(obj3).put(obj5);
		
		obj2.put("title", "BOOK");
		obj2.put("signing", obj7);
		
		jsonStringer.object().key("session").value(obj2).endObject();
		System.out.println(jsonStringer.toString());
		
		PrintWriter out;
		try {
			out = new PrintWriter(new FileOutputStream("json.out"));
			out.println(jsonStringer.toString());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void jsonRead(){
		try {
			JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader(new File("json.out"))));
			System.out.println(jsonObject.getJSONObject("session").getJSONArray("signing"));
			
		} catch (JSONException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
