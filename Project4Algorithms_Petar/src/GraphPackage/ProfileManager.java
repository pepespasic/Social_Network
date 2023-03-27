package GraphPackage;

//ProfileManager should be able to:
//        OK Create an undirected graph
//        OK Add a profile to an undirected graph
//        OK Connect two vertexes on the graph (create a friendship between profiles)
//        OK Display all of the profiles
//        OK Display each profile's information and friends (through a Breadth First Traversal)

import ADTPackage.DictionaryInterface;
import ADTPackage.LinkedQueue;
import ADTPackage.QueueInterface;
import ADTPackage.UnsortedLinkedDictionary;

import javax.sound.midi.Soundbank;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedMap;

enum Role { ADMIN, USER }

public class ProfileManager {
    DictionaryInterface<String, Role> users;
    DictionaryInterface<String, Profile> profiles;
    UndirectedGraph<Profile> mySocialNetwork;
    private String currentUser;


    public ProfileManager() {
        mySocialNetwork = new UndirectedGraph<>();
        users = new UnsortedLinkedDictionary<>();
        users.add("admin", Role.ADMIN);
        profiles = new UnsortedLinkedDictionary<>();
    }

    /**
     * Returns currentUser.
     * @return currentUser
     */
    public String getCurrentUser() {
        return currentUser;
    }
    /**
     * Sets currentUser
     */
    public void setCurrentUser(String user) {
        currentUser = user;
    }

    /**
     * Checks if profile exists before adding.
     * @param profile profile
     * @return true if profile added successfully
     */
    public boolean addProfile(Profile profile) {
        if(!users.contains(profile.getName())) {
            users.add(profile.getName(), Role.USER);
            profiles.add(profile.getName(), profile);
            return mySocialNetwork.addVertex(profile);
        }
        else return false;
    }

    /**
     * Checks if user exists and makes sure the role is user.
     * @param user profile
     * @return true if user exists and role is user
     */
    public boolean isUser(String user) {
        return users.contains(user) && users.getValue(user)==Role.USER;
    }

    /**
     * Connect two vertices or friends' profiles
     * @param profile1 start vertex
     * @param profile2 end vertex
     */
    public void makeFriends(Profile profile1, Profile profile2) {
        if (!profile1.equals(profile2) && isUser(profile1.getName()) && isUser(profile2.getName())) {
            // Update profiles before passing them to the graph.
            profiles.getValue(profile1.getName()).addFriend(profile2.getName());
            profiles.getValue(profile2.getName()).addFriend(profile1.getName());
            if (!mySocialNetwork.addEdge(profile1, profile2)) {
                profiles.getValue(profile1.getName()).removeFriend(profile2.getName());
                profiles.getValue(profile2.getName()).removeFriend(profile1.getName());
            }
        }
        else {
            System.out.println("Cannot make friends between " + profile1.getName() + " and " + profile2.getName() + "!");
        }
    }

    /**
     * Displays all names
     * @param userProfile username
     */
    public void displayAll(Profile userProfile) {
        QueueInterface<Profile> allProfiles;
        allProfiles = mySocialNetwork.getBreadthFirstTraversal(userProfile);
        if (!allProfiles.isEmpty()) {
            System.out.println("\n******* All Profiles *******");
        }
        else {
            System.out.println("\n******** No profiles found! ********");
        }
        while(!allProfiles.isEmpty()) {
            System.out.println(allProfiles.dequeue().getName());
        }
        System.out.println("****************************");
    }

    /**
     * Displays all usernames.
     */
    public void displayAll() {
        Iterator<Profile> iter = profiles.getValueIterator();
        if (iter.hasNext()) {
            displayAll(iter.next());
        }
    }

    /**
     * Displays all profiles.
     */
    public void displayAllProfiles() {
        Iterator<Profile> iter = profiles.getValueIterator();
        if (iter.hasNext()) {
            displayAllProfiles(iter.next());
        }
    }

    /**
     * Displays all profiles through breath first search traversal.
     * @param userProfile username
     */
    public void displayAllProfiles(Profile userProfile) {
        QueueInterface<Profile> allProfiles;

        allProfiles = mySocialNetwork.getBreadthFirstTraversal(userProfile);
        if (!allProfiles.isEmpty()) {
            System.out.println("\n******* Profiles Info ******\n");
        }
        else {
            System.out.println("\n**** No profiles found! ****");
        }
        String currentProfile;
        while(!allProfiles.isEmpty()) {
            System.out.println(profiles.getValue(allProfiles.dequeue().getName()));
        }
        System.out.println("****************************");
    }

