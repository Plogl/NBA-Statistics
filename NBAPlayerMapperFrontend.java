import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class NBAPlayerMapperFrontend implements INBAPlayerMapperFrontend{
    
    boolean B = false;
    INBAPlayerMapperBackend backend;
    List<INBAPlayer> playerDisplay = new ArrayList<>();
    Scanner scnr;

    NBAPlayerMapperFrontend(Scanner userInputScanner, INBAPlayerMapperBackend backend1)
    {
        scnr = userInputScanner;
        backend = backend1;
    }
    
    public void runCommandLoop() {
      displayMainMenu();
      String input;
      input = scnr.nextLine();
      do {
        switch (input) {
          case "1":
            if (B)
              break;
            displayPoints();
            break;
            
          case "2":
            if (B){
              break;
            }
            displayRebounds();
            
          case "3":
              if (B){
                break;
              }
              displayAssists();
              break;
              
          case "4":
            if (B){
              break;
            }
            displayBlocks();
            break;
                
          case "5":
            if (B){
              break;
            }
            displaySteals();
            break;

          case "6":
            if (B) {
              break;
            }
            backend.resetFilter();
            runCommandLoop();
            break;

          case "7":
            System.out.println("Goodbye!");
            B = true;
            break;
            default:
              if(B){
                break;
              }
              System.out.println("Invalid Input!");
              runCommandLoop();
              break;
        }
      }while (!B);
  }
    
    public void displayMainMenu(){
      System.out.println("You are in the Main Menu: ");
      System.out.println("          1) Search by Points");
      System.out.println("          2) Search by Rebounds");
      System.out.println("          3) Search by Assists");
      System.out.println("          4) Search by Blocks");
      System.out.println("          5) Search by Steals");
      System.out.println("          6) Clear Filters");
      System.out.println("          7) Exit Application");
    }
/*    
    public void setPlayer()
    {
        String name;
        String setPlayer;
        if(backend.getFilter() == null)
        {
            name = "none";
        }
        else
        {
            name = backend.getFilter();
        }
        System.out.println("You are in the Set Player Filter Menu:");
        System.out.println(          "Player name must currently contain: " + name);
        System.out.println(          "Enter a new string for player names to contain "
            + "(empty for any): ");
        setPlayer = scnr.nextLine();
        backend.setFilter(setPlayer);
        runCommandLoop();
    }
*/    
    
    public void displayPlayers(List<INBAPlayer> players){
      if(players == null)
      {
          System.out.println("No players found matching your criteria;");
      }
      else if(players.isEmpty())
      {
          System.out.println("No players found matching your criteria");
      }
      else
      {
        System.out.println("Name   Points   Rebounds   Assists   Blocks   Steals");
        for (INBAPlayer player:backend.getFilter())
        {
          System.out.println(player.getName() + "   " + player.getPoints() + "   " + player.getRebounds() + "   "
          + player.getAssists() + "   " + player.getBlocks() + "   " + player.getSteals());
        }
      }
      playerDisplay.clear();
      runCommandLoop();
  }
    
    public Boolean isLesser() {
        System.out.print("Are you looking for a player with greater than (>) or less than (<) the provided statistic?");
        String line = scnr.nextLine();
         if (line.contains("<")) {
           return true;
         } else if (line.contains(">")){
           return false;
         } else {
         System.out.println("invailed input");
         throw new IllegalArgumentException();
         }
      }

      public void displayPoints() {
        List<INBAPlayer> list = new LinkedList<>();
        int num;
        String curNum = "";
        Boolean isLess;
        System.out.println("You are in the Search for Search by Points Menu: ");
        try {
        isLess = isLesser();
        System.out.println("          Enter a number to search for in Points: ");
        num = Integer.parseInt(scnr.nextLine());
        backend.setScoreFilter(num, isLess);
        displayPlayers(backend.getFilter());
        } catch(Exception e){
      	  runCommandLoop();
        }
      }
      
      public void displayRebounds() {
  	  List<INBAPlayer> list = new LinkedList<>();
        int num;
        String curNum = "";
        Boolean isLess;
        System.out.println("You are in the Search for Search by Rebounds Menu: ");
        try {
        isLess = isLesser();
        System.out.println("          Enter a number to search for in Rebounds: ");
        num = Integer.parseInt(scnr.nextLine());
        backend.setScoreFilter(num, isLess);
        displayPlayers(backend.getFilter());
        } catch(Exception e){
     	   runCommandLoop();
        }
      }

      public void displayAssists() {
      	 List<INBAPlayer> list = new LinkedList<>();
           int num;
           String curNum = "";
           Boolean isLess;
           System.out.println("You are in the Search for Search by Assists Menu: ");
           try {
           isLess = isLesser();
           System.out.println("          Enter a number to search for in Assists: ");
           num = Integer.parseInt(scnr.nextLine());
           backend.setScoreFilter(num, isLess);
           displayPlayers(backend.getFilter());
           } catch(Exception e){
         	  runCommandLoop();
           }
      }
      
      
      public void displayBlocks() {
      	 List<INBAPlayer> list = new LinkedList<>();
           int num;
           String curNum = "";
           Boolean isLess;
           System.out.println("You are in the Search for Search by Blocks Menu: ");
           try {
           isLess = isLesser();
           System.out.println("          Enter a number to search for in Blocks: ");
           num = Integer.parseInt(scnr.nextLine());
           backend.setScoreFilter(num, isLess);
           displayPlayers(backend.getFilter());
           } catch(Exception e){
         	  runCommandLoop();
           }
      }
        
      public void displaySteals() {
      	 List<INBAPlayer> list = new LinkedList<>();
           int num;
           String curNum = "";
           Boolean isLess;
           System.out.println("You are in the Search for Search by Steals Menu: ");
           try {
           isLess = isLesser();
           System.out.println("          Enter a number to search for in Steals: ");
           num = Integer.parseInt(scnr.nextLine());
           backend.setScoreFilter(num, isLess);
           displayPlayers(backend.getFilter());
           } catch(Exception e){
         	  runCommandLoop();
           }

      }
      
    
    
}
