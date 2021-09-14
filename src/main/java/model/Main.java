package model;

import model.entities.Player;
import model.entities.Puckemon;

public class Main {

	public static void main(String args[]){
		Player player = new Player();
		player.addPuckemon(new Puckemon());

//		System.out.println("Player puckemon health: " + player.getPuckeBag().getPuckemons()[0].getHealth());
		System.out.println("Player puckemon health: ");

	}

}