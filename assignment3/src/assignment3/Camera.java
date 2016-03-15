package assignment3;

public class Camera {

	/* TODO Task c)
	 * Add necessary fields */
	private Ray eye;
	private Vector up;

	public Camera(Ray e, Vector u) {
		/* TODO Task c)
		 * Initialize the camera  with
		 * eye = e  and  up = u */
		this.eye= e;
		this.up=u;
		
	}
	
	/* TODO Task c)
	 * Implement the functions `getEye()` and `getUP()` 
	 * which return the corresponding value */
	public Ray getEye(){
		return eye;
	}
	
	public Vector getUp(){
		return up;
	}
}
