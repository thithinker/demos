package zgl.test.nav;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.princeton.cs.introcs.Stopwatch;

public class StepCount {
	public static void main(String[] args) {
		 //System.out.println(new StepCount().count());;
		 //System.out.println(new StepCount().count1());;

		new StepCount().smoothSSID();
		
		new StepCount().calcDistance();
		//String.format("%.3f", 23.3333333);
	}
	
	public void smoothSSID(){
		try{
			BufferedReader br = new BufferedReader(new FileReader("./smooth_ssid.data"));
			String line = null;
			double currData;
			while((line = br.readLine()) != null){
				currData = Double.parseDouble(line);
				index = index % 5;
				filter2_step(index++, currData, origin, d1, d2);
			}
			br.close();
		}catch(NumberFormatException | IOException e){
			e.printStackTrace();
		}
	}
	

	public int count1() {
		int step = 0;
		final double UP = 1.22;
		final double DOWN = 0.82;
		boolean isFindingUp = true;
		try {
			BufferedReader br = new BufferedReader(new FileReader("./step_count.data"));
			double currentData = 0;
			String line = null;
			while ((line = br.readLine()) != null) {
				currentData = Double.parseDouble(line);
				index = index % 5;

				/*
				 * if(currentData < 1.122 && currentData > 1.015){ break; }
				 */

				if (filter2_step(index++, currentData, origin, d1, d2)) {
					stepp++;
				}
				;

				/*
				 * //System.out.prinn("&&&  "+currentData); arr[nn++ % countNew]
				 * = currentData; if (nn == countNew) { stepOfBeforeTurn +=
				 * getStep(arr); nn = 0; } if(isFindingUp && currentData > UP){
				 * isFindingUp = false; } if(!isFindingUp && currentData <
				 * DOWN){ step++; isFindingUp = true; }
				 */
			}

			System.out.println("^^^" + stepp);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return step;
	}

	float[] origin = new float[5];
	float[] d1 = new float[5];
	float[] d2 = new float[5];
	int index = 0;
	int stepp = 0;

	boolean filter2_step(int i, double value, float[] Ini, float[] FirFilter, float[] SecFilter) {

		Ini[i] = (float) value;
		FirFilter[i] = ((Ini[(i + 1) % 5] + Ini[(i + 2) % 5] * 4 + Ini[(i + 3) % 5] * 6 + Ini[(i + 4) % 5] * 4 + Ini[(i + 5) % 5]) / 16);
		SecFilter[i] = ((FirFilter[(i + 1) % 5] + FirFilter[(i + 2) % 5] * 4 + FirFilter[(i + 3) % 5] * 6 + FirFilter[(i + 4) % 5] * 4 + FirFilter[(i + 5) % 5]) / 16);
		System.out.println(SecFilter[i]);
		if ((SecFilter[(i + 4) % 5] >= SecFilter[(i + 3) % 5]) && (SecFilter[(i + 4) % 5] >= SecFilter[i])) {		//避免小幅波动： && SecFilter[i] > 1.13
			return true;
		} else
			return false;
	}

	public int count() {
		int step = 0;
		final double UP = 1.22;
		final double DOWN = 0.82;
		boolean isFindingUp = true;
		try {
			BufferedReader br = new BufferedReader(new FileReader("./step_count.data"));
			double currentData = 0;
			String line = null;
			while ((line = br.readLine()) != null) {
				currentData = Double.parseDouble(line);
				if (isFindingUp && currentData > UP) {
					isFindingUp = false;
				}
				if (!isFindingUp && currentData < DOWN) {
					step++;
					isFindingUp = true;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return step;
	}

	double first = 0;
	double second = 0;
	double third = 0;
	int stepCount = 0;
	int count1 = 24;
	int countNew = 20;
	double[] arr = new double[count1];
	double[] arrNew = new double[countNew];
	int nn = 0;
	int stepOfBeforeTurn = 0;

	private int getStep(double[] arr) {
		for (int i = 0; i < countNew; i++) {
			arrNew[i] = (arr[(i - 4 + count1) % count1] * 1 + arr[(i - 3 + count1) % count1] * 4 + arr[(i - 2 + count1) % count1] * 6
					+ arr[(i - 1 + count1) % count1] * 4 + arr[i] * 1) / 16;
		}

		for (int i = arr.length - 1; i > arr.length - 4; i--) {
			arr[i] = arr[i - 4];
		}

		/*
		 * for (int i = 0; i < count1; i++) { if (acceleration >
		 * AppConstants.UP) { isFindingUp = false; } }
		 */

		String str = "";
		for (int i = 0; i < arrNew.length; i++) {
			str += arrNew[i] + "\n";
		}
		System.out.print(str);
		String str1 = "";
		for (int i = 0; i < arr.length; i++) {
			str1 += arr[i] + ", ";
		}

		first = second;
		second = third;
		third = arrNew[0];
		if (second > first && second > third) {
			stepCount++;
		}
		first = second;
		second = third;
		third = arrNew[1];
		if (second > first && second > third) {
			stepCount++;
		}

		int stepOfBeforeTurn = 0;
		for (int i = 0; i < arrNew.length - 2; i++) {
			first = arrNew[i];
			second = arrNew[i + 1];
			third = arrNew[i + 2];

			if (second > first && second > third) {
				stepOfBeforeTurn++;
			}
		}
		return stepOfBeforeTurn;

	}

	private void calcDistance() {
		/*
		 * System.out.println("Math.tan(45): " + Math.tan(Math.toRadians(45)));
		 * System.out.println("Math.tan(45): " + Math.toDegrees(Math.atan(1)));
		 * System.out.println(Math.sin(Math.toRadians(30)));
		 * System.out.println(Math.toDegrees(Math.asin(0.5)));
		 * 
		 * 
		 * double x1 = 1, y1 = 1; double x2 = 20, y2 = 5; double x, y; double
		 * step = 5; double angle = Math.toDegrees(Math.atan((y2 - y1) / (x2 -
		 * x1))); x = step * 0.5 * Math.cos(Math.toRadians(angle)) + x1; y =
		 * step * 0.5 * Math.sin(Math.toRadians(angle)) + y1;
		 * //System.out.println("X:" + x + " Y: " + y);
		 * 
		 * //1 OldPoint{ p1:[1.1568793 384140514E7,3600191.8971091956],
		 * p2:[1.1568784122000001E7,3600198.698133333]} //NewPoint{ p:[1.1568793
		 * 787160587E7,3600191.6011787634] x1 = 1.1568793384140514E7; y1 =
		 * 3600191.8971091956; x2 = 1.1568784122000001E7; y2 =
		 * 3600198.698133333;
		 * 
		 * step = 1; angle = Math.atan((y2 - y1) / (x2 - x1)); x = step * 0.5 *
		 * Math.cos(angle) + x1; y = step * 0.5 * Math.sin(angle) + y1;
		 * System.out.println("X: " + x + " Y: " + y);
		 * System.out.println(Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1,
		 * 2)));;
		 * 
		 * //7 OldPoint{ p1:[1.1568790 563000003E7,3600193.9686222216],
		 * p2:[1.15687937835E7,3600191.6038666666]} //newPoint{ p:[1.1568793
		 * 384140514E7,3600191.8971091956]
		 * 
		 * x1 = 1.1568790563000003E7; y1 = 3600193.9686222216; x2 =
		 * 1.15687937835E7; y2 = 3600191.6038666666;
		 * 
		 * step = 7; angle = Math.atan((y2 - y1) / (x2 - x1)); x = step * 0.5 *
		 * Math.cos(angle) + x1; y = step * 0.5 * Math.sin(angle) + y1;
		 * System.out.println("X: " + x + " Y: " + y);
		 * System.out.println(Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1,
		 * 2)));;
		 */

		/*int x1 = 0;
		int y1 = 0;
		float angle = 30;
		int d = 5;
		System.out.println("Math.cos(Math.toRadians(angle)): " + Math.sin(Math.toRadians(angle)));
		System.out.println("Math.sin(Math.toRadians(angle)): " + Math.cos(Math.toRadians(angle)));
		double x = x1 + d * Math.sin(Math.toRadians(angle));
		double y = y1 + d * Math.cos(Math.toRadians(angle));
		System.out.println("[x, y]: [" + x + ", " + y + "]");

		System.out.println("******************************************************************");

		angle = 330;
		System.out.println("Math.cos(Math.toRadians(angle)): " + Math.cos(Math.toRadians(angle)));
		System.out.println("Math.sin(Math.toRadians(angle)): " + Math.sin(Math.toRadians(angle)));
		x = x1 + d * Math.cos(Math.toRadians(angle));
		y = y1 + d * Math.sin(Math.toRadians(angle));
		System.out.println("[x, y]: [" + x + ", " + y + "]");

		System.out.println("******************************************************************");

		angle = 355;
		System.out.println("Math.cos(Math.toRadians(angle)): " + Math.cos(Math.toRadians(angle)));
		System.out.println("Math.sin(Math.toRadians(angle)): " + Math.sin(Math.toRadians(angle)));
		x = x1 + d * Math.cos(Math.toRadians(angle));
		y = y1 + d * Math.sin(Math.toRadians(angle));
		System.out.println("[x, y]: [" + x + ", " + y + "]");*/

		// 19-bz
		double xx0 = 11568799.5804;
		double yy0 = 3600187.3473;
		// 19-bq
		double xx1 = 11568793.7835;
		double yy1 = 3600191.6039;

		System.out.println("Angle: " + Math.toDegrees(Math.atan((xx1 - xx0) / (yy1 - yy0))));
		System.out.println("Angle: " + Math.toDegrees(Math.atan((xx0 - xx1) / (yy0 - yy1))));
		System.out.println(getAngle(xx0, yy0, xx1, yy1));
		System.out.println(getAngle(xx1, yy1, xx0, yy0));

		/*int xx2 = 2, yy2 = 1;
		int xx3 = 4, yy3 = 2;
		System.out.println(getAngle(xx2, yy2, xx3, yy3));
		System.out.println(getAngle(xx3, yy3, xx2, yy2));*/
	}

	public int getAngle(double x0, double y0, double x1, double y1) {
		int angle = (int) Math.toDegrees(Math.atan((x1 - x0) / (y1 - y0)));
		//System.out.println("^^^^^ " + angle);

		/*if (y1 < y0) {
			return angle + 180;
		} else if (y1 > y0) {
			return (angle + 360) % 360;
		}*/

		if (x1 > x0 && y1 > y0) {
			return angle;
		}
		if (x1 > x0 && y1 < y0) {
			return angle + 180;
		}
		if (x1 < x0 && y1 < y0) {
			return angle + 180;
		}
		if (x1 < x0 && y1 > y0) {
			return angle + 360;
		}
		return (int) angle;
	}
}
