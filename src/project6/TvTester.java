/**
 * @author - Miguel Ferreiro 
 * COP-3337 
 * NetBeans IDE 8.2
 * Java 1.8.0_221
 * Instructor - Dr. Charlyne Walker
 * 
 * The TV Tester class will get all the information from a CSV file 
 * and will execute sorting and searching algorithms.
*/
//package project6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TvTester 
{
 
    public static void main(String[] args) 
    {
        // creating array of TvShows
        TvShow[] myShows = new TvShow[20] ;
        
        // initial value of where TvShow objects should start getting placed in array
        int listSize = 0 ;
        
        String path = "C:\\Users\\Miguel\\Downloads\\tv_shows.csv" ;
        String line = "" ;
               
        try {
            BufferedReader br = new BufferedReader(new FileReader(path)) ;
            
            // skips first line in CSV file where it has the header of each column
            String temp = br.readLine() ;
            
            while( (line = br.readLine()) != null )
            {
                // Use string.split to load a string array with the values from
                // each line of the file using a comma as a delimiter
                String[] values = line.split(",") ;               
                TvShow show = createTvShow(values) ;   
                
                // Adding show to array
                myShows[listSize++] = show ;
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TvTester.class.getName()).log(Level.SEVERE, null, ex) ;
        } catch (IOException ex) {
            Logger.getLogger(TvTester.class.getName()).log(Level.SEVERE, null, ex) ;
        }
        
        
        System.out.println("Before Sorting: ") ;
        printTvShows(myShows) ;
        
        // Sorting Examples
        
        System.out.println("\nSorted by Show Title - Ascending:") ;
        insertSortTitle(myShows) ;
        
        System.out.println("\nSorted by Seasons - Descending:") ;
        insertSortSeason(myShows) ;
        
        System.out.println("\nSorted by Year Premiered - Ascending:") ;
        selectSortYear(myShows) ;
        
        System.out.println("\nSorted by Genre - Descending:") ;
        selectSortGenre(myShows) ;
        
        System.out.println("\nSorted by Network - Descending:") ;
        sortNetwork(myShows, 0, myShows.length-1) ;
        // Printing out new merged array sorted by networks
        for (TvShow dest1 : myShows) 
        {
            System.out.println("Name: " + dest1.getName() + ", Year Premiered: " 
                            + dest1.getPremiereYear() + ", Seasons: " 
                            + dest1.getSeasons() + ", NETWORK: " + dest1.getNetwork()) ;
        }
               
        System.out.println("\nSorted by Episodes - Ascending:") ;
        sortEpisode(myShows, 0, myShows.length-1) ;
        // Printing out new merged array sorted by episodes
        for (TvShow dest1 : myShows) 
        {
            System.out.println("Name: " + dest1.getName() + ", Year Premiered: " 
                            + dest1.getPremiereYear() + ", EPISODES: " 
                            + dest1.getEpisodes() + ", Network: " + dest1.getNetwork()) ;
        }
        
        
        // Searching Examples
        
        // Checking to see what shows lasted 2 years
        System.out.println("\nList of shows that lasted for 2 years:") ;
        linearSearchSeason(myShows, 2);
        
        // Checking to see what shows have more than 100 episodes
        System.out.println("\nList of shows that have more than 100 episodes:") ;
        linearSearchEpisode(myShows, 100);
        
        //check to see which shows are aires on CBS
        int location = 0 ;
        sortNetwork(myShows, 0, myShows.length-1) ;
        System.out.println("") ;
        System.out.println("Are there any shows that were aired on CBS?") ;
        location = binarySearchCBS(myShows, "CBS") ;
        if( location > 0 )
            System.out.println("Yes! " + myShows[location].getName() + " was aired on CBS") ;
        else
            System.out.println("No shows were aired on CBS") ;
        
        //check to see which shows are fantasy genre
        int location2 = 0 ;      
        System.out.println("") ;
        System.out.println("Are there any shows that are in the fantasy category?") ;
        location2 = binarySearchCBS(myShows, "Fantasy") ;
        if( location2 > 0 )
            System.out.println("Yes! " + myShows[location2].getName() + " is fantasy") ;
        else
            System.out.println("No shows are fantasy") ;
    }
    
    // Prints whole array of Tv Shows
    public static void printTvShows(TvShow[] t)
    {
        for( int i = 0; i < t.length; i++ )
        System.out.println(t[i]) ;   
    }

    // Gathering all info from one Tv Show and making it into an object
    private static TvShow createTvShow(String[] values) 
    {
        String name = values[0] ;
        int premiereYear = Integer.parseInt(values[1]) ;
        int seasons = Integer.parseInt(values[2]) ;
        int episodes = Integer.parseInt(values[3]) ;
        String network = values[4] ;
        String genre = values[5] ;
        String mLead = values[6] ;
        String fLead = values[7] ;
        
        return new TvShow(name, premiereYear, seasons, episodes,
                          network, genre, mLead, fLead) ;
    }
    
    // Standard insertion sort algorithm(Ascending)
    public static void insertSortTitle(TvShow[] source)
    {
        TvShow[] dest = new TvShow[source.length] ;
        
        for ( int i = 0 ; i < source.length ; i++ )
        {
            TvShow next = source[ i ] ;
            int insertindex = 0 ;
            int k = i ;
            
            while ( k > 0 && insertindex == 0 )
            {
                if( next.getName().compareTo(dest[ k - 1 ].getName()) > 0 )
                {
                    insertindex = k ;
                }
                else
                {
                    dest[ k ] = dest[ k - 1 ] ;
                }
                
                k-- ;
            }

            dest[ insertindex ] = next ;
        } 
        
        // Printing out new sorted array
        for (TvShow dest1 : dest) 
        {
            System.out.println("NAME: " + dest1.getName() + ", Year Premiered: " 
                            + dest1.getPremiereYear() + ", Male lead: " 
                            + dest1.getmLead() + ", Female lead: " + dest1.getfLead()) ;
        }
    }
    
    // Standard insertion sort algorithm(Descending)
    public static void insertSortSeason(TvShow[] source)
    {
        TvShow[] dest = new TvShow[source.length] ;
        
        for ( int i = 0 ; i < source.length ; i++ )
        {
            TvShow next = source[ i ] ;
            int insertindex = 0 ;
            int k = i ;
               
            while ( k > 0 && insertindex == 0 )
            {
                if ( next.getSeasons() < dest[ k - 1 ].getSeasons() )
                {
                    insertindex = k ;
                }
                else
                {
                    dest[ k ] = dest[ k - 1 ] ;
                }
                   
                    k-- ;
            }

            dest[ insertindex ] = next ;
        } 
         
        // Printing out new sorted array
        for (TvShow dest1 : dest) 
        {
            System.out.println("Name: " + dest1.getName() + ", Year Premiered: " 
                            + dest1.getPremiereYear() + ", SEASONS: " 
                            + dest1.getSeasons()) ;
        }
    }
    
    // Standard selection sort algorithm(Ascending)
    public static void selectSortYear(TvShow[] source)
    {
        int i ;
        int k ;
        int posmax ;
        TvShow temp ;
        
        for ( i = source.length - 1 ; i >= 0 ; i-- )
           {
               // find largest element in the i elements
               posmax = 0 ;
               for ( k = 0 ; k <= i ; k++ )
                {
                   if ( source[ k ].getPremiereYear() > source[ posmax ].getPremiereYear() )
                        posmax = k ;
                }
                // swap the largest with the position i
                // now the item is in its proper location 
                temp = source[ i ] ;
                source[ i ] = source[ posmax ] ;
                source[ posmax ] = temp ;
            }
        // Printing out name, year premiered and number of seasons
        for (TvShow source1 : source) 
        {
            System.out.println("Name: " + source1.getName() + ", YEAR PREMIERED: " 
                            + source1.getPremiereYear() + ", Seasons: " 
                            + source1.getSeasons()) ;
        }
    }
    
    // Standard selection sort algorithm(Descending) 
    public static void selectSortGenre(TvShow[] source)
    {
        int i ;
        int k ;
        int posmax ;
        TvShow temp ;
      
        for ( i = source.length - 1 ; i >= 0 ; i-- )
        {
            // find largest element in the i elements
            posmax = 0 ;
            for ( k = 0 ; k <= i ; k++ )
            {
                if ( source[ k ].getGenre().compareTo(source[ posmax ].getGenre()) < 0 )
                    posmax = k ;
            }
            
            // swap the largest with the position i
            // now the item is in its proper location 
            temp = source[ i ] ;
            source[ i ] = source[posmax ] ;
            source[ posmax ] = temp ;
        }
        // Printing out name, number of episodes and genre
        for (TvShow source1 : source) 
        {
            System.out.println("Name: " + source1.getName() + ", Episodes: " 
                               + source1.getEpisodes() + ", GENRE: " 
                               + source1.getGenre()) ;
        }
    }
    
    // Standard merge sort algorithm(Descending)
    public static void sortNetwork(TvShow[] a, int low, int high)
    {
        if ( low == high )
            return ;

        int mid = ( low + high ) / 2 ;

        sortNetwork( a, low, mid ) ; 
        sortNetwork( a, mid + 1, high) ; 

        mergeNetwork( a, low, mid, high) ;
                     
    }   
    
    public static void mergeNetwork( TvShow[] a, int low, int mid, int high )
    {
        TvShow[] temp = new TvShow[ high - low + 1 ] ;

        int i = low, j = mid + 1, n = 0 ;

        while ( i <= mid || j <= high )
        {
            if ( i > mid )
            {
                temp[ n ] = a[ j ] ;
                j++ ;
            }
            else if ( j > high )
            {
                temp[ n ] = a[ i ] ;
                i++ ;
            }
            else if ( a[ i ].getNetwork().compareTo(a[ j ].getNetwork()) > 0 )
            {
                temp[ n ] = a[ i ] ;
                i++ ;
            }
            else
            {
                temp[ n ] = a[ j ] ;
                j++ ;
            }
            
            n++ ;
        } 

        for ( int k = low ; k <= high ; k++ )
            a[ k ] = temp[ k - low ] ;
    } 
    
    // Standard merge sort algorithm(Descending)
    public static void sortEpisode(TvShow[] a, int low, int high)
    {
        if ( low == high )
            return ;

        int mid = ( low + high ) / 2 ;

        sortEpisode( a, low, mid ) ; 
        sortEpisode( a, mid + 1, high) ; 

        mergeEpisode( a, low, mid, high) ;                    
    }
    
    public static void mergeEpisode( TvShow[] a, int low, int mid, int high )
    {
        TvShow[] temp = new TvShow[ high - low + 1 ] ;

        int i = low, j = mid + 1, n = 0 ;

        while ( i <= mid || j <= high )
        {
            if ( i > mid )
            {
                temp[ n ] = a[ j ] ;
                j++ ;
            }
            else if ( j > high )
            {
                temp[ n ] = a[ i ] ;
                i++ ;
            }
            else if ( a[ i ].getEpisodes() < a[ j ].getEpisodes() )
            {
                temp[ n ] = a[ i ] ;
                i++ ;
            }
            else
            {
                temp[ n ] = a[ j ] ;
                j++ ;
            }
            
            n++ ;
        } 

        for ( int k = low ; k <= high ; k++ )
            a[ k ] = temp[ k - low ] ;
    } 
    
    // Standard sequesntial search algorithm
    public static void linearSearchSeason(TvShow [] source, int target)
    {
     boolean match = false ;
     
     for( int i = 0 ; i < source.length ; i++ )
     {
        if( target == source[ i ].getSeasons() )
        {
            System.out.println("Name: " + source[i].getName() + ", Year Premiered: "
                                + source[i].getPremiereYear() + ", Seasons: " +
                                source[i].getSeasons() + ", Network: " + source[i].getNetwork()) ; 
            match = true ;  
        }
     } 
     if(!match)
         System.out.println("There is no show that lasted 2 years") ;
    }
    
    public static void linearSearchEpisode(TvShow [] source, int target)
    {
     boolean match = false ;
     
     for( int i = 0 ; i < source.length ; i++ )
     {
        if( source[ i ].getEpisodes() > target )
        {
            System.out.println("Name: " + source[i].getName() + ", Seasons: " + 
                               source[i].getSeasons() + ", Episodes: " + 
                               source[i].getEpisodes()) ;    
            match = true ;  
        }
     } 
     if(!match)
         System.out.println("There is no show that has more than 100 episodes") ;
    }
    
    // Standard binary search algorithm
    public static int binarySearchCBS(TvShow [] source, String network)
    {
        int low = 0 ;
        int high = source.length - 1 ;
          
        while ( high >= low )
        {
            int mid = (low + high) / 2 ;
            
            if (network.compareTo(source[mid].getNetwork()) > 0)
               high = mid - 1 ;
            else if (network.equals(source[mid].getNetwork()))
                return mid; 
            else
               low = mid + 1 ;
        }
          
        return -1 - low ;
    }
    
    public static int binarySearchFantasy(TvShow [] source, String genre)
    {
        int low = 0 ;
        int high = source.length - 1 ;
          
        while ( high >= low )
        {
            int mid = (low + high) / 2 ;
            
            if (genre.compareTo(source[mid].getGenre()) > 0)
               high = mid - 1 ;
            else if (genre.equals(source[mid].getGenre()))
               return mid ;
            else
               low = mid + 1 ;
        }
          
        return -1 - low ;
    }
    
}
