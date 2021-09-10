package helloworld;

import helloworld.entities.Player;

public class Main {

	public static void main(String args[]){
		Player player = new Player();

		System.out.println("Player puckemon health: " + player.getPuckeBag().getPuckemon().getHealth());

	}

}