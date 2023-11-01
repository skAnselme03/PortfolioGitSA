package com.example.projetfinalandanss;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    private static UserDB instance;
    private List<User> userList;

    // Constructeur
    private UserDB() {
        userList = new ArrayList<>();
        // Usagers prédéfinis
        userList.add(new User("Admin", "admin", "Administrateur", "t0ps3cr3t", 10001));
        userList.add(new User("Security", "guard", "Watcher Graves", "1nv1s1bl3", 10012));
        userList.add(new User("Security", "nightnight", "Gustave Knight", "devine", 10013));
        userList.add(new User("Visitor", "anonyme", "Anne Honyme", "password123", 12345));
        userList.add(new User("Visitor", "commander", "Jane Shepard", "citadel", 12254));
        userList.add(new User("Visitor", "camcam", "Camille Andriamamonjy", "TPfinal", 15051));
        userList.add(new User("Visitor", "steph", "Stéphanie Anselme", "XML2023", 19985));
    }

    // Instance de UserDB
    public static synchronized UserDB getInstance() {
        if (instance == null) {
            instance = new UserDB();
        }
        return instance;
    }

    // Méthodes CURD
    public List<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public User validateUser(String enteredUsername, String enteredPassword) {
        for (User user : userList) {
            if (user.getNomUsager().equals(enteredUsername) && user.getMotPasse().equals(enteredPassword)) {
                return user;
            }
        }
        return null;
    }

    public void deleteUser(int userId) {
        for (User user : userList) {
            if (user.getUserId() == userId) {
                userList.remove(user);
                break;
            }
        }
    }

    public void updateUser(User selectedUser) {
        // Récupère la liste des usagers
        List<User> userList = getUserList();

        // Cherche l'index à mettre à jour
        int index = -1;
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (user.getUserId() == selectedUser.getUserId()) {
                index = i;
                break;
            }
        }

        // Si l'utilisateur est trouvé, les informations sont modifiées
        if (index != -1) {
            userList.set(index, selectedUser);
        }
    }
}
