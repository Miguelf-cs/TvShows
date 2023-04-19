/**
 * @author - Miguel Ferreiro 
 * COP-3337 
 * NetBeans IDE 8.2
 * Java 1.8.0_221
 * Instructor - Dr. Charlyne Walker
 * 
 * The TV Show class will make a TV Show object where it will store 
 * all the information given of a specific show.
*/
//package project6;


public class TvShow 
{
    private String name ;
    private int premiereYear ;
    private int seasons ;
    private int episodes ;
    private String network ;
    private String genre ;
    private String mLead ;
    private String fLead ;
    
    public TvShow(String name, int premiereYear, int seasons, int episodes,
                String network, String genre, String mLead, String fLead)
    {
        this.name = name ;
        this.premiereYear = premiereYear ;
        this.seasons = seasons ;
        this.episodes = episodes ;
        this.network = network ;
        this.genre = genre ;
        this.mLead = mLead ;
        this.fLead = fLead ;
    }

    // Getter methods for TvShow Class
    public String getName() 
    {
        return name ;
    }

    public int getPremiereYear() 
    {
        return premiereYear ;
    }

    public int getSeasons() 
    {
        return seasons ;
    }

    public int getEpisodes() 
    {
        return episodes ;
    }

    public String getNetwork() 
    {
        return network ;
    }

    public String getGenre() 
    {
        return genre ;
    }

    public String getmLead() 
    {
        return mLead ;
    }

    public String getfLead() 
    {
        return fLead ;
    }

    // Setter methods for TvShow Class
    public void setName(String name) 
    {
        this.name = name ;
    }

    public void setPremiereYear(int premiereYear) 
    {
        this.premiereYear = premiereYear ;
    }

    public void setSeasons(int seasons) 
    {
        this.seasons = seasons ;
    }

    public void setEpisodes(int episodes) 
    {
        this.episodes = episodes ;
    }

    public void setNetwork(String network) 
    {
        this.network = network ;
    }

    public void setGenre(String genre) 
    {
        this.genre = genre ;
    }

    public void setmLead(String mLead) 
    {
        this.mLead = mLead ;
    }

    public void setfLead(String fLead) 
    {
        this.fLead = fLead ;
    }
    
    @Override
    public String toString()
    {
        return "Name: " + name + ", Year premiered: " + premiereYear + ", Seasons: "
                + seasons + ", Episodes: " + episodes + ", Network: " + network +
                ", Genre: " + genre + ", Male lead: " + mLead + ", Female lead: "
                + fLead ;
    }
    
}
