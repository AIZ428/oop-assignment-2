package org.uob.a2.gameobjects;

public class Equipment extends GameObject implements Usable {
   
    /**
     * Returns a string representation of this equipment, including the attributes inherited from {@code GameObject}
     * and the associated use information.
     *
     * @return a string describing the equipment
     */
    


    protected UseInformation useInformation;
    
    public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation) {
        super(id, name, description, hidden);
        this.useInformation = useInformation;
        
    }

    public void setUseInformation(UseInformation useInformation) {
        this.useInformation = useInformation;
    }

    
    public UseInformation getUseInformation() {
        return useInformation;
    }

    public String use(GameObject target, GameState gameState) {
        if (useInformation.isUsed()) {
            return "This item has already been used.";
        }

        Room currentRoom = gameState.getMap().getCurrentRoom();

        // Check if the target is a container and unhide its items
        if (target instanceof Container) {
            Container container = (Container) target;
            for (Item item : currentRoom.getItems()) {
                item.setHidden(false);
            }
        }

        useInformation.setUsed(true);
        // Perform the action and update the game state
        return useInformation.getMessage();
    }
    @Override
    public String toString() {
        return super.toString() + ", useInformation=" + useInformation;
    }
}
