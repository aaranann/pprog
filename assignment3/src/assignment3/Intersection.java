package assignment3;

public class Intersection {

	/*
	 * TODO Task a) Add necessary fields
	 */
	private Sphere sphere;
	private double t;

	public Intersection(Sphere sphere, double t) {
		/*
		 * TODO Task a) Initialize the class fields
		 */
		this.sphere = sphere;
		this.t = t;

	}
	
	/*
	 * TODO Task a) Implement getters for the two class fields
	 */
	public Sphere getSphere() {
		return sphere;
	}

	public double getT() {
		return t;
	}
}
