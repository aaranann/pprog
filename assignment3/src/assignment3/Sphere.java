package assignment3;

import assignment3.Ray;
import assignment3.Vector;

public class Sphere {
	// Implement class Sphere according to the exercise sheet!
	public final Vector center, color, emission;
	public final double radius;

	public Sphere(double radius, Vector center, Vector color, Vector emission) {
		this.radius = radius;
		this.center = center;
		this.color = color;
		this.emission= emission;

	}
	

	public Vector getColor() {

		return color;
	}

	public double intersect(Ray r) {
		Vector v = r.start.minus(center).smult(-1);
		double b= v.dot(r.direction);
		double det = b*b - v.dot(v) + radius * radius;
		//double t, t1, t2;
		if (det < 0.0) {
			return Double.NaN;
		} else{
			det=Math.sqrt(det);
		}
			final double eps = 1e-4;
			if (b-det>eps){return b-det;}
			if (b+det>eps){return b+det;}
			return Double.NaN;
			
			/*
			t1 = b + det;
			t2 = b - det;
			if (t1 >= 0 && t2 >= 0) {
				t = Math.min(t1, t2);
			} else if (t1 >= 0 && t2 < 0) {
				t = t1;
			} else if (t1 < 0 && t2 >= 0) {
				t = t2;
			} else {
				return Double.NaN;

				
			}
			return t;
		*/	

		}

	}

