package dev.lpa;

import java.util.ArrayList;
import java.util.List;

record Affiliation(String name, String type, String countryCode){
    @Override
    public String toString() {
        return name + " (" + type + " in " + countryCode + ")";
    }
}

//Here is our first generic class: Team with one type parameter.

//T, in our case, would really stand for Player, either a football player or a baseball player.

//We want to replace any reference to the Player class, paying attention to exact match on case, to the type T.
public class Team <T extends Player, S>{

    private String teamName;
    private List<T> teamMembers = new ArrayList<>();
    private int totalWins = 0;
    private int totalLosses = 0;
    private int totalTies = 0;
    private S affiliation;

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(String teamName, S affiliation) {
        this.teamName = teamName;
        this.affiliation = affiliation;
    }

    public void addTeamMember(T t) {

        if (!teamMembers.contains(t)) {
            teamMembers.add(t);
        }
    }

    public void listTeamMembers() {

        //When we don't specify an upper bound, the upper bound is implicitly java.lang.Object, meaning that's the only
        //functionality we can use on our type parameter without first casting.
        System.out.print(teamName + " Roster:");
        System.out.println((affiliation == null ? "" : " AFFILIATION: " + affiliation));
        for(T t : teamMembers){
            System.out.println(t.name());
        }
    }

    public int ranking() {
        return (totalLosses * 2) + totalTies + 1;
    }

    public String setScore(int ourScore, int theirScore) {

        String message = "lost to";
        if (ourScore > theirScore) {
            totalWins++;
            message = "beat";
        } else if (ourScore == theirScore) {
            totalTies++;
            message = "tied";
        } else {
            totalLosses++;
        }

        return message;

    }

    @Override
    public String toString() {
        return teamName + " (Ranked "  + ranking() + ")";
    }
}

//Generic Type Parameters

//We've seen that one way to declare a generic class is to include a type parameter, as seen below in angle brackets:

//public class Team<T> {}

//Using T is just a convention, short for whatever type you want to use this Team class for.

//But you can put anything you want in there.

//Single letter types are the convention however, and they're a lot easier to spot in teh class code, so sticking to
//this convention is encouraged.

//You can have more than one type parameter, so we could do T1, T2, T3:

//public class Team<T1, T2, T3> {}

//But convention says that instead of using type parameters like this, it's easier to read the code with alternate
//letter selections.

//And these are usually S, U, and V, in that order.

//If we had three types, we'd probably want ot declare this class as shown below with T,S, and U:

//public class Team<T, S, U> {}

//A few letters are reserved for special use cases.

//The most commonly used type parameter identifiers are:

//E for Element (used extensively by the Java Collections Framework).

//K for Key(used for mapped types).

//N for Number.

//T for Type.

//V for Value.

//S, U, V etc. for 2nd, 3rd, 4th types.

//In the above code(Line 11), if we were to have public class Team<T extends Player> {}, we would get a compiler error on the
//two cases where we use Team for String and Integer.

//The message we would get on the error is that java.lang.String is not within its bound, and it should implement
//dev.lpa.Player

//What does not within its bound mean?

//Generic classes can be bounded, limiting the types that can use it.

//The above-mentioned extends keyword does not mean the same thing when it's not used in a class declaration.

//It isn't saying our type T extends Player, although it could.

//It's saying the parametrized type T has to be a Player, or a subtype of Player.

//Player in this case could have been either a class or an interface, the syntax would be the same.

//This declaration establishes what is called an upper bound on the types that are allowed to be used with this class.

//This means that only subtypes of Player or a Player itself (if it were a class and not an instance) can be used with
//this class.

//In this case, this code doesn't care if Player is an interface or a class. We use the extends keyword for either.

//There are good reasons for specifying an upper bound: We can limit what types can be used by the generic class.

//Why specify an upper bound?

//An upper bound permits access to the bounded type's functionality.

//An upper bound limits the kind of type parameters you can use when using a generic class. The type used must be equal
//to, or a subtype of the bounded type.