/*package zgl.shapefile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;

import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.dbf.DbaseFileHeader;
import org.geotools.data.shapefile.dbf.DbaseFileReader;
import org.geotools.data.shapefile.files.ShpFiles;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;





import org.opengis.geometry.primitive.Point;

import com.vividsolutions.jts.geom.Geometry;


public class ReadShapefileWithGT {
	public static void main(String[] args){
		String pathString = "./buildPoint/buildPoint.shp";
		//readDBF(pathString);
		readSHP(pathString);
	}
	
	private static void readDBF(String path){
		DbaseFileReader reader = null;
		try{
			reader = new DbaseFileReader(new ShpFiles(path), false, Charset.forName("GBK"));
			DbaseFileHeader header = reader.getHeader();
			int numFields = header.getNumFields();
			while(reader.hasNext()){
				try{
					Object[] entry = reader.readEntry();
					for(int i = 0; i < numFields; i++){
						String title = header.getFieldName(i);
						Object value = entry[i];
						System.out.print(title + "=" + value + " ");
					}
					System.out.println();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(reader != null){
				try{
					reader.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	*//**
	 * ��ȡShapefile�ļ�
	 * @param path
	 *//*
	private static void readSHP(String path){
		ShapefileDataStore shpDataStore = null;
		try{
			shpDataStore = new ShapefileDataStore(new File(path).toURI().toURL());
			shpDataStore.setCharset(Charset.forName("GBK"));
			String typeName = shpDataStore.getTypeNames()[0];
			FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = null;
			featureSource = (FeatureSource<SimpleFeatureType, SimpleFeature>)shpDataStore.getFeatureSource(typeName);
			FeatureCollection<SimpleFeatureType, SimpleFeature> result = featureSource.getFeatures();
			System.out.println(result.size());
			FeatureIterator<SimpleFeature> iterator = result.features();
			while(iterator.hasNext()){
				SimpleFeature feature = iterator.next();
				Collection<Property> p = feature.getProperties();
				Iterator<Property> it = p.iterator();
				while(it.hasNext()){
					Property pro = it.next();
					if(pro.getValue() instanceof Point){
						DirectPosition dp = ((Point)pro.getValue()).getRepresentativePoint();
						System.out.println("PointX: " + dp.getOrdinate(0));
					}
				}
			}
		}
	}
	
	private static void readSHP(String path){
		 File file = new File(path);  
	        ShapefileDataStore shpDataStore = null;  
	        try{  
	            shpDataStore = new ShapefileDataStore(file.toURL());
	            shpDataStore.setCharset(Charset.forName("GBK"));
	            String typeName = shpDataStore.getTypeNames()[0];  
	            FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = null;  
	            featureSource = (FeatureSource<SimpleFeatureType, SimpleFeature>)shpDataStore.getFeatureSource(typeName);  
	            FeatureCollection<SimpleFeatureType, SimpleFeature> result = featureSource.getFeatures();  
	              
	            FeatureIterator<SimpleFeature> itertor = result.features();  
	            while(itertor.hasNext()){  
	                SimpleFeature feature = itertor.next();  
	                com.vividsolutions.jts.geom.Point p = ((Geometry)feature.getDefaultGeometry()).getCentroid();
	                System.out.println(feature.getAttribute("cname"));
	                System.out.println(p.getX() + " " + p.getY() + "\n\n");
	                Geometry geometry = (Geometry)feature.getDefaultGeometry();  
	                Collection<Property> p = feature.getProperties();
	                Iterator<Property> it = p.iterator();
	                while(it.hasNext()){
	                	Property pro = it.next();
	                	System.out.println(pro);
	                }
	                
	               // System.out.println(geometry.getCentroid().getX() + geometry.toText() + "\n");  
	            }  
	            itertor.close();  
	        }  
	        catch(MalformedURLException e){  
	            e.printStackTrace();  
	        }  
	        catch(IOException e){  
	            e.printStackTrace();  
	        }  
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
*/