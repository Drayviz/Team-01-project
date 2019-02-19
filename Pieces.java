public class Pieces {
    private ArrayList<Entity> humanPieces = new ArrayList<Entity>;
    private ArrayList<Entity> aiPieces = new ArrayList<Entity>;

    public ArrayList<Entity> getHumanPieces() {
        ArrayList<Entity> decoy = new ArrayList<Entity>();
        for (Entity h:humanPieces) {
            decoy.add(new Entity(h));
        }
        return decoy;
    }
    
    public ArrayList<Entity> getAIPieces() {
        ArrayList<Entity> decoy = new ArrayList<Entity>();
        for (Entity e:aiPieces) {
            decoy.add(new Entity(e));
        }
        return decoy;
    }   

    public void addHumanPieces(Entity h){
        humanPieces.add(new Entity(h));
    }

    public void addHumanPieces(Entity e){
        aiPieces.add(new Entity(e));
    }   
}