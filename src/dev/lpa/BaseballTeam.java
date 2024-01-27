package dev.lpa;

import java.util.ArrayList;
import java.util.List;

public class BaseballTeam {

    private String teamName;
    private List<BaseballPlayer> teamMembers = new ArrayList<>();
    private int totalWins = 0;
    private int totalLosses = 0;
    private int totalTies = 0;

    public BaseballTeam(String teamName) {
        this.teamName = teamName;
    }

    //Since BaseballPlayer is a record, and records come with an implicit equals method, this method can test the
    //equality of all the record's attributes.
    public void addTeamMember(BaseballPlayer player){
        if(!teamMembers.contains(player)){
            teamMembers.add(player);
        }
    }

    public void listTeamMembers() {

        System.out.println(teamName + " Roster:");
        System.out.println(teamMembers);
    }

    public int ranking(){
        return (totalLosses * 2) + totalTies + 1;
    }

    public String setScore(int ourScore, int theirScore){

        String message = "lost to";
        if(ourScore > theirScore){
            totalWins++;
            message = "beat";
        } else if (ourScore == theirScore){
            totalTies++;
            message = "tied";
        } else {
            totalLosses++;
        }
        return message;
    }

    @Override
    public String toString() {
        return teamName + " (Ranked " + ranking() + ")";
    }
}

//Here, we have built a BaseballTeam class, and now we need to have a class that can handle football teams as well.

//One solution is to duplicate the code- we could copy and paste the BaseballTeam, and rename everything for FootballTeam
//, and create a FootballPlayer.

//This means you would have to make sure any changes you made to one team or player, that made sense for the other team
//and player, had to be made in both sets of code.

//This is rarely a recommended approach unless team operations are significantly different.

//The second solution might be to change BaseballTeam to simply Team, and use an interface type (Or abstract or base
//class) called Player.

//We can make Player an interface, and have BaseballPlayer and FootballPlayer implement that interface.

//This is better than the copy/paste method, but it still has problems.

//We are leaving the rules up to whoever is building the code with the interface.

//Alternatively, we could make our own class, in this case Team, and make it generic (Making SportsTeam generic).

//Generics allow the compiler to do compile-time type checking, when adding and processing elements in the list.

//Generics simplify code, because we don't have to do our own type checking and casting, as we would if the type
//in our elements was Object.