package zgl.test.nav;

public class TestNewPoint {
	public static void main(String[] args) {
		/*for(int i = 120; i < 130; i++){
			System.out.println(i + ": " + Math.toRadians(i) + ", "+ Math.sin(Math.toRadians(i)));
		}*/
		
		double[] d = {143.71185517754367,
				143.7108919457092 ,
				143.7099287113074 ,
				323.71185517754367, 
				323.710891932535  ,
				323.7108919457092 ,
				323.7099287113074 ,};
		for(int i = 0; i < d.length; i++){
			System.out.println(d[i] + ": " + Math.toRadians(d[i]) + ", "+ Math.sin(Math.toRadians(d[i])));
		}
		for(int i = 0; i < d.length; i++){
			System.out.println((int)d[i] + ": " + Math.toRadians((int)d[i]) + ", "+ Math.sin(Math.toRadians((int)d[i])));
		}
		
		/*System.out.println(Math.tan(Math.toRadians(60)));
		System.out.println(Math.toDegrees(Math.atan((1))));
		
		int step = 2;
		point(x1, y1, x2, y2, step);
		
		point(1d,1d, 5d,5d, 3);
		point(0,0, 2d,3.464d, 2);*/
		/*point(0, 0, -1d, 1.732d, 1);
		point1(0, 0, -1d, 1.732d, 1);
		System.out.println("---------------------------");
		point(0, 0, 1d, 1.732d, 1);
		point1(0, 0, 1d, 1.732d, 1);*/
		
		/*System.out.println(Math.cos(Math.toRadians(120)));
		System.out.println("---------------------------");*/
		/*point2(0, 0, 1d, 1.732d, 1);
		point2(0, 0, 1d, -1.732d, 1);
		point2(0, 0, -1d, 1.732d, 1);
		point2(0, 0, -1d, -1.732d, 1);*/
		
		/*System.out.println(getAngle(0, 0, 1, -1.732));
		System.out.println(getAngle(0, 0, 1, 1.732));*/
		getAngle2(1, 2, 2, 2);
	}
	static double x1 = 1.15687667314E7;
	static double y1 = 3600211.4678;
	
	static double x2 = 1.15687590022E7;
	static double y2 = 3600217.1432;
	
	public static void point(double x1, double y1, double x2, double y2, int step){
		double angle = Math.atan((y2 - y1) / (x2 - x1));
		double y = y1 + step * Math.sin(angle);
		double x = x1 + step * Math.cos(angle);
		System.out.println(x + ", " + y);
	}
	
	public static void point1(double x1, double y1, double x2, double y2, int step){
		double angle = Math.atan((y2 - y1) / (x2 - x1));
		angle = Math.asin((y2 - y1) / Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2)));
		angle = Math.asin((x2 - x1) / Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2)));
		System.out.println(Math.toDegrees(angle));
		double y = y1 + step * Math.sin(angle);
		double x = x1 + step * Math.cos(angle);
		System.out.println(x + ", " + y);
	}
	
	public static void point2(double x1, double y1, double x2, double y2, int step){
		double angle = getAngle2(x1, y1, x2, y2);
		System.out.println("角度： " + angle);
		angle = Math.toRadians(angle);
		double y = y1 + step * Math.sin(angle);
		double x = x1 + step * Math.cos(angle);
		System.out.println(x + ", " + y);
	}
	
	public static int getAngle1(double x0, double y0, double x1, double y1) {
		//int angle = (int) Math.toDegrees(Math.atan((x1 - x0) / (y1 - y0)));
		int angle = (int) Math.toDegrees(Math.atan((y1 - y0) / (x1 - x0)));
		System.out.println(angle);
		if (x1 > x0 && y1 > y0) {
			return angle;
		}
		if (x1 > x0 && y1 < y0) {
			return angle + 360;
		}
		if (x1 < x0 && y1 < y0) {
			return angle + 180;
		}
		if (x1 < x0 && y1 > y0) {
			return angle + 180;
		}
		return (int) angle;
	}
	
	public static double getAngle2(double x0, double y0, double x1, double y1) {
		//int angle = (int) Math.toDegrees(Math.atan((x1 - x0) / (y1 - y0)));
		double angle = Math.toDegrees(Math.atan((y1 - y0) / (x1 - x0)));
		//System.out.println(angle);
		if (x1 > x0 && y1 > y0) {
			//return angle;
		}
		if (x1 > x0 && y1 < y0) {
			angle = angle + 360;
		}
		if (x1 < x0 && y1 < y0) {
			angle = angle + 180;
		}
		if (x1 < x0 && y1 > y0) {
			angle = angle + 180;
		}
		System.out.println(angle);
		return angle;
	}
	
}
