package GraphPackage;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {

        //Testing profile methods
//        ArrayList<String> myFriends = new ArrayList<>();
//        myFriends.add("Boban");
//        myFriends.add("Josh");
//        myFriends.add("Jaykob");
//
//        Profile myProfile = new Profile("Petar", 21, myFriends);
//        System.out.println(myProfile.toString());
//
//        System.out.println("My name:   " + myProfile.getName());
//        System.out.println("My age:    " + myProfile.getAge());
//        System.out.println("My status: " + myProfile.getStatus());
//
//        myProfile.setName("Pepe");
//        myProfile.setAge(5);
//        myProfile.setStatus(Status.OFFLINE);
//        System.out.println(myProfile.toString());
//
//        myProfile.addFriend("Milos");
//        myProfile.addFriend("Bogdan");
//        System.out.println(myProfile.toString());
//
//        Profile friendProfile = new Profile("Novak");
//        System.out.println(friendProfile.toString());
//
//        Profile grandpa = new Profile("Rade", 77, Status.OFFLINE);
//        System.out.println(grandpa.toString());
//
//        Profile grandma = new Profile("Olga", Status.ONLINE);
//        System.out.println(grandma.toString());
//
//        Profile uncle = new Profile("Nenad", 51);
//        System.out.println(uncle.toString());

        // Testing ProfileManager class methods:
        ProfileManager manager = new ProfileManager();
        Profile Pepe = new Profile("Petar", 21);
        Profile Kumo = new Profile("Kumo", 10);
        Profile Kiku = new Profile("Kiku", 2);
        manager.addProfile(Pepe);
        //manager.displayAllProfiles(Pepe.getName());

        // Add two more profiles and make new friends.
        manager.addProfile(Kumo);
        manager.addProfile(Kiku);
        manager.makeFriends(Pepe, Kumo);
        manager.makeFriends(Kumo, Kiku);
        //manager.displayAll(Pepe.getName());
        //manager.displayAllProfiles(Pepe.getName());

        // Make one more friendship.
        manager.makeFriends(Pepe, Kiku);
        manager.displayAllProfiles(Pepe);

        // Add one more profile and make friends with two existing members.
        Profile Koi = new Profile("Koi", 10);
        manager.addProfile(Koi);
        manager.makeFriends(Koi, Kumo);
        manager.makeFriends(Koi, Pepe);
        manager.displayAllProfiles(Kiku);
        Profile Olga = new Profile("Olga", 71);
        Profile Rade = new Profile("Rade", 76);
        manager.addProfile(Olga);
        manager.addProfile(Rade);
        manager.makeFriends(Pepe, Olga);
        manager.makeFriends(Pepe, Rade);
        manager.makeFriends(Rade, Olga);
        manager.displayAll(Koi);

        manager.start();

    }
}

// Required: Testing all Profile class methods:
//        ========= Profile ==========
//        Name:   Petar
//        Age:    21
//        Status: ONLINE
//        Friends:
//        1. Boban
//        2. Josh
//        3. Jaykob
//        ============================
//
//        My name:   Petar
//        My age:    21
//        My status: ONLINE
//
//        ========= Profile ==========
//        Name:   Pepe
//        Age:    5
//        Status: OFFLINE
//        Friends:
//        1. Boban
//        2. Josh
//        3. Jaykob
//        ============================
//
//
//        ========= Profile ==========
//        Name:   Pepe
//        Age:    5
//        Status: OFFLINE
//        Friends:
//        1. Boban
//        2. Josh
//        3. Jaykob
//        4. Milos
//        5. Bogdan
//        ============================
//
//
//        ========= Profile ==========
//        Name:   Novak
//        Age:    0
//        Status: ONLINE
//        Friends: NONE
//        ============================
//
//
//        ========= Profile ==========
//        Name:   Rade
//        Age:    77
//        Status: OFFLINE
//        Friends: NONE
//        ============================
//
//
//        ========= Profile ==========
//        Name:   Olga
//        Age:    0
//        Status: ONLINE
//        Friends: NONE
//        ============================
//
//
//        ========= Profile ==========
//        Name:   Nenad
//        Age:    51
//        Status: ONLINE
//        Friends: NONE
//        ============================

