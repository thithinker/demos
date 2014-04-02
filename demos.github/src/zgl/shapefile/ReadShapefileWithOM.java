/*package zgl.shapefile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bbn.openmap.dataAccess.shape.DbfFile;
import com.bbn.openmap.dataAccess.shape.DbfTableModel;
import com.bbn.openmap.dataAccess.shape.EsriGraphicList;
import com.bbn.openmap.io.BinaryFile;
import com.bbn.openmap.io.FormatException;
import com.bbn.openmap.layer.shape.ESRIPointRecord;
import com.bbn.openmap.layer.shape.ShapeFile;
import com.bbn.openmap.plugin.esri.EsriPlugIn;

public class ReadShapefileWithOM {
	public static void main(String[] args) {
		String path = "./buildPoint/buildPoint.shp";
		readSHP(path);
		
		//String path2 = "./buildPoint/buildPoint.dbf";
		//readAttr2(path2);
		
		//readAttr2(path2+"dbf");
		
		String pathShp = "./buildPoint/buildPoint";
		Map<String, Double[]> positions = readPointShp(pathShp, "��¥", 4);
		Set<String> names = positions.keySet();
		for(String n : names){
			System.out.println(n + " " + positions.get(n)[0] + " " +  positions.get(n)[1]);
		}
		
	}
	
	private static void readSHP(String path){
		try{
			File file = new File(path);
			ShapeFile shp = new ShapeFile(file);
			ESRIPointRecord e = (ESRIPointRecord)shp.getNextRecord();
			
			while(e != null){
				System.out.println(e.getX() + " " + e.getY());
				e = (ESRIPointRecord) shp.getNextRecord();
			}
			shp.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private static void readAttr(String path){
		File dbf = new File(path + "dbf");
		File shape = new File(path + "shp");
		File shx = new File(path + "shx");
		try{
			URL dbfUrl = dbf.toURI().toURL();
			EsriPlugIn epi = new EsriPlugIn("name", dbfUrl, shape.toURI().toURL(), shx.toURI().toURL());
			EsriGraphicList egList = epi.getEsriGraphicList();
			Map<Object, Object> attrs = egList.getAttributes();
			
			System.out.println(attrs.size());
			
			Set<Object> keys = attrs.keySet();
			for(Object k : keys){
				System.out.println(attrs.get(k));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	private static void readAttr2(String path){
		try {
			BinaryFile bf = new BinaryFile(new File(path));
			DbfFile dbfFile  = new DbfFile(bf);
			//System.out.println(dbfFile.getRowCount());
			
			System.out.println(dbfFile.getHeaderLength() + " " + dbfFile.getColumnCount() +" " + dbfFile.getRowCount());
			
			DbfTableModel tableModel = DbfFile.getDbfTableModel(path);
			
			
			//System.out.println(tableModel.getRowCount());
			
			List<Object> list = dbfFile.getRecordData(85);
			System.out.println(list.get(4));
			for(Object b : list){
				System.out.println(b);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	*//**
	 * �ӵ�shapefile�ļ��ж�ȡ��������
	 * @param path: ��ȡ�ļ���·������"./buildPoint/buildPoint"��buildPointΪ�ļ���
	 * @param name: ��Ҫ���ҵ�λ����
	 * @param index: name�ڸ�shapefile�ļ��е�������
	 * @return 
	 *//*
	private static Map<String, Double[]> readPointShp(String path, String name, int index){
		//List<Map<String, Double[]>> list = new ArrayList<Map<String,Double[]>>();
		Map<String, Double[]> map = new HashMap<String, Double[]>();
		
		File shpFile = new File(path + ".shp");
		File dbfFile = new File(path + ".dbf");
		ShapeFile shp = null;
		DbfFile dbf = null;
		try{
			BinaryFile bf = new BinaryFile(dbfFile);
			dbf = new DbfFile(bf);		
			shp = new ShapeFile(shpFile);
			for(int i = 0; i < dbf.getRowCount(); i++){				
				ESRIPointRecord e = (ESRIPointRecord)shp.getNextRecord();
				String position = dbf.getRecordData(i).get(index).toString();
				if(position.contains(name)){
					map.put(position, new Double[]{e.getX(), e.getY()});
				}
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(shp != null)
					shp.close();
				if(dbf != null)
					dbf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				
			}
		}
		return map;
	}
	
	
	public class SearchPosition{
		
	}
	
}
*/