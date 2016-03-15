package assignment3;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {

		final int width = 1024;
		final int height = 768;

		final int samples = 6000;

		final Camera camera = new Camera(new Ray(new Vector(50, 52, 295.6),
				new Vector(0, -0.042612, -1)), new Vector(0, 1, 0));

		final Scene scene = new Scene(camera, width, height);

		// Left
		scene.addSphere(new Sphere(1e5, new Vector(1e5 + 1.0, 40.8, 81.6),
				new Vector(0.75, 0.25, 0.25), new Vector(0.0, 0.0, 0.0)));
		// Right
		scene.addSphere(new Sphere(1e5, new Vector(-1e5 + 99.0, 40.8, 81.6),
				new Vector(0.25, 0.25, 0.75), new Vector(0.0, 0.0, 0.0)));
		// Back
		scene.addSphere(new Sphere(1e5, new Vector(50, 40.8, 1e5), new Vector(0.75, 0.75, 0.75), new Vector(
						0.0, 0.0, 0.0)));
		// Front
		scene.addSphere(new Sphere(1e5, new Vector(50, 40.8, -1e5 + 600),
				new Vector(1.0, 1.0, 1.0), new Vector(0.0, 0.0, 0.0)));
		// Bottom
		scene.addSphere(new Sphere(1e5, new Vector(50, 1e5, 81.6), new Vector(0.75, 0.75, 0.75), new Vector(
						0.0, 0.0, 0.0)));
		// Top
		scene.addSphere(new Sphere(1e5, new Vector(50, -1e5 + 81.6, 81.6),
				new Vector(0.75, 0.75, 0.75), new Vector(0.0, 0.0, 0.0)));
		// Sphere 1
		scene.addSphere(new Sphere(16.5, new Vector(27.0, 16.5, 47.0),
				new Vector(0.999, 0.999, 0.999), new Vector(0.0, 0.0, 0.0)));
		// Sphere 2
		scene.addSphere(new Sphere(16.5, new Vector(73.0, 16.5, 78.0),
				new Vector(0.999, 0.999, 0.999), new Vector(0.0, 0.0, 0.0)));
		// Light-source
		scene.addSphere(new Sphere(600, new Vector(50, 681.6 - 0.27, 81.6),
				new Vector(0.0, 0.0, 0.0), new Vector(12.0, 12.0, 12.0)));

		final BufferedImage image = new BufferedImage(width, height,
				ColorSpace.TYPE_RGB);
		long nanos = System.nanoTime();

		scene.buildImage(image, samples);

		System.out.println("\nTime elapsed [ms]: "
				+ (System.nanoTime() - nanos) / 1_000_000);

		try {
			ImageIO.write(image, "PNG", new File("raytrace.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