    /**
     * Gets user input and calls the menu method.
     */
    public void start() {
        do {
            String user = "";
            System.out.println();
            line("*", 52);
            System.out.println("************  AmigoSpace Social Network ************");
            line("*", 52);
            Scanner input = new Scanner(System.in);
            do{
                System.out.print("Username: ");
                user = input.next();
                if (user.equals("x")) break;
                if(!validUserName(user) ) {
                    System.out.println("User name can only contain letters or numbers.");
                    System.out.println("The first character has to be a letter.");
                    System.out.println("Enter x to exit.");
                }
                else {
                    break;
                }
            }
            while (true);

            if (user.equals("x")) break;
            else if(isUser(user)) {
                setCurrentUser(user);
                profiles.getValue(getCurrentUser()).setStatus(Status.ONLINE);
                userMenu();
            } // admin user
            else if (users.getValue(user) == Role.ADMIN) {
                setCurrentUser(user);
                adminMenu();
            }
            else { // New user
                System.out.println(user + " does not have a profile.");
                System.out.println("Do you want to create a profile? [y/n]: ");
                String choice = input.next();
                if(choice.equals("y")) {
                    displayMenu("User Menu > Add New Profile");
                    String age = "";
                    do {
                        System.out.print("Enter " + user + "'s age: ");
                        age = input.next();
                    }
                    while(!validAge(age));
                    int ageInt = Integer.parseInt(age);

                    System.out.println("You must make at least one friend to join the network.");
                    displayAll();
                    System.out.print("Enter your friend's name from the list above: ");
                    String friend = getUserName();
                    // Add new profile
                    addProfile(new Profile(user, ageInt));
                    makeFriends(profiles.getValue(user), profiles.getValue(friend));
                    setCurrentUser(user);
                    profiles.getValue(getCurrentUser()).setStatus(Status.ONLINE);
                    userMenu();
                }
                else {
                    break;
                }
            }
        }
        while (true);
    }

    /**
     * Displays menus.
     * @param menuName name of the menu
     */
    public void displayMenu(String menuName) {
        int front = (52 - menuName.length())/2;
        line("*", 52);
        System.out.println(String.format("%52s", "User: " + getCurrentUser()));
        prefix(" ", front);
        System.out.println(menuName);
        line("*", 52);
    }

