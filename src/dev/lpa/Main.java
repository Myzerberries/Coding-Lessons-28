package dev.lpa;

interface Player {
    String name();
}
record BaseballPlayer(String name, String position) implements Player{}
record FootballPlayer(String name, String position) implements Player{}
record VolleyballPlayer(String name, String position) implements Player {}

public class Main {

    public static void main(String[] args) {

        var philly = new Affiliation("city", "Philedelphia, PA", "US");
        BaseballTeam phillies1 = new BaseballTeam("Philadelphia Phillies");
        BaseballTeam astros1 = new BaseballTeam("Houston Atros");
        scoreResult(phillies1, 3, astros1, 5);

        SportsTeam phillies2 = new SportsTeam("Philadelphia Phillies");
        SportsTeam astros2 = new SportsTeam("Houston Atros");
        scoreResult(phillies2, 3, astros2, 5);

        Team<BaseballPlayer, Affiliation> phillies = new Team<>("Philadelphia Phillies", philly);
        Team<BaseballPlayer, Affiliation> astros = new Team<>("Houston Atros");
        scoreResult(phillies, 3, astros, 5);

        var harper = new BaseballPlayer("B Harper", "Right Fielder");
        var marsh = new BaseballPlayer("B Marsh", "Right Fielder");
        phillies.addTeamMember(harper);
        phillies.addTeamMember(marsh);
        var guthrie = new BaseballPlayer("D Guthrie", "Center Fielder");
        phillies.addTeamMember(guthrie);
        phillies.listTeamMembers();

        SportsTeam afc1 = new SportsTeam("Adelaide Crows");
        Team<FootballPlayer, String> afc = new Team<>("Adelaide Crows",
                "City of Adelaide, South Australia, in AU");
        var tex = new FootballPlayer("Tex Walker", "Centre half forward");
        afc.addTeamMember(tex);

        //There is no runtime type checking when using an interface, in this example, as seen below. We are able to add
        //a baseball player to the football player roster.

        //This is not what we would want. We could leave the rules up to whoever is using this code, or we could build
        //in some rules. Generics give us this solution by creating a generic team, meaning a team class, which has a
        //type parameter.

//        var guthrie = new BaseballPlayer("D Guthrie", "Center Fielder");
//        afc.addTeamMember(guthrie);
        afc.listTeamMembers();

        Team<VolleyballPlayer, Affiliation> adelaide = new Team<>("Adelaide Storm");
        adelaide.addTeamMember(new VolleyballPlayer("N Roberts", "Setter"));
        adelaide.listTeamMembers();

        var canberra = new Team<VolleyballPlayer, Affiliation>("Canberra Heat");
        canberra.addTeamMember(new VolleyballPlayer("B Black", "Opposite"));
        canberra.listTeamMembers();
        scoreResult(canberra, 0, adelaide, 1);

        //We can't use primitive types as the type when using generics, but we can if we autobox it:

//        Team<Integer> melbourneVB = new Team<>("Melbourne Vipers");
    }

    public static void scoreResult(BaseballTeam team1, int t1_score, BaseballTeam team2, int t2_score){

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    public static void scoreResult(SportsTeam team1, int t1_score, SportsTeam team2, int t2_score){

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    //Below, we get a warning from IntelliJ about the Raw Use of the reference type.

    //When using generic classes, it's recommended that you include a type parameter.

    //The raw use of these classes is still available, for backwards compatibility, but it's discouraged for several
    //reasons.

    //Generics allow the compiler to do compile-time type checking, when adding and processing elements in the list.

    //Generics simplify code, because we don't have to do our own type checking and casting, as we would if the type
    //in our elements was Object.
    public static void scoreResult(Team team1, int t1_score, Team team2, int t2_score){

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

}

//What are Generics?

//Java supports generic types, such as classes, records and interfaces.

//It also supports generic methods.

//Below we see a regular class declaration next to a generic class.

//The thing to notice with the generic class is that the class declaration has angle brackets with a T in them, directly
//after the class name.

//T is the placeholder for a type, which will be referenced later.

//This is called a type identifier, and it can be any letter or word, but T which is short for Type is commonly used.

//          Regular Class                                           Generic Class

//          class ITellYou {                                        class YouTellMe<T> {
//              private String field;                                   private T field;
//          }                                                       }

//For the regular class we have declared a field with a type of String.

//For the generic class, the field's type is that placeholder, T, and this means it can be any type at all.

//The T in the angle brackets means it's the same type as the T, specified as the type of the field.

//Below, we have a variable declaration of the generic ArrayList.

//In the declaration of a reference type that uses generics, teh type parameter is declared in angle brackets.

//The reference type is ArrayList, the type parameter (or parameterized type) is String, which is declared in angle
//brackets, and listOfString is teh variable name:

//Reference Type<Type Parameter>Variable Name

//ArrayList<String>listOfString

//Many of Java's libraries are written using generic classes and interfaces, so we'll be using them often moving forward.
