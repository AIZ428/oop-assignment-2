package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;


public class Combine extends Command {

    private String item1; 
    private String item2;
    
    // initialize the items to combine
    public Combine(String item1, String item2){ 
        this.item1 = item1;
        this.item2 = item2;
    }

    public String execute(GameState gameState) {

        // check if the player has the items to combine
        if(gameState.getPlayer().hasItem(item1) && gameState.getPlayer().hasItem(item2) ){

            // get the items to combine
            Item item1 = gameState.getPlayer().getItem(this.item1);
            Item item2 = gameState.getPlayer().getItem(this.item2);

            // check if the specific items can be combined in whatever order
            if(this.item1.equals("rope") && this.item2.equals("anchor") || this.item1.equals("anchor") && this.item2.equals("rope")){
                
                // if true basically removes the original items from inventory
                gameState.getPlayer().getInventory().remove(item2);
                gameState.getPlayer().getInventory().remove(item1);
                // new equipment object with its useinfo and then added to the players inventory
                UseInformation resultingEquipmentUseInfo = new UseInformation(false, "open", "mb1", "g1", "You use the rope with anchor to test each pressure plate eventually tapping into right one and unlocking the metal box");
                Equipment resultingEquipment = new Equipment("ancrp1", "anchoredrope", "A rope with an anchor can be used to avoid traps/pressure plates", false, resultingEquipmentUseInfo);
                gameState.getPlayer().addEquipment(resultingEquipment);
                System.out.println("You combined " + this.item1 + " and " + this.item2 + " to get " + resultingEquipment.getName());
                gameState.addPoints(5); // add points for combining items
            } 

            else if(this.item1.equals("fragment") && this.item2.equals("shard") || this.item1.equals("shard") && this.item2.equals("fragment")){
                gameState.getPlayer().getInventory().remove(item2);
                gameState.getPlayer().getInventory().remove(item1);
                UseInformation resultingEquipmentUseInfo = new UseInformation(false, "reveal", "ld1", "e12", "You use the steelkey on the locked door and it opens an exit with a creaking sound");
                Equipment resultingEquipment = new Equipment("sk1", "steelkey", "A special and sturdy steel key", false, resultingEquipmentUseInfo);
                gameState.getPlayer().addEquipment(resultingEquipment);
                System.out.println("You combined " + this.item1 + " and " + this.item2 + " to get " + resultingEquipment.getName());
                gameState.addPoints(5);

            }  else if(this.item1.equals("blade") && this.item2.equals("handle") || this.item1.equals("handle") && this.item2.equals("blade")){
                gameState.getPlayer().getInventory().remove(item2);
                gameState.getPlayer().getInventory().remove(item1);
                UseInformation resultingEquipmentUseInfo = new UseInformation(false, "open", "m1", "b1", "You use the dagger to stab and kill the monster finally reaching to the barrel he was defending");
                Equipment resultingEquipment = new Equipment("d1", "dagger", "A rusty dagger", false, resultingEquipmentUseInfo);
                gameState.getPlayer().addEquipment(resultingEquipment);
                System.out.println("You combined " + this.item1 + " and " + this.item2 + " to get " + resultingEquipment.getName());
                gameState.addPoints(5);
            }
            
            // if the items can't be combined
            else {
                System.out.println("You can't combine " + this.item1 + " and " + this.item2);
            }

        }
        return "";
    }
}



    
