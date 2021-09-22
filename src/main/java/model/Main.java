package model;

import model.entities.Player;
import model.entities.Puckemon;

import javax.swing.*;
import java.awt.*;

public class Main extends Canvas implements Runnable {

	public static final int WIDTH = 1080, HEIGHT = WIDTH /(16*9);
	private Thread thread;
	private boolean running = false;

	public Main(){
		new Window(WIDTH, HEIGHT, "Puckemon", this);
	}

	public static void main(String args[]){

	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// NEED A RUN METHOD
	}
}