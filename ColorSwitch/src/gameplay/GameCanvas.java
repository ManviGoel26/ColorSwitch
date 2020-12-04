package gameplay;
//package burj;

import java.util.ArrayList;
//import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameCanvas extends Application {
	public static void main(String[] args) {
		System.out.println("running1");
		launch(args);
		
	}

	public void start(Stage theStage) {
		System.out.println("running");
		theStage.setTitle("Colour Switch");

		Group root = new Group();
		Scene theScene = new Scene(root);
		theStage.setScene(theScene);
		Canvas canvas = new Canvas(1000, 700);
		root.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		ArrayList<Image> manyEarths = new ArrayList<Image>();
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 100; i++) {
				if (i / 10 == 0 || i / 10 == 4 || i / 10 == 8)
					manyEarths.add(new Image("/redc.png", 20, 20, false, false));

				else if (i / 10 == 1 || i / 10 == 5 || i / 10 == 9)
					manyEarths.add(new Image("/yellow.png", 20, 20, false, false));

				else if (i / 10 == 2 || i / 10 == 6)
					manyEarths.add(new Image("/bluec.png", 20, 20, false, false));

				else if (i / 10 == 3 || i / 10 == 7)
					manyEarths.add(new Image("/greenc.png", 20, 20, false, false));
			}
		}

		ArrayList<Image> manycircs = new ArrayList<Image>();
		for (int i = 0; i < 16; i++) {
			if (i == 0 || i == 1 || i == 2 || i == 3)
				manycircs.add(new Image("/redc.png", 20, 20, false, false));

			else if (i == 4 || i == 5 || i == 6 || i == 7)
				manycircs.add(new Image("/yellow.png", 20, 20, false, false));

			else if (i == 8 || i == 9 || i == 10 || i == 11)
				manycircs.add(new Image("/bluec.png", 20, 20, false, false));

			else if (i == 12 || i == 13 || i == 14 || i == 15)
				manycircs.add(new Image("/greenc.png", 20, 20, false, false));
		}
		ArrayList<Image> manycircs2 = new ArrayList<Image>();

		int i = 0;
		while (i < 32) {
			manycircs2.add(new Image("/redc.png", 20, 20, false, false));
			i++;
		}

		while (i < 64) {
			manycircs2.add(new Image("/yellow.png", 20, 20, false, false));
			i++;
		}
		while (i < 96) {
			manycircs2.add(new Image("/bluec.png", 20, 20, false, false));
			i++;
		}
		while (i < 128) {
			manycircs2.add(new Image("/greenc.png", 20, 20, false, false));
			i++;
		}

		
//		The user from user class, empty constructor.
		User user = new User(); 
//		Image user = new Image("/redc.png", 15, 15, false, false);

		
		
//		The colorBall from ColorBall class
		ColorBall b1 = new ColorBall();
		Image space = new Image("/space.png", 1000, 1000, false, false);
		Image revsign = new Image("/rev.png", 50, 50, false, false);
		Image psign = new Image("/favicon-32x32.png", 50, 50, false, false);
		Image star = new Image("/star.png", 50, 50, false, false);

		Image bluebar = new Image("/bluebar.png", 200, 15, false, false);
		Image redbar = new Image("/redbar.png", 200, 15, false, false);
		Image yellowbar = new Image("/yellowbar.png", 200, 15, false, false);
		Image greenbar = new Image("/greenbar.png", 200, 15, false, false);

		final long startNanoTime = System.nanoTime();
		

		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				double t = (currentNanoTime - startNanoTime) / 1000000000.0;

				double x = 200 + 300 * Math.cos(4 * t);
				double y = 100;
				double x2 = 200 + 300 * Math.sin(4 * t);

				double x3 = 400 + 100 * Math.cos(5 * t);
				double y3 = 400 + 100 * Math.sin(5 * t);

				// background image clears canvas
				gc.drawImage(space, 0, 0);
				gc.drawImage(revsign, 880, 20);
				gc.drawImage(psign, 940, 20);

				for (int i = 0; i < 100; i++) {
					gc.drawImage(manyEarths.get(i), -40 * i + 150 * t, 600 + 20 * t);
					gc.drawImage(manyEarths.get(i), 40 * i + 150 * t, 600 + 20 * t);
				}
				for (int i = 0; i < 200; i++) {

					gc.drawImage(manyEarths.get(i), -40 * i + 150 * t, -100 + 20 * t);
					gc.drawImage(manyEarths.get(i), 40 * i + 150 * t, -100 + 20 * t);
				}

				gc.drawImage(star, 500, 530 + 20 * t);
				gc.drawImage(star, 500, 50 + 20 * t);
				gc.drawImage(star, 480, 380 + 20 * t);
				gc.drawImage(b1.getColorBallImage(), 500, 20*t);


				// gc.drawImage( earth, x3, y3 );
				for (int i = 0; i < 16; i++) {
					gc.drawImage(manycircs.get(i), 400 + 100 * Math.cos(i * Math.PI / 8 + 3 * t),
							400 + 100 * Math.sin(i * Math.PI / 8 + 3 * t) + 20 * t);
				}
				for (int i = 0; i < 16; i++) {
					gc.drawImage(manycircs.get((i + 4) % 16), 600 + 100 * Math.sin(i * Math.PI / 8 + 3 * t),
							400 + 100 * Math.cos(i * Math.PI / 8 + 3 * t) + 20 * t);
				}
				for (int i = 0; i < 128; i++) {
					gc.drawImage(manycircs2.get(i), 500 + 100 * Math.cos(i * Math.PI / 64 + 3 * t),
							400 + 100 * Math.sin(i * Math.PI / 64 + 3 * t) + 20 * t - 700);
				}

				double yb = y + 20 * t;

				gc.drawImage(bluebar, x, yb);
				gc.drawImage(yellowbar, x2 + 200, yb + 40);
				gc.drawImage(redbar, x, yb + 80);
				gc.drawImage(greenbar, x2 + 200, yb + 120);
				
//				Adding the colorball;
				
				gc.drawImage(redbar, x, yb - 800);
				gc.drawImage(greenbar, x2 + 200, yb + 40 - 800);
				gc.drawImage(bluebar, x, yb + 80 - 800);
				gc.drawImage(yellowbar, x2 + 200, yb + 120 - 800);
				gc.drawImage(star, 500, yb - 650);

				gc.drawImage(user.getUserImage(), 500, 640);
//				user.changeColor(3);

				EventHandler<MouseEvent> handler = new EventHandler<>() {
					@Override
					public void handle(MouseEvent event) {
						if (event.equals(MouseEvent.MOUSE_CLICKED)) {
							System.out.println("poilo");

						}
						event.consume();
					}
				};

			}
		}.start();

		theStage.show();
	}

}