    /**
     * Gives user a list of options for the profile.
     */
    public void userMenu() {
        String option = "";
        Scanner input = new Scanner(System.in);
        do {
            displayMenu("Main Menu");
            System.out.println("These are your options:");
            System.out.println("  1. Modify profile ");
            System.out.println("  2. View all profiles & add a friend from the list ");
            System.out.println("  3. Add a friend ");
            System.out.println("  4. View your friend list ");
            System.out.println("  5. View your friend's friend list ");
            System.out.println("  6. Delete a profile ");
            System.out.println("  7. Add another profile ");
            System.out.println("  8. Switch the current user ");
            System.out.println("  9. Logout ");
            System.out.print("Your choice [1-9]: ");
            option = input.next();
            while (!validOption(option, "9")) {
                System.out.println("Invalid option please try again: ");
                System.out.print("Your choice [1-9]: ");
                option = input.next();
            }

            if (option.equals("1")) {
                displayMenu("User Menu > Modify Profile");
                modifyProfileMenu();
            }
            else if (option.equals("2")) {
                displayMenu("User Menu > View all Profiles & Add a Friend");
                displayAll();
                String friend;
                do {
                    System.out.print("Enter user name to make a friend: ");
                    friend = input.next();
                    // Get back to a parent menu.
                    if (friend.equals("x")) break;
                    else if(!isUser(friend)) {
                        System.out.println(friend + " does not have a profile.");
                    }
                    else if(currentUser.equals(friend)) {
                        System.out.println("You cannot add yourself as a friend.");
                    }
                    else if(profiles.getValue(currentUser).isFriend(friend)) {
                        System.out.println(friend + " is already your friend.");
                    }
                    else {
                        profiles.getValue(currentUser).addFriend(friend);
                        profiles.getValue(friend).addFriend(currentUser);
                        if(mySocialNetwork.addEdge(profiles.getValue(currentUser), profiles.getValue(friend))) {
                            System.out.println("Added " + friend + " to your friends list successfully.");
                        }
                        else {
                            profiles.getValue(currentUser).removeFriend(friend);
                            profiles.getValue(friend).removeFriend(currentUser);
                        }
                    }
                    System.out.println("Enter x to exit.");
                }
                while (true);
            }
            else if (option.equals("3")) {
                displayMenu("User Menu > Add a Friend");
                displayAll();
                // Ask user which friend to add.
                String friend;
                do {
                    System.out.print("Enter user name to make a friend: ");
                    friend = input.next();
                    // Get back to a parent menu.
                    if (friend.equals("x")) break;
                    else if(!isUser(friend)) {
                        System.out.println(friend + " does not have a profile.");
                    }
                    else if(currentUser.equals(friend)) {
                        System.out.println("You cannot add yourself as a friend.");
                    }
                    else if(profiles.getValue(currentUser).isFriend(friend)) {
                        System.out.println(friend + " is already your friend.");
                    }
                    else {
                        makeFriends(profiles.getValue(currentUser), profiles.getValue(friend));
                    }
                    System.out.println("Enter x to exit.");
                }
                while (true);
            }
            else if (option.equals("4")) {
                displayMenu("User Menu > View Your Friends List");
                System.out.println(currentUser + "'s " + profiles.getValue(currentUser).friendsList());
            }
            else if (option.equals("5")) {
                displayMenu("User Menu > View a Friend's Friends List");
                //System.out.println("My friends:");
                System.out.println(currentUser + "'s " + profiles.getValue(currentUser).friendsList());
                String friend;
                do {
                    System.out.print("Enter your friend name: ");
                    friend = input.next();
                    // Get back to a parent menu.
                    if (friend.equals("x")) break;
                    else if(!profiles.getValue(currentUser).isFriend(friend)) {
                        System.out.println(friend + " is not your friend.");
                    }
                    else {
                        System.out.println(friend + "'s " + profiles.getValue(friend).friendsList());
                    }
                    System.out.println("Enter x to exit.");
                }
                while (true);
            }
            else if (option.equals("6")) {
                displayMenu("User Menu > Delete Profile");
                deleteProfile(currentUser);
                displayAllProfiles(profiles.getValue(currentUser));
                displayAll(profiles.getValue(currentUser));
            }
            else if (option.equals("7")) {
                displayMenu("User Menu > Add Another Profile");
                String newUser;
                do {
                    do {
                        System.out.print("Enter new user name to create a profile: ");
                        newUser = input.next();
                        // Get back to a parent menu.
                        if (newUser.equals("x")) break;
                        else if(!validUserName(newUser)) {
                            System.out.println("User name can only contain letters or numbers.");
                            System.out.println("The fist character has to be a letter.");
                            System.out.println("Enter x to exit.");
                        }
                        else
                            break;
                    } while (true);

                    if (newUser.equals("x")) break;
                    else if(currentUser.equals(newUser)) {
                        System.out.println("You cannot add yourself as a friend.");
                    }
                    else if(!isUser(newUser)) {
                        System.out.println(newUser + " does not have a profile.");
                        String age = "0";
                        do {
                            System.out.print("Enter " + newUser + "'s age [1-149]: ");
                            age = input.next();
                        }
                        while(!validAge(age));
                        int ageInt = Integer.parseInt(age);
                        // Add new profile
                        addProfile(new Profile(newUser, ageInt));
                        // New user should have at least one friend.
                        makeFriends(profiles.getValue(currentUser), profiles.getValue(newUser));
                        System.out.println("Added " + newUser + " to your friends list successfully.");
                        break;
                    }
                    else {
                        System.out.println(newUser + " already has a profile.");
                        System.out.println("Enter x to exit.");
                    }
                }
                while (true);
            }
            else if (option.equals("8")) {
                displayMenu("User Menu > Switch the Current User");
                String newUser;
                do {
                    System.out.print("Enter new username: ");
                    newUser = input.next();
                    // Get back to a parent menu.
                    if (newUser.equals("x")) break;
                    else if (newUser.equals("admin")) {
                        System.out.println("You cannot switch to admin user before you logout.");
                    }
                    else if (currentUser.equals(newUser)) {
                        System.out.println("You cannot switch user to yourself.");
                    }
                    else if (isUser(newUser)) {
                        // Current user goes offline.
                        profiles.getValue(currentUser).setStatus(Status.OFFLINE);
                        // Set new current user.
                        setCurrentUser(newUser);
                        profiles.getValue(newUser).setStatus(Status.ONLINE);
                        break;
                    }
                    else {
                        System.out.println("User does not exist.");
                        System.out.println("Enter x to exit.");
                    }
                }
                while (true);
            }

        } while (!option.equals("9") && !option.equals("x"));
        if (isUser(currentUser)) {
            profiles.getValue(getCurrentUser()).setStatus(Status.OFFLINE);
        }
    }

