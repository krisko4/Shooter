package sample;

/**
 * Singleton tworzący gracza
 */
public class Player implements Comparable<Player>{
    private static Player instance;
    public String Nickname,Score,Level,Difficulty;
    public Player currPlayer;

    private Player()
    {}

    /**
     * Porównuje graczy - najpierw na podstawie poziomu, później uzyskanego wyniku
     * @param otherPlayer
     * @return
     */
    @Override
    public int compareTo(Player otherPlayer)
    {
        int result = Integer.parseInt(otherPlayer.Level) - Integer.parseInt(this.Level);
        if (result == 0) result = Integer.parseInt(otherPlayer.Score)-Integer.parseInt(this.Score);
        return result;
    }

    /**
     * Jeśli gracz został już stworzony pobiera jego parametry, jeśli nie - tworzy nowego gracza
     * @return
     */
    public static synchronized Player getInstance()
    {
        if (instance==null){
            instance=new Player();

        }
        return instance;
    }


}
