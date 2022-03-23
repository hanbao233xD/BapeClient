package mc.bape.Manager;

import mc.bape.Manager.ConfigManager;
import mc.bape.Manager.FileManager;
import mc.bape.Manager.Manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FriendManager {
    private static HashMap friends;

    public static boolean isFriend(String name) {
        return friends.containsKey(name);
    }

    public static String getAlias(Object friends2) {
        return (String) friends.get(friends2);
    }

    public static HashMap getFriends() {
        return friends;
    }

    public static HashMap access$0() {
        return friends;
    }

    public static void init() {
        friends = new HashMap();
        List frriends = ConfigManager.read("Friends.cfg");
        Iterator var3 = frriends.iterator();

        while (var3.hasNext()) {
            String v = (String) var3.next();
            if (v.contains(":")) {
                String name = v.split(":")[0];
                String alias = v.split(":")[1];
                friends.put(name, alias);
            } else {
                friends.put(v, v);
            }
        }
    }
}