    /**
     * Gives admin a list of options for the profile.
     */
    public void adminMenu() {
        String option = "";
        Scanner input = new Scanner(System.in);
        do {
            displayMenu("Admin Menu");
            System.out.println("These are your options:");
            System.out.println("  1. View user profile ");
            System.out.println("  2. View all profiles");
            System.out.println("  3. Make new friends from the list");
            System.out.println("  4. View user's friends list ");
            System.out.println("  5. Delete a profile ");
            System.out.println("  6. Add another profile ");
            System.out.println("  7. Logout ");
            System.out.print("Your choice [1-7]: ");
            option = input.next();
            while (!validOption(option, "7")) {
                System.out.println("Invalid option please try again: ");
                System.out.print("Your choice [1-7]: ");
                option = input.next();
            }

            if (option.equals("1")) {
                displayMenu("Admin Menu > View User Profile");
                displayAll();
                String user = getUserName();
                System.out.println(profiles.getValue(user).toString());
            }
            else if (option.equals("2")) {
                displayMenu("Admin Menu > View all Profiles");
                displayAllProfiles();
            }
            else if (option.equals("3")) {
                displayMenu("Admin Menu > Make New Friends");
                displayAll();
                String user1 = getUserName();
                String user2 = getUserName();
                makeFriends(profiles.getValue(user1), profiles.getValue(user2));
            }
            else if (option.equals("4")) {
                displayMenu("Admin Menu > View User Friends List");
                displayAll();
                String user = getUserName();
                System.out.println(user + "'s " + profiles.getValue(user).friendsList());
            }
            else if (option.equals("5")) {
                displayMenu("Admin Menu > Delete Profile");
                displayAll();
                String user = getUserName();
                deleteProfile(user);
                //Remove next 2 lines when finalized.
                displayAllProfiles();
                displayAll();
            }
            else if (option.equals("6")) {
                displayMenu("Admin Menu > Add Another Profile");
                String newUser;
                do {
                    do {
                        System.out.print("Enter new user name to create a profile: ");
                        newUser = input.next();
                        // Get back to a parent menu.
                        if (newUser.equals("x")) break;
                        else if(!validUserName(newUser)) {
                            System.out.println("User name can only contain letters or numbers.");
                            System.out.println("The fist character has to be a letter.");
                            System.out.println("Enter x to exit.");
                        }
                        else
                            break;
                    } while (true);

                    if (newUser.equals("x")) break;
                    else if(currentUser.equals(newUser)) {
                        System.out.println("You cannot add yourself as a friend.");
                    }
                    else if(!isUser(newUser)) {
                        System.out.println(newUser + " does not have a profile.");
                        String age = "0";
                        do {
                            System.out.print("Enter " + newUser + "'s age [1-149]: ");
                            age = input.next();
                        }
                        while(!validAge(age));
                        int ageInt = Integer.parseInt(age);
                        displayAll();
                        String user = getUserName();
                        // Add new profile
                        addProfile(new Profile(newUser, ageInt));
                        // New user should have at least one friend.
                        makeFriends(profiles.getValue(user), profiles.getValue(newUser));
                        System.out.println("Added " + newUser + " to your friends list successfully.");
                        break;
                    }
                    else {
                        System.out.println(newUser + " already has a profile.");
                        System.out.println("Enter x to exit.");
                    }
                }
                while (true);
            }
        } while (!option.equals("7") && !option.equals("x"));
        if (isUser(currentUser)) {
            profiles.getValue(getCurrentUser()).setStatus(Status.OFFLINE);
        }
    }


    /**
     * Gives a few options what the user can change on the profile.
     */
    public void modifyProfileMenu() {
        String option;
        Scanner input = new Scanner(System.in);
        Profile current = profiles.getValue(getCurrentUser());
        do {
            System.out.println("These are your options:");
            System.out.println("  1. Change age: " + current.getAge());
            System.out.println("  2. Change status: " + current.getStatus());
            System.out.println("  3. Exit ");
            System.out.print("Your choice [1-3]: ");
            option = input.next();
            while (!validOption(option, "3")) {
                System.out.println("Invalid option " + option + ", please try again: ");
                System.out.print("Your choice [1-3]: ");
                option = input.next();
            }

            if (option.equals("1")) {
                displayMenu("User Menu > Modify Profile > Change Age");
                String age;
                System.out.print("Enter your new age [1-149]: ");
                age = input.next();
                while (!validAge(age)) {
                    System.out.println("Invalid age " + age + ", please try again. ");
                    System.out.print("Enter your new age [1-149]: ");
                    age = input.next();
                }
                current.setAge(Integer.valueOf(age));
                System.out.println("Your new age: " + current.getAge());
            }
            else if (option.equals("2")) {
                displayMenu("User Menu > Modify Profile > Change Status");
                if (current.getStatus() == Status.ONLINE) {
                    current.setStatus(Status.OFFLINE);
                    System.out.println("New status: OFFLINE");
                }
                else {
                    current.setStatus(Status.ONLINE);
                    System.out.println("New status: ONLINE");
                }
            }
        } while (!option.equals("3") && !option.equals("x"));
    }

