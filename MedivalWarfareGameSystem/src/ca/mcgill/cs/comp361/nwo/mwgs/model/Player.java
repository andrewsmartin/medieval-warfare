package ca.mcgill.cs.comp361.nwo.mwgs.model;



/**
 * Player class definition.
 * Generated by the TouchCORE code generator.
 */
public class Player {
    
    private String username;
    private String password;
    private PlayerStatus status;
    private int wins;
    private int losses;
    private ColourType colour;
    private Game currentGame;
    private List<Village> controlledVillages;
    
    public ColourType getColour() {
        /* TODO: No message view defined */
        return null;
    }

    public PlayerStatus getStatus() {
        /* TODO: No message view defined */
        return null;
    }

    public void setStatus(PlayerStatus status) {
        /* TODO: No message view defined */
    }

    public void setColour(ColourType colour) {
        /* TODO: No message view defined */
    }

    public static void setUpPlayers(ArrayList<Player> players) {
        Player p;
        Iterator<Player> iterator;
        iterator = players.iterator();
        for (iterator.hasNext()) {
            p = iterator.next();
            In the actual implementation, this loop will also iterate through the ColourType enum;
            p.setColour(colour);
            p.setStatus();
        }
    }

    public ArrayList<Village> getVillages() {
        /* TODO: No message view defined */
        return null;
    }

    public void setVillages(ArrayList<Village> controlledVillages) {
        /* TODO: No message view defined */
    }

    public ArrayList<Unit> getUnits() {
        List<Unit> supportedUnits;
        Declare a new ArrayList and add the units from each village to this list;
        for (for int i = 0 to controlledVillages.size()) {
            supportedUnits = controlledVillages.getSupportedUnits();
            return supportedUnits;
        }
        return the new ArrayList of Unit;
        return ;
    }

    public void incrementWinCount() {
        /* TODO: No message view defined */
    }

    public void incrementLossCount() {
        /* TODO: No message view defined */
    }
}
