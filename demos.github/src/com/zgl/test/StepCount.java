package com.zgl.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StepCount {
	public static void main(String[] args) {
		//System.out.println(new StepCount().count());;
		new StepCount().calcDistance();;
	}
	
	public int count(){
		int step = 0;
		final double UP = 1.22;
		final double DOWN = 0.82;
		boolean isFindingUp = true;
		try {
			BufferedReader br = new BufferedReader(new FileReader("./step_count.data"));
			double currentData = 0;
			String line = null;
			while((line = br.readLine()) != null){
				currentData = Double.parseDouble(line);
				if(isFindingUp && currentData > UP){
					isFindingUp = false;
				}
				if(!isFindingUp && currentData < DOWN){
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
	
	private void calcDistance(){
		System.out.println("Math.tan(45): " + Math.tan(Math.toRadians(45)));
		System.out.println("Math.tan(45): " + Math.toDegrees(Math.atan(1)));
		System.out.println(Math.sin(Math.toRadians(30)));
		System.out.println(Math.toDegrees(Math.asin(0.5)));
		
		
		double x1 = 1, y1 = 1;
		double x2 = 20, y2 = 5;
		double x, y;
		double step = 5;
		double angle = Math.toDegrees(Math.atan((y2 - y1) / (x2 - x1)));
		x = step * 0.5 * Math.cos(Math.toRadians(angle)) + x1;
		y = step * 0.5 * Math.sin(Math.toRadians(angle)) + y1;
		//System.out.println("X:" + x + " Y: " + y);
		
		 //1 OldPoint{ p1:[1.1568793 384140514E7,3600191.8971091956], p2:[1.1568784122000001E7,3600198.698133333]}
		 //NewPoint{    p:[1.1568793 787160587E7,3600191.6011787634]
		x1 = 1.1568793384140514E7;
		y1 = 3600191.8971091956;
		x2 = 1.1568784122000001E7;
		y2 = 3600198.698133333;
		
		step = 1;
		angle = Math.atan((y2 - y1) / (x2 - x1));
		x = step * 0.5 * Math.cos(angle) + x1;
		y = step * 0.5 * Math.sin(angle) + y1;
		System.out.println("X: " + x + " Y: " + y);
		System.out.println(Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2)));;
		
		//7 OldPoint{ p1:[1.1568790 563000003E7,3600193.9686222216], p2:[1.15687937835E7,3600191.6038666666]}
		//newPoint{    p:[1.1568793 384140514E7,3600191.8971091956]
		
		x1 = 1.1568790563000003E7;
		y1 = 3600193.9686222216;
		x2 = 1.15687937835E7;
		y2 = 3600191.6038666666;
		
		step = 7;
		angle = Math.atan((y2 - y1) / (x2 - x1));
		x = step * 0.5 * Math.cos(angle) + x1;
		y = step * 0.5 * Math.sin(angle) + y1;
		System.out.println("X: " + x + " Y: " + y);
		System.out.println(Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2)));;
	}
}
