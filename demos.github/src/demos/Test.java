package demos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.omg.CORBA.PUBLIC_MEMBER;

import demos.DealSqliteData.SsidAvg;

public class Test {
	public enum SSID{B1_202east, B1_203Left, B1_207, B1_207_1, B1_207_2, B1_207_3, B1_207_4, B1_208, BSP};
	public static void main(String[] args) {
		/*System.out.println(SSID.B1_202east.toString());
		System.out.println(SSID.values().length);
		System.out.println(SSID.valueOf("B1-203Left".replace('-', '_')).ordinal());
		System.out.println(SSID.values()[3]);
		List<String> list = new ArrayList<String>();
		
		System.out.println(String.format("%4.2f", Math.sqrt(23)));*/
		List<String> list = new ArrayList<String>();
		for(SSID s : SSID.values()){
			list.add(s.toString());
		}
		for(String s : list){
			System.out.print(s + " ");
		}
		
		SSID[] s = SSID.values();
		System.out.println(s[2]);
		System.out.println(SSID.valueOf("B1_202east"));
		
	}

	private void zip() {
		System.out.println(String.format("%3.7f", 103.92423023083316));

		try {
			ZipFile zipFile = new ZipFile("D:/Desktop.zip");
			Enumeration<? extends ZipEntry> zips = zipFile.entries();
			System.out.println(zips.toString());
			while (zips.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) zips.nextElement();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						zipFile.getInputStream(entry)));
				String line = null;
				System.out.println(entry.getName());
				System.out.println("**********************************");
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int letterNumber(String string) {
		int en = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) > 0 && string.charAt(i) < 255) {
				en++;
			}
		}
		return en;
	}

	private void comparableTest() {
		List<String> list = new ArrayList<String>();
		list.add("电梯2");
		list.add("扶梯1");
		list.add("扶梯2");
		list.add("楼梯");
		list.add("电梯1");
		list.add("楼");
		list.add("扶梯3");
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.contains("电梯") && !o2.contains("电梯"))
					return -1;
				else if (!o1.contains("电梯") && o2.contains("电梯"))
					return 1;
				else if (o1.contains("电梯") && o2.contains("电梯"))
					return 0;
				else if (o1.contains("扶梯") && !o2.contains("扶梯"))
					return -1;
				else if (!o1.contains("扶梯") && o2.contains("扶梯"))
					return 1;
				else
					return 0;
			}
		});
		for (String s : list) {
			System.out.print(s + " ");
		}
	}
}
