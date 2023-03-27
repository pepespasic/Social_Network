package GraphPackage;

import ADTPackage.LinkedListWithIterator;

import java.util.ArrayList;

//Profile should be able to:
//        OK Create a profile with a picture (optional), name, status and a list of friendProfiles.
//        OK Feel free to add whatever you like here including Location, Relationship Status, Age, Occupation, Astrological Sign - whatever you want!
//        Retrieve the profile picture (optional)
//        OK Set and get the name of the profile
//        OK Set and get the status
//        OK Add a friend
//        OK Print out all the details of the profile including the list of friends.

enum Status { OFFLINE, ONLINE }

public class Profile {
    private String name;
    private int age;
    private Status status;
    private ArrayList<String> friendList;

    public Profile(String name, int age, Status status, ArrayList<String> friendList) {
        this.name = name;
        this.age  = age;
        this.status = status;
        this.friendList = friendList;
    }

    public Profile(String name, int age, ArrayList<String> friendList) {
        this(name, age, Status.ONLINE, friendList);
    }

    public Profile(String name, int age, Status status) {
        this(name, age, status, null);
    }

    public Profile(String name, Status status) {
        this(name, 0, status, null);
    }

    public Profile(String name, int age) {
        this(name, age, Status.ONLINE, null);
    }

    public Profile(String name) {
        this(name, 0, Status.ONLINE, null);
    }

    /**
     * Returns profile name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets profile name
     * @param newName new profile name
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Returns age
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age
     * @param newAge age of user
     */
    public void setAge(int newAge) {
        age = newAge;
    }

    /**
     * Returns status
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status
     * @param newStatus status of user
     */
    public void setStatus(Status newStatus) {
        status = newStatus;
    }

    /**
     * Returns friendList arrayList
     * @return friendList
     */
    public ArrayList<String> getFriendList() {
        return friendList;
    }

    public boolean addFriend(String friendName) {
        boolean addedFriend = false;
        if(friendList != null) {
            if (!isFriend(friendName)) {
                friendList.add(friendName);
                addedFriend = true;
            }
        }
        else {
            friendList = new ArrayList<>();
            friendList.add(friendName);
            addedFriend = true;
        }
        return addedFriend;
    }

    /**
     * Checks if friend name is in user's list
     * @param friendName username that is searched for
     * @return true if friend name is found
     */
    public boolean isFriend(String friendName) {
        boolean isFriend = false;
        if (friendList != null) {
            for (int i = 0; i < friendList.size(); i++) {
                if (friendName.equals(friendList.get(i))) {
                    isFriend = true;
                    break;
                }
            }
        }
        return isFriend;
    }

    /**
     * Removes friend from friendList.
     * @param friendName friendName
     */
    public void removeFriend(String friendName) {
        if (friendList.contains(friendName)) friendList.remove(friendName);
    }

    /**
     * Returns string array of friends.
     * @return friendsArray
     */
    public String[] friendsToString() {
        String[] friendsArray = new String[friendList.size()];
        for (int i = 0; i < friendList.size(); i++) {
            friendsArray[i] = friendList.get(i);
        }
        return friendsArray;
    }

    /**
     * Returns string representation of friendList.
     * @return str
     */
    public String friendsList(){
        String str = "";
        if (friendList != null) {
            str += "Friends: \n";
            for(int i = 0; i < friendList.size(); i++) {
                str += String.format("%5s. ", i + 1) + friendList.get(i) + "\n";
            }
        }
        else {
            str += "Friends: NONE\n";
        }
        return str;
    }

    /**
     * Returns string representation of profile.
     * @return str
     */
    public String toString() {
        String str = "========= Profile ==========\n";
        str += "Name:   " + name + "\n";
        str += "Age:    " + age  + "\n";
        str += "Status: " + status + "\n";
        str += friendsList();
        str += "============================\n";
        return str;
    }

}

