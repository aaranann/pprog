package assignment3;

import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

public class Scene {

	/* TODO Task b)
	 * Add necessary fields */
	private Camera cam;
	private int imageWidth,imageHeight;	
	private Set<Sphere> spheres;
	private double h,wi;
	private Vector rf;
	

	/**
	 * Initialize the scene
	 */
	public Scene(Camera camera, int imageWidth, int imageHeight) {
		/* TODO Task b)
		 * Initialize the class fields */
		cam = camera;
		this.imageWidth= imageWidth;
		this.imageHeight= imageHeight;		
		spheres = new HashSet<Sphere>();
		
	}

	/**
	 * Add a new sphere to the scene
	 */
	public void addSphere(Sphere sphere) {
		/* TODO Task b)
		 * Add the provided sphere to the spheres set */
		spheres.add(sphere);
		
		
	}

	/**
	 * Find the closest {@link Sphere} that intersects with the given ray.
	 * 
	 * @param ray
	 *            The ray to shoot at shapes.
	 * @return An {@link Intersection} object to store shape and distance or
	 *         <code>null</code> if no intersection has been found.
	 */
	public Intersection intersect(Ray ray) {
		double t0 = Double.MAX_VALUE;
		Sphere s0 = null;
		
		/* TODO Task b)
		 * Implement the code for the ray intersection.
		 * 
		 * Check if the ray intersects with any of the spheres.
		 * If so, return a new Intersection object with that sphere.
		 */
		for (Sphere z: spheres){
			if ((z.intersect(ray)<t0)&&(z.intersect(ray)!=Double.NaN)){
				s0=z;
				t0=z.intersect(ray);			
				
			}
		}
		if (t0==Double.MAX_VALUE){
			return null;
		}
		return new Intersection(s0,t0);
	}


	/**
	 * Get a ray from the camera through pixels identified by <code>i</code> and
	 * <code>j</code>.
	 * 
	 * @param i
	 *            The pixel (0-based) on the width of the picture.
	 * @param j
	 *            The pixel (0-based) on the height of the picture.
	 * @return A new ray from the camera through the pixel.
	 */
	public Ray getCameraRay(int i, int j) {
		double u0 = -1;
		double v0 = -1;
		double u1 = 1;
		double v1 = 1;
		double s0 = 2;
		
		
		/* TODO Task c)
		 * Implement this function.
		 * Calculate and return the ray as explained in the exercise.
		 */
		
		Vector w = (cam.getEye().direction).smult(-1).normalize();
		Vector u = cam.getUp().cross(w).normalize();
		Vector v = w.cross(u).normalize();
		Vector a = u.smult(u1-u0);
		Vector b = v.smult(v1-v0);
		Vector e = cam.getEye().start;
		Vector c = e.plus(u.smult(u0).plus(v.smult(v0)).minus(w.smult(s0)));
		wi= 1.0*i/imageWidth;
		h= 1.0*j/imageHeight;
		Vector s = c.plus(a.smult(wi).plus(b.smult(h)));
		
		Ray r = new Ray(e,(s.minus(e)).normalize());
		
		
		return r;
	}

	/**
	 * Compute the light at some ray. Performs intersection tests and recursive
	 * sampling of up to {@link #MAX_DEPTH} ({@value #MAX_DEPTH}) levels.
	 * 
	 * @param ray
	 *            The ray to compute the light for.
	 * @param depth
	 *            The current recursion depth.
	 * @return The color encoded as vector.
	 */
	public Vector trace(Ray ray, int depth) {
		/* TODO Task d)
		 */
		Vector C= new Vector (0,0,0);
		Sphere S;
		double t;
		S= intersect(ray).getSphere();
		t= intersect(ray).getT();
		
		Random random= new Random();
		double r1= 2*Math.PI*random.nextDouble();
		double r2= random.nextDouble();
		double x= Math.cos(r1)*Math.sin(r2);
		double y= Math.sin(r1)*Math.sin(r2);
		double z= Math.cos(r2);
		Vector h= ray.start.plus(ray.direction.smult(t));
		Vector n= h.minus(S.center).normalize();
		
		//Vector w = (cam.getEye().direction).smult(-1).normalize();
		//Vector u = cam.getUp().cross(w).normalize();
		Vector w = n.normalize();
		Vector u = new Vector(1,0,0).cross(w).normalize();
		Vector v = w.cross(u).normalize();
		
		rf= (u.smult(x).plus(v.smult(y)).plus(w.smult(z))).normalize();
		double norminside= n.dot(ray.direction);
		
		if (S != null){
			if (depth>5){
				return S.emission;
			}
			
			
			if (norminside>=0){
			Ray rR=new Ray(h, rf.smult(-1));
			Vector rC= trace(rR,depth+1);
			C=S.emission.plus((S.color).mul(rC));
			}
			else {
				Ray rR= new Ray(h,rf);
			
			Vector rC= trace(rR,depth+1);
			C=S.emission.plus((S.color).mul(rC));}
		}
		
		return C;
	}

	public void buildImage(final BufferedImage image, final int samples) {
		/* TODO Task d)
		 */
		
		
		for (int i = 0; i < imageWidth; i++) {
			for (int  j=0; j< imageHeight; j++) {
				Vector current= new Vector (0,0,0);
				for (int k=0; k<samples;k++){
					current= current.plus(trace(getCameraRay(i,j),3));
				}
				current= current.smult(1.0/samples);
				image.setRGB(i,imageHeight-1-j, current.toRGB());
					}
							
					
				}
			
				
			}
		
	}