    /**
     * Get existing username.
     * @return user
     */
    public String getUserName() {
        String user;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Enter user name from the list above: ");
            user = input.next();
        }
        while (!isUser(user));
        return user;
    }

    /**
     * Removes list of user's friends and user from the friends list.
     * Run time is O(n)
     * @param user current user
     */
    public void deleteProfile(String user) {
        if (isUser(user)) {
            // First remove my name from my friends friends' list.
            String[] myFriends = profiles.getValue(user).friendsToString();
            for (int i = 0; i < myFriends.length; i++) {
                // Remove my name from friends profile.
                profiles.getValue(myFriends[i]).removeFriend(user);
            }
            // Then remove all my friends from my friends' list
            for (int i = 0; i < myFriends.length; i++) {
                profiles.getValue(user).removeFriend(myFriends[i]);
            }

//            QueueInterface<String> allProfileKeys;
//
//            while () {
//
//            }

        }
        else {
            System.out.println(user + " does not have a profile.");
        }
    }

    /**
     * Checks if the age is in range.
     * @param age user age
     * @return true if age is in range
     */
    public boolean validAge(String age) {
        if (age.length() >= 4) return false;
        else if (age.length() == 3) {
            char c1 = age.charAt(0);
            char c2 = age.charAt(1);
            char c3 = age.charAt(2);
            // Maximum age should be no more than 149
            if (c1 != '1') return false;
            else if (c2 < '0' || c2 > '4') return false;
            else if (c3 < '0' || c3 > '9') return false;
            else return true;
        }
        else if (age.length() == 2) {
            char c1 = age.charAt(0);
            char c2 = age.charAt(1);
            if (c1 < '1' || c1 > '9') return false;
            else if (c2 < '0' || c2 > '9') return false;
            else return true;
        }
        else if (age.length() == 1) {
            char c1 = age.charAt(0);
            if (c1 < '0' || c1 > '9') return false;
            else return true;
        }
        else return true;
    }

    public boolean validUserName(String userName) {
        if (userName.length() == 0) return false;
        // The first character has to be a letter.
        else if(userName.charAt(0) >= 'a' && userName.charAt(0) <= 'z' ||
                userName.charAt(0) >= 'A' && userName.charAt(0) <= 'Z') {
            // The remaining characters should be letters or numbers
            for (int i = 1; i < userName.length(); i++) {
                if (!Character.isLetterOrDigit(userName.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks if admin or user entered valid option.
     * @param option 1-9 for user and 1-7 for admin
     * @param max maximum number
     * @return true if option is valid
     */
    public boolean validOption(String option, String max) {
        if (option.equals("x")) return true;
        if (option.length() != max.length()) return false;
        if (option.length() == 1) {
            char c1 = option.charAt(0);
            if (c1 >= '1' && c1 <= max.charAt(0) ) {
                return true;
            }
            else {
                return false;
            }
        }
        if (option.length() == 2) {
            char c1 = option.charAt(0);
            char c2 = option.charAt(1);
            if (c1 >= '1' && c1 <= '9' && c2 >= '0' && c2 <= max.charAt(1)) {
                return true;
            }
            else {
                return false;
            }
        }
        else { // Unlikely to have more than 19 options.
            return false;
        }
    }

    /**
     * Prints out line for title of menu
     * Run time is O(n).
     * @param ch character
     * @param len number of times character is printed
     */
    public void line(String ch, int len) {
        String line = "";
        for(int i = 0; i < len; i++) line += ch;
        System.out.println(line);
    }

    /**
     * Prints character len times.
     * @param ch character
     * @param len number of times character is printed
     */
    public void prefix(String ch, int len) {
        String line = "";
        for(int i = 0; i < len; i++) line += ch;
        System.out.print(line);
    }
}
