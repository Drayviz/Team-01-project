import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class Pieces 
{
    /** Instance variables for the class that are set to default values and all array lists */
    private ArrayList<Entity> humanPieces = new ArrayList<Entity>();
    private ArrayList<Entity> aiPieces = new ArrayList<Entity>();
    private ArrayList<Entity> entityParty = new ArrayList<Entity>();
    private ArrayList<Entity> playerParty = new ArrayList<Entity>();
    private ArrayList<Entity> masterlist = new ArrayList<Entity>();
    private Random r = new Random();

    Pieces()
    {
    
    }
    /** Copy Constructor.
    *@param copy takes piece object as an argument and creates a copy of i
    */
    Pieces(Pieces copy)
    {
        humanPieces = copy.getHumanPieces();
        aiPieces = copy.getAIPieces();
        entityParty = copy.getAIParty();
        playerParty = copy.getPlayerParty();
        masterlist = copy.getMasterList();

    }
    /** Getter
    * @ return decoy as array list holding all human pieces
    */
    public ArrayList<Entity> getHumanPieces() {
        ArrayList<Entity> decoy = new ArrayList<Entity>();
        for (Entity h:humanPieces) {
            decoy.add(new Entity(h));
        }
        return decoy;
    }
    /** Getter
    * @ return decoy as array list holding all AI pieces
    */
    public ArrayList<Entity> getAIPieces() {
        ArrayList<Entity> decoy = new ArrayList<Entity>();
        for (Entity e:aiPieces) {
            decoy.add(new Entity(e));
        }
        return decoy;
    }
    /** Getter
    * @ return entityParty as array list holding all AI party members
    */
    public ArrayList<Entity> getAIParty() {
       
        return entityParty;
    }
    /** Getter
    * @ return playerParty as array list holding all human members
    */
    public ArrayList<Entity> getPlayerParty() {
       
        return playerParty;
    }
    /** Getter
    * @ return decoy as array list holding all pieces
    */
    public ArrayList<Entity> getMasterList() {
    
        return masterlist;
    }
    /** Method to create names of pieces
    * @ return string containing random first and last name
    */
    public String namegenerator()
    {
        String First[] = {"adamant", "adroit", "amatory", "animistic", "antic", "arcadian", "baleful", "bellicose", "bilious", "boorish", "calamitous", "caustic", "cerulean", "comely", "concomitant", "contumacious", "corpulent", "crapulous", "defamatory", "didactic", "dilatory", "dowdy", "efficacious", "effulgent", "egregious", "endemic", "equanimous", "execrable", "fastidious", "feckless", "fecund", "friable", "fulsome", "garrulous", "guileless", "gustatory", "heuristic", "histrionic", "hubristic", "incendiary", "insidious", "insolent", "intransigent", "inveterate", "invidious", "irksome", "jejune", "jocular", "judicious", "lachrymose", "limpid", "loquacious", "luminous", "mannered", "mendacious", "meretricious", "minatory", "mordant", "munificent", "nefarious", "noxious", "obtuse", "parsimonious", "pendulous", "pernicious", "pervasive", "petulant", "platitudinous", "precipitate", "propitious", "puckish", "querulous", "quiescent", "rebarbative", "recalcitant", "redolent", "rhadamanthine", "risible", "ruminative", "sagacious", "salubrious", "sartorial", "sclerotic", "serpentine", "spasmodic", "strident", "taciturn", "tenacious", "tremulous", "trenchant", "turbulent", "turgid", "ubiquitous", "uxorious", "verdant", "voluble", "voracious", "wheedling", "withering", "zealous"};
        String Last[] = {"ninja", "chair", "pancake", "statue", "unicorn", "rainbows", "laser", "senor", "bunny", "captain", "nibblets", "cupcake", "carrot", "gnomes", "glitter", "potato", "salad", "toejam", "curtains", "beets", "toilet", "exorcism", "stick figures", "mermaid eggs", "sea barnacles", "dragons", "jellybeans", "snakes", "dolls", "bushes", "cookies", "apples", "ice cream", "ukulele", "kazoo", "banjo", "opera singer", "circus", "trampoline", "carousel", "carnival", "locomotive", "hot air balloon", "praying mantis", "animator", "artisan", "artist", "colorist", "inker", "coppersmith", "director", "designer", "flatter", "stylist", "leadman", "limner", "make-up artist", "model", "musician", "penciller", "producer", "scenographer", "set decorator", "silversmith", "teacher", "auto mechanic", "beader", "bobbin boy", "clerk of the chapel", "filling station attendant", "foreman", "maintenance engineering", "mechanic", "miller", "moldmaker", "panel beater", "patternmaker", "plant operator", "plumber", "sawfiler", "shop foreman", "soaper", "stationary engineer", "wheelwright", "woodworkers"};

        return First[r.nextInt(First.length)] + " " + Last[r.nextInt(Last.length)];

    }
    /** Method to create different type of human piece
    * @param prefab takes integer that distinguishes what type of character to add
    * creates new entity and then adds to humanPieces list
    */
    public void addHumanPieces(int prefab)
    {
        if(prefab == 1)
        {   
            //artillery1
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 0, 1, 2, 2,prefab);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 2)
        {
            //ranger1
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 0, 1, 2, 2,prefab);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 3)
        {
            //titan1
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 0, 1, 2, 2,prefab);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 4)
        {
            //artillery2
            Entity hPiece = new Entity(namegenerator(),2, 0, 4, 0, 1, 2, 2,prefab);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 5)
        {
            //ranger2
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 0, 1, 2, 2,prefab);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 6)
        {
            //titan2
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 1, 1, 2, 2,prefab);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 7)
        {
            //artillery3
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 1, 1, 2, 2,prefab);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 8)
        {
            //ranger3
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 1, 1, 2, 2,prefab);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 9)
        {
            //titan3
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 1, 1, 2, 2,prefab);
            humanPieces.add(new Entity(hPiece));
        }
    }
    
    /** Method to create different type ofAI piece
    * @param prefab takes integer that distinguishes what type of character to add
    * creates new entity and then adds to aiPieces list
    */
    public void addAIPieces(int prefab)
    {

        if(prefab == 10)
        {
            //enemy titan
            Entity ePiece = new Entity("enemytitan", 1, 0, 2, 2, 1, 5, 2,prefab);
            aiPieces.add(new Entity(ePiece));
        }
        if(prefab == 20)
        {
            //enemy artillery
            Entity ePiece = new Entity("enemyartillery", 1, 0, 2, 2, 1, 5, 2,prefab);
            aiPieces.add(new Entity(ePiece));
        }
        if(prefab == 30)
        {
            //enemy fast boy
            Entity ePiece = new Entity("enemy fastboi", 1, 0, 2, 2, 1, 5, 2,prefab);
            aiPieces.add(new Entity(ePiece));
        }
        if(prefab == 40)
        {   
            //enemy ranger
            Entity ePiece = new Entity("enemy ranger", 1, 0, 2, 2, 1, 5, 2,prefab);
            aiPieces.add(new Entity(ePiece));
        }
        if(prefab == 50)
        {
            //enemy boss
            Entity ePiece = new Entity("enemy boss", 1, 0, 2, 2, 1, 5, 2,prefab);
            aiPieces.add(new Entity(ePiece));
        }

    }
    /**Setter.
     * Used to set the AI piece on grid.
     * @param loc to indicate exactly location of piece
     * @param entity to to indicate entity object to location
     */
    public void setAIPieces(int loc,Entity b)
    {
        aiPieces.set(loc,b);


    }
    /**Setter.
     * Used to set the human piece on grid.
     * @param loc to indicate exactly location of piece
     * @param entity to to indicate entity object to location
     */
    public void setHumanPieces(int loc,Entity b)
    {
        humanPieces.set(loc,b);
    }


    /** Method to adds any entity to master array list
    */
    public void addtoMasterList()
    {   
        {
            masterlist.add(aiPieces.get(aiPieces.size() - 1));
        }
    }
    /** Method to adds any entity to player party list
    * @param index to identify where to put entity object into playerParty array list
    */
    public void addtoPlayerParty(int index)
    {
        playerParty.add(new Entity(humanPieces.get(index)));
    }
    /** Method to remove any entity from player party
    *@param takes integer to identify what index to remove entity from array list
    */
    public void removePlayerParty(int index)
    {
        playerParty.remove(index);
    }
    /** Method to remove entity from entity party
    *@param takes integer to identify what index to remove entity from array list
    */
    public void removeEntityParty(int index)
    {
        entityParty.remove(index);
    }
    /** Method to add entity from entity party
    *@param takes integer to identify what index to add entity from array list
    */
    public void addtoEntityParty(int index)
    {
        entityParty.add(new Entity(aiPieces.get(index)));
    }
    
    /** Method to add all player 
    * This or addtoMastersList is redundant need approval of leader to delete one or the other
    */
    public void compileMasterList()
    {
        for (int b = 0; b < playerParty.size(); b++) 
        {
            masterlist.add(playerParty.get(b));
        }
        
        for (int b = 0; b < aiPieces.size(); b++) 
        {
            masterlist.add(aiPieces.get(b));
        }


    }

}
