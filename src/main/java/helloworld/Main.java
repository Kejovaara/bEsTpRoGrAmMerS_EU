package helloworld;

import helloworld.entities.Player;
import helloworld.entities.Puckemon;

public class Main {

	public static void main(String args[]){
		Player player = new Player();
		player.addPuckemon(new Puckemon());

//		System.out.println("Player puckemon health: " + player.getPuckeBag().getPuckemons()[0].getHealth());
		System.out.println("Player puckemon health: ");

	}

}