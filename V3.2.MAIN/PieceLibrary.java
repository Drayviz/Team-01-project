import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class PieceLibrary
{
	/**
	 * Instance variables
	 */
    private ArrayList<Entity> humanPieces = new ArrayList<Entity>();
    private ArrayList<Entity> aiPieces = new ArrayList<Entity>();
    private ArrayList<Entity> entityParty = new ArrayList<Entity>();
    private ArrayList<Entity> playerParty = new ArrayList<Entity>();
    private ArrayList<Entity> masterlist = new ArrayList<Entity>();
    private Random r = new Random();

    /**
     * Copy constructor for class PieceLibrary
     */

    PieceLibrary(PieceLibrary copy)
    {
        humanPieces = copy.getHumanPieces();
        aiPieces = copy.getAIPieces();
        entityParty = copy.getAIParty();
        playerParty = copy.getPlayerParty();
        masterlist = copy.getMasterList();

    }
    /**
     * Getter for human pieces
     * @return array list with all human pieces
     */
    public ArrayList<Entity> getHumanPieces() {
        ArrayList<Entity> decoy = new ArrayList<Entity>();
        for (Entity h:humanPieces) {
            decoy.add(new Entity(h));
        }
        return decoy;
    }
    /**
     * Getter for AI pieces
     * @return array list with all AI pieces
     */
    public ArrayList<Entity> getAIPieces() {
        ArrayList<Entity> decoy = new ArrayList<Entity>();
        for (Entity e:aiPieces) {
            decoy.add(new Entity(e));
        }
        return decoy;
    }
    
    public ArrayList<Entity> getAIParty() {
       
        return entityParty;
    }

    public ArrayList<Entity> getPlayerParty() {
       
        return playerParty;
    }
    /**
     * Getter for master list
     * @return array list with all entities
     */
    public ArrayList<Entity> getMasterList() {
    
        return masterlist;
    }
    /**
     * Method that creates names for each entity
     * @return string with two names
     */
    public String namegenerator()
    {
        String First[] = {"adamant", "adroit", "amatory", "animistic", "antic", "arcadian", "baleful", "bellicose", "bilious", "boorish", "calamitous", "caustic", "cerulean", "comely", "concomitant", "contumacious", "corpulent", "crapulous", "defamatory", "didactic", "dilatory", "dowdy", "efficacious", "effulgent", "egregious", "endemic", "equanimous", "execrable", "fastidious", "feckless", "fecund", "friable", "fulsome", "garrulous", "guileless", "gustatory", "heuristic", "histrionic", "hubristic", "incendiary", "insidious", "insolent", "intransigent", "inveterate", "invidious", "irksome", "jejune", "jocular", "judicious", "lachrymose", "limpid", "loquacious", "luminous", "mannered", "mendacious", "meretricious", "minatory", "mordant", "munificent", "nefarious", "noxious", "obtuse", "parsimonious", "pendulous", "pernicious", "pervasive", "petulant", "platitudinous", "precipitate", "propitious", "puckish", "querulous", "quiescent", "rebarbative", "recalcitant", "redolent", "rhadamanthine", "risible", "ruminative", "sagacious", "salubrious", "sartorial", "sclerotic", "serpentine", "spasmodic", "strident", "taciturn", "tenacious", "tremulous", "trenchant", "turbulent", "turgid", "ubiquitous", "uxorious", "verdant", "voluble", "voracious", "wheedling", "withering", "zealous"};
        String Last[] = {"ninja", "chair", "pancake", "statue", "unicorn", "rainbows", "laser", "senor", "bunny", "captain", "nibblets", "cupcake", "carrot", "gnomes", "glitter", "potato", "salad", "toejam", "curtains", "beets", "toilet", "exorcism", "stick figures", "mermaid eggs", "sea barnacles", "dragons", "jellybeans", "snakes", "dolls", "bushes", "cookies", "apples", "ice cream", "ukulele", "kazoo", "banjo", "opera singer", "circus", "trampoline", "carousel", "carnival", "locomotive", "hot air balloon", "praying mantis", "animator", "artisan", "artist", "colorist", "inker", "coppersmith", "director", "designer", "flatter", "stylist", "leadman", "limner", "make-up artist", "model", "musician", "penciller", "producer", "scenographer", "set decorator", "silversmith", "teacher", "auto mechanic", "beader", "bobbin boy", "clerk of the chapel", "filling station attendant", "foreman", "maintenance engineering", "mechanic", "miller", "moldmaker", "panel beater", "patternmaker", "plant operator", "plumber", "sawfiler", "shop foreman", "soaper", "stationary engineer", "wheelwright", "woodworkers"};

        return First[r.nextInt(First.length)] + " " + Last[r.nextInt(Last.length)];

    }
    /**
     * Method that takes in parameter prefab to determine character type with unique stats
     * @param prefab is an integer to distinguish different type of characters
     */
    public void addHumanPieces(int prefab)
    {
        if(prefab == 1)
        {   
            //artillery1
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 1, 1, 2, 2,prefab,0);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 2)
        {
            //ranger1
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 1, 1, 2, 2,prefab,0);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 3)
        {
            //titan1
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 1, 1, 2, 2,prefab,0);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 4)
        {
            //artillery2
            Entity hPiece = new Entity(namegenerator(),2, 0, 4, 1, 1, 2, 2,prefab,0);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 5)
        {
            //ranger2
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 1, 1, 2, 2,prefab,0);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 6)
        {
            //titan2
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 1, 1, 2, 2,prefab,0);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 7)
        {
            //artillery3
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 1, 1, 2, 2,prefab,0);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 8)
        {
            //ranger3
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 1, 1, 2, 2,prefab,0);
            humanPieces.add(new Entity(hPiece));
        }
        if(prefab == 9)
        {
            //titan3
            Entity hPiece = new Entity(namegenerator(),1, 0, 4, 1, 1, 2, 2,prefab,0);
            humanPieces.add(new Entity(hPiece));
        }
    }
    
    /**
     * Method that takes in parameter prefab to determine character type with unique stats for the AI
     * @param prefab is an integer to distinguish different type of characters
     */
    public void addAIPieces(int prefab)
    {

        if(prefab == 10)
        {
            //enemy titan
            Entity ePiece = new Entity("enemytitan", 1, 0, 2, 2, 1, 5, 2,prefab,0);
            aiPieces.add(new Entity(ePiece));
        }
        if(prefab == 20)
        {
            //enemy artillery
            Entity ePiece = new Entity("enemyartillery", 1, 0, 2, 2, 1, 5, 2,prefab,0);
            aiPieces.add(new Entity(ePiece));
        }
        if(prefab == 30)
        {
            //enemy fast boy
            Entity ePiece = new Entity("enemy fastboi", 1, 0, 2, 2, 1, 5, 2,prefab,0);
            aiPieces.add(new Entity(ePiece));
        }
        if(prefab == 40)
        {   
            //enemy ranger
            Entity ePiece = new Entity("enemy ranger", 1, 0, 2, 2, 1, 5, 2,prefab,0);
            aiPieces.add(new Entity(ePiece));
        }
        if(prefab == 50)
        {
            //enemy boss
            Entity ePiece = new Entity("enemy boss", 1, 0, 2, 2, 1, 5, 2,prefab,0);
            aiPieces.add(new Entity(ePiece));
        }

    }
    /**
     * Setter for AI pieces
     * @param loc is desired location of piece
     * @param b is entity in question
     */
    public void setAIPieces(int loc,Entity b)
    {
        aiPieces.set(loc,b);


    }
    /**
     * Setter for Human piece
     * @param loc is desired location of piece
     * @param b is entity in question
     */
    public void setHumanPieces(int loc,Entity b)
    {
        humanPieces.set(loc,b);
    }


    /**
     * Method that adds entity into array list with all entities
     */
    public void addtoMasterList()
    {   
        {
            masterlist.add(aiPieces.get(aiPieces.size() - 1));
        }
    }
    /**
     * Method to add certain entity to Player party arraly list
     * @param index to add into array list
     */
    public void addtoPlayerParty(int index)
    {
        playerParty.add(new Entity(humanPieces.get(index)));
    }
    /**
     * Method that removes certain entity from Player Party
     * @param index of entity that you wish to remove
     */
    public void removePlayerParty(int index)
    {
        playerParty.remove(index);
    }
    /**
     * Method that removes certain entity from Entity Party
     * @param index of entity that you wish to remove
     */
    public void removeEntityParty(int index)
    {
        entityParty.remove(index);
    }
    /**
     * Method to add certain entity to Entity Party arraylist
     * @param index to add into array list
     */
    public void addtoEntityParty(int index)
    {
        entityParty.add(new Entity(aiPieces.get(index)));
    }
    /**
     * Method that adds all entities into one array list using both playerparty and aiPieces
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