package demos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CloneUtils {
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(Object obj){
		T cloneObj = null;
		try{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.close();
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			cloneObj = (T) ois.readObject();
			ois.close();
			bais.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return cloneObj;
	}
}
