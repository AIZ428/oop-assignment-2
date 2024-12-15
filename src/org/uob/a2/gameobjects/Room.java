package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents a room in the game, which is a type of {@code GameObject}.
 * 
 * <p>
 * Rooms can have items, equipment, features, and exits. They also manage navigation
 * and interactions within the game world.
 * </p>
 */
public class Room extends GameObject {

    private ArrayList<Equipment> equipment;
    private ArrayList<Item> items;
    private ArrayList<Feature> features;
    private ArrayList<Exit> exits;
    private String id;


    /**
     * Returns a string representation of the room, including its contents and description.
     *
     * @return a string describing the room
     */

     

    public Room(String id, String name, String description, boolean hidden) {

        super(id, name, description, hidden);
        this.items = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.features = new ArrayList<>();
        this.exits = new ArrayList<>();
    }

    public Room() {
        this.id = "";
        this.name = "";
        this.description = "";
        this.items = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.features = new ArrayList<>();
        this.exits = new ArrayList<>();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String getDescription() {
        return description;
    }

    public ArrayList<Exit> getExits(){
        return exits;
    }

    public Exit getExitByName(String name){
        for (Exit e : this.exits) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public Exit getExitById(String id){
        for (Exit e : this.exits) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public Feature getFeatureById(String id){
        for (Feature f : this.features) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }


    public String getExitDirections() {
        StringBuilder directions = new StringBuilder();
        for (Exit e : this.exits) {
            if (!e.getHidden()) {
                directions.append(e.getName()).append(" ");
            }
        }
        return directions.toString().trim();
    }
    

    public Item getItemById(String id){
        for (Item i : this.items) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }


    public void addExit(Exit exit) {
        exits.add(exit);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItem(String id){
        for (Item i : this.items) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public Item getItemByName(String name){
        for (Item i : this.items) {
            if (i.getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return null;
    }

    public Feature getFeatureByName(String name){
        for (Feature f : this.features) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        return null;
    }

    public ArrayList<Equipment> getEquipments() {
        return equipment;
    }

    public Equipment getEquipmentByName(String name){
        for (Equipment e : this.equipment) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }


    public Equipment getEquipment(String id){
        for (Equipment e : this.equipment) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public Exit getExit(String id){
        for (Exit e : this.exits) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public void addEquipment(Equipment equipment) {
        this.equipment.add(equipment);
    }

    public Feature getFeature(String id){
        for (Feature f : this.features) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    public Container getContainer(String id){
        for (Feature f : this.features) {
            if (f.getId().equals(id)) {
                return (Container) f;
            }
        }
        return null;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public ArrayList<Feature> getFeatures(){
        return features;
    }

    public void removeEquipment(Equipment equipment){
        this.equipment.remove(equipment);
    }

    public ArrayList<GameObject> getAll(){
        ArrayList<GameObject> all = new ArrayList<>();
        all.addAll(this.items);
        all.addAll(this.equipment);
        all.addAll(this.features);
        all.addAll(this.exits);
        return all;
    }

    public void addFeature(Feature feature) {
        this.features.add(feature);
    }

    public boolean hasItem(String itemName){
        for (Item i : this.items) {
            if (i.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }


    public boolean hasEquipment(String name){
        for (Equipment e : this.equipment) {
            if (e.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String out = "[" + id +"] Room: " + name + "\nDescription: " + description + "\nIn the room there is: ";
        for (Item i : this.items) {
            out += i + "\n";
        }
        for (Equipment e : this.equipment) {
            out += e + "\n";
        }
        for (Feature f : this.features) {
            out += f + "\n";
        }
        for (Exit e : this.exits) {
            out += e + "\n";
        }
        return out + "\n";
    }
}
