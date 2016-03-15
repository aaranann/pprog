package assignment3;

import assignment3.Vector;

public class Vector {

	public static final Vector VEC_ZERO = new Vector(0, 0, 0);
	private final double x, y, z;

	public Vector(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
    
	// TODO: implement these vector functions!
	public double getX() {
		
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
	
	public Vector cross(Vector o) {
		//𝑎2𝑏3 − 𝑎3 𝑏2 𝑒1 + 𝑎3𝑏1 − 𝑎1𝑏3 𝑒2 + 𝑎1𝑏2 − 𝑎2𝑏1 𝑒3
		Vector c = new Vector ((y*o.z - z*o.y) ,(z*o.x - x*o.z) , (x*o.y - y*o.x));
		
		return c;
	}

	public double dot(Vector r) {
		double d = x*r.x+y*r.y+z*r.z;
		return d;
	}

	public Vector minus(Vector o) {
		Vector m = new Vector (x-o.x, y-o.y,z-o.z);
		return m;
	}

	public Vector mul(Vector o) {
		Vector n= new Vector (x*o.x, y*o.y, z*o.z);
		return n;
	}

	public double norm() {
		double b= Math.sqrt(x*x+y*y+z*z);
		return b;
	}

	public Vector normalize() {
		Vector v= new Vector (x/this.norm(),y/this.norm(), z/this.norm());
		return v;
	}

	public Vector plus(Vector r) {
		Vector p= new Vector (x + r.x,y+r.y,z+r.z);
		return p;
	}

	public Vector smult(double f) {
		Vector s = new Vector (f*x,f*y,f*z);
		return s;
	}
	

	// Some helper function to produce a color from a Vector
	// You don't have to change those!
	private static double clamp(double value, double min, double max) {
		double d = value;
		if (d < min) {
			d = min;
		} else if (d > max) {
			d = max;
		}
		return d;
	}
	
	public int toRGB() {
		return ((int) (Math.pow(clamp(x, 0, 1), 1 / 2.2) * 255. + .5) << 16)
				| ((int) (Math.pow(clamp(y, 0, 1), 1 / 2.2) * 255. + .5) << 8)
				| ((int) (Math.pow(clamp(z, 0, 1), 1 / 2.2) * 255. + .5));
	}

	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

}
