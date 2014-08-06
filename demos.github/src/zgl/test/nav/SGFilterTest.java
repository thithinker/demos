package zgl.test.nav;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SGFilterTest {
	public static void main(String[] args) {
		/*float[] data = new float[] { 8916.81f, 8934.24f, 9027.06f, 9160.79f, 7509.14f };
		float[] leftPad = new float[] { 8915.06f, 8845.53f, 9064.17f, 8942.09f, 8780.87f };
		double[] coeffs = SGFilter.computeSGCoefficients(5, 4, 2);
		ContinuousPadder padder1 = new ContinuousPadder();
		SGFilter sgFilter = new SGFilter(5, 5);
		sgFilter.appendPreprocessor(padder1);
		float[] smooth = sgFilter.smooth(data, leftPad, new float[0], coeffs);
		MeanValuePadder padder2 = new MeanValuePadder(10, false, true);
		sgFilter.removePreprocessor(padder1);
		sgFilter.appendPreprocessor(padder2);
		smooth = sgFilter.smooth(data, leftPad, new float[0], coeffs);
		
		
		
		
		for(int i = 0; i < smooth.length; i++){
			System.out.println(smooth[i] + " ");
		}*/
		/*String ssString = "sadf，lfd";
		String[] strings = ssString.split(",|，");
		for(int i = 0; i < strings.length; i++){
			System.out.println(strings[i] + " ");
		}*/
		
		
		
		filter1();
		//new SGFilterTest().calc();
	}
	
	public static void filter1(){
		List<Double> list = new ArrayList<>();
		try{
			BufferedReader br = new BufferedReader(new FileReader("./smooth_ssid.data"));
			String line = null;
			double currData;
			while((line = br.readLine()) != null){
				currData = Double.parseDouble(line);
				list.add(currData);
			}
			br.close();
		}catch(NumberFormatException | IOException e){
			e.printStackTrace();
		}
		
		double[] input = new double[list.size()];
		for(int i = 0; i < list.size(); i++){
			input[i] = list.get(i);
		}
		
		double[] output = new double[list.size()];
		lowPass(input, output, 0.45);
		for(int i = 0; i < output.length;i++){
			System.out.println(String.format("%.3f", output[i]));
		}
	}
	
	private static double[] lowPass(double[] input, double[] output, final double ALPHA){
		if(output == null)
			return input;
		output[0] = input[0];
		for(int i = 1; i < input.length; i++){
			output[i] = output[i - 1] + ALPHA * (input[i] - output[i - 1]);
		}
		return output;
		
	}
	
	public void calcMatch(double[] origin, double[] m){
		final int THRESHOLD = 1500;
		double tsum = 0; 
		double sum = Double.MAX_VALUE;
		int begin = -1;
		int end = -1;
		for(int i = 0; i < origin.length; i++){
			if(Math.abs(origin[i] - m[0]) > THRESHOLD){
				continue;
			}else{
				if(i + m.length >= origin.length){
					continue;
				}
				for(int j = 0; j < m.length; j++){
					if(Math.abs(origin[i + j] - m[j]) > THRESHOLD){
						tsum = Double.MAX_VALUE;
						break;
					}
						
					tsum += Math.abs(origin[i + j] - m[j]);	
				}
				if(tsum < sum){
					begin = i;
					end = i + m.length - 1;
					sum = tsum;
				}
				System.out.println("tsum: "+tsum + " begin: " + i  + " end: " + (i + m.length - 1) + " sum " + sum);
				tsum = 0;
			}
		}
		System.out.println("begin: " + begin + " end: " + end + " sum: " + sum);
	}
	
	private double similarity(double[] orgin, int start, double[] m){
		double avg = 0;
		double[] tmp = new double[m.length];
		for(int i = 0; i < m.length; i++){
			tmp[i] = Math.abs(orgin[start + i] - m[i]);
		}
		for(int i = 0; i < m.length; i++){
			avg += tmp[i];
		}
		avg = avg / m.length;
		for(int i = 0; i < m.length; i++){
			tmp[i] = Math.abs(tmp[i] - avg);
		}
		avg = 0;
		for(int i = 0; i < m.length; i++){
			avg += tmp[i];
		}
		return avg;
	}
	
	public void calc(){
		List<Double> list = new ArrayList<>();
		try{
			BufferedReader br = new BufferedReader(new FileReader("./smooth_ssid.data"));
			String line = null;
			double currData;
			while((line = br.readLine()) != null){
				currData = Double.parseDouble(line);
				list.add(currData);
			}
			br.close();
		}catch(NumberFormatException | IOException e){
			e.printStackTrace();
		}
		
		double[] input = new double[list.size()];
		for(int i = 0; i < list.size(); i++){
			input[i] = list.get(i);
		}
		
		
		
		double[] d3_7 = {-82.87139,-81.48926,-80.41409,-83.28775,-78.83826};
		double[] d5_9 = {-80.41409,-83.28775,-78.83826,-79.63105,-75.79207};
		double[] d15_20 = {-69.1511,-65.4831,-61.17071,-58.21389,-56.54264,-50.80845};
		double[] d15_24 ={-72.87473,-69.1511,-65.4831,-61.17071,-58.21389,-56.54264,-50.80845,-51.39465,-57.35342,-60.38938};
		double[] o3_7 = {-80.08038,-78.96016,-78.80214,-75.50448,-75.27746};
		double[] o5_9 = {-78.80214,-75.50448,-75.27746,-72.5426,-75.40928};
		double[] o19_26 = {-51.10373,-49.13105,-49.50318,-55.77475,-55.93731,-62.33122,-66.07017,-67.26394};
		double[] o11_20 = {-72.39186,-70.04067,-69.14217,-69.30319,-65.66306,-62.90903,-62.41402,-57.32496,-51.10373,-49.13105};
		//calcMatch(input, d15_20);
		
		
		for(int i = 0; i < input.length - d3_7.length; i++){
			System.out.println(i + " " + similarity(input, i, d15_24));
		}
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		/*for(int i = 0; i < input.length - d3_7.length; i++){
			System.out.println(i + " " + similarity(input, i, d3_7));
		}*/
		
		calcMatch(input, d15_24);
		
	}
}
