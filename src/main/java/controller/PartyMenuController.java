package controller;

import model.Model;
import run.VCHandler;
import view.screens.Screens;

/**
 * A class that listens to events from a Menu defined in IMenuController. In this implementation
 * the class handles the inputs from the Menu displaying the Puckemons in your party.
 * @author Rasmus Almryd
 * @author Lukas Jigberg
 */
public class PartyMenuController implements IMenuController{

    private final Model model;
    private final VCHandler handler;

    /**
     * Constructor of PartyMenuController
     * @param handler used to switch controller and/or screen.
     * @param model used to check and interact with the model.
     */
    public PartyMenuController(Model model, VCHandler handler){
        this.handler = handler;
        this.model = model;
    }

    /**
     * Switches to the selected Puckemon if it has HP left. Then, or if back button is selected,
     * switches to the combat screen.
     * @param index the index of the selected MenuItem in a Menu.
     */
    @Override
    public void onCursorEnter(int index) {
        if(index < model.getParty().size()){ //checks if MenuItem is in puckemon
            if(model.getPlayerPuckemon(index).getHealth() > 0 && index >= 1){ //if selected puckemon is not fainted, and it's not the active puckemon (active puckemon always index 0)
                if (model.getActivePlayerPuckemon().getHealth()>0){
                    model.switchPuckemon(index);
                    model.useSwitch(); //Only execute opponents attack if switch was used when active Puckemon had health
                }else{
                    model.switchPuckemon(index);
                }
                handler.setView(Screens.COMBAT);
                handler.setController(InputController.Controllers.COMBAT);
            }
        }else{ // Back button
            if(model.getActivePlayerPuckemon().getHealth()>0) {
                handler.setView(Screens.COMBAT);
                handler.setController(InputController.Controllers.COMBAT);
            }
        }
    }
}