// Required: Testing ProfileManager class methods
//        ******* Profiles Info ******
//
//        ========= Profile ==========
//        Name:   Petar
//        Age:    21
//        Status: ONLINE
//        Friends: NONE
//        ============================
//
//        ****************************
//
//        ******* All Profiles *******
//        Petar
//        Kumo
//        Kiku
//        ****************************
//
//        ******* Profiles Info ******
//
//        ========= Profile ==========
//        Name:   Petar
//        Age:    21
//        Status: ONLINE
//        Friends:
//        1. Kumo
//        ============================
//
//        ========= Profile ==========
//        Name:   Kumo
//        Age:    10
//        Status: ONLINE
//        Friends:
//        1. Petar
//        2. Kiku
//        ============================
//
//        ========= Profile ==========
//        Name:   Kiku
//        Age:    2
//        Status: ONLINE
//        Friends:
//        1. Kumo
//        ============================
//
//        ****************************
//
//        ******* Profiles Info ******
//
//        ========= Profile ==========
//        Name:   Petar
//        Age:    21
//        Status: ONLINE
//        Friends:
//        1. Kumo
//        2. Kiku
//        ============================
//
//        ========= Profile ==========
//        Name:   Kumo
//        Age:    10
//        Status: ONLINE
//        Friends:
//        1. Petar
//        2. Kiku
//        ============================
//
//        ========= Profile ==========
//        Name:   Kiku
//        Age:    2
//        Status: ONLINE
//        Friends:
//        1. Kumo
//        2. Petar
//        ============================
//
//        ****************************
//
//        ******* Profiles Info ******
//
//        ========= Profile ==========
//        Name:   Kiku
//        Age:    2
//        Status: ONLINE
//        Friends:
//        1. Kumo
//        2. Petar
//        ============================
//
//        ========= Profile ==========
//        Name:   Kumo
//        Age:    10
//        Status: ONLINE
//        Friends:
//        1. Petar
//        2. Kiku
//        3. Koi
//        ============================
//
//        ========= Profile ==========
//        Name:   Petar
//        Age:    21
//        Status: ONLINE
//        Friends:
//        1. Kumo
//        2. Kiku
//        3. Koi
//        ============================
//
//        ========= Profile ==========
//        Name:   Koi
//        Age:    10
//        Status: ONLINE
//        Friends:
//        1. Kumo
//        2. Petar
//        ============================
//
//        ****************************


//       Menu Output:
// =======================================
//
//        ******* Profiles Info ******
//
//        ========= Profile ==========
//        Name:   Petar
//        Age:    21
//        Status: ONLINE
//        Friends:
//        1. Kumo
//        2. Kiku
//        ============================
//
//        ========= Profile ==========
//        Name:   Kumo
//        Age:    10
//        Status: ONLINE
//        Friends:
//        1. Petar
//        2. Kiku
//        ============================
//
//        ========= Profile ==========
//        Name:   Kiku
//        Age:    2
//        Status: ONLINE
//        Friends:
//        1. Kumo
//        2. Petar
//        ============================
//
//        ****************************
//        Passed in add Profile.
//
//        ******* Profiles Info ******
//
//        ========= Profile ==========
//        Name:   Kiku
//        Age:    2
//        Status: ONLINE
//        Friends:
//        1. Kumo
//        2. Petar
//        ============================
//
//        ========= Profile ==========
//        Name:   Kumo
//        Age:    10
//        Status: ONLINE
//        Friends:
//        1. Petar
//        2. Kiku
//        3. Koi
//        ============================
//
//        ========= Profile ==========
//        Name:   Petar
//        Age:    21
//        Status: ONLINE
//        Friends:
//        1. Kumo
//        2. Kiku
//        3. Koi
//        ============================
//
//        ========= Profile ==========
//        Name:   Koi
//        Age:    10
//        Status: ONLINE
//        Friends:
//        1. Kumo
//        2. Petar
//        ============================
//
//        ****************************
//        Passed in add Profile.
//        Passed in add Profile.
//
//        ******* All Profiles *******
//        Koi
//        Kumo
//        Petar
//        Kiku
//        Olga
//        Rade
//        ****************************
//
//        ****************************************************
//        ************  AmigoSpace Social Network ************
//        ****************************************************
//        Username: admin
//        ****************************************************
//        User: admin
//        Admin Menu
//        ****************************************************
//        These are your options:
//        1. View user profile
//        2. View all profiles
//        3. Make new friends from the list
//        4. View user's friends list
//        5. Delete a profile
//        6. Add another profile
//        7. Logout
//        Your choice [1-7]: 6
//        ****************************************************
//        User: admin
//        Admin Menu > Add Another Profile
//        ****************************************************
//        Enter new user name to create a profile: Zagor
//        Zagor does not have a profile.
//        Enter Zagor's age [1-149]: 33
//
//        ******* All Profiles *******
//        Rade
//        Petar
//        Olga
//        Kumo
//        Kiku
//        Koi
//        ****************************
//        Enter user name from the list above: Petar
//        Passed in add Profile.
//        Added Zagor to your friends list successfully.
//        ****************************************************
//        User: admin
//        Admin Menu
//        ****************************************************
//        These are your options:
//        1. View user profile
//        2. View all profiles
//        3. Make new friends from the list
//        4. View user's friends list
//        5. Delete a profile
//        6. Add another profile
//        7. Logout
//        Your choice [1-7]: 7
//
//        ****************************************************
//        ************  AmigoSpace Social Network ************
//        ****************************************************
//        Username: x
//
//        Process finished with exit code 0
