package zgl.test.nav;

import java.io.Serializable;

public class PPoint implements Serializable{
	private static final long serialVersionUID = 1L;
	private double x, y;
	public PPoint next;
	
	public PPoint(){
		this.x = 0;
		this.y = 0;
	}

	public PPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public PPoint(PPoint p){
		this.next = p;
	}
	
	public PPoint getNext() {
		return next;
	}

	public void setNext(PPoint next) {
		this.next = next;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "[" + this.x + ", " + this.y + " NEXT: " + next + "]";
	}
	
	/*@Override
	public  PPoint clone() {
		PPoint p = null;
		try {
			p = (PPoint) super.clone();
			p.next = new PPoint(next.getX(), next.getY());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return p;
		
	}*/
}
