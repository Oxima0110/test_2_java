package views;

import java.util.Scanner;

import controllers.Controller;
import model.Toy;

public class ViewToy {
    
    private Controller controller;

    public ViewToy(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("Введите команду:\n" +
                                "LIST - просмотр игрушек для розыгрыша\n" +
                                "CREATE - добавление новой игрушки\n" +
                               "UPDATE - изменение частоты выпадания\n" +
                               "DELETE - удаление игрушки\n" +
                               "PUT - розыгрыш призов\n" +
                               "GET - выдача приза\n" +
                               "EXIT - выход");
            try {
                com = Commands.valueOf(command);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Неопознанная команда");
            }
            if (com == Commands.EXIT)
                return;
            try {
                switch (com) {

                    case CREATE:
                        User user = setUser(false);
                        userController.saveUser(user);
                        break;
                    case LIST:
                        List<User> userList = userController.readUserList();
                        for (User item : userList) {
                            System.out.println(item);
                            System.out.println();
                        }
                        break;
                    case UPDATE:
                        User updateUser = setUser(true);
                        userController.updateUser(updateUser);
                        break;
                    case DELETE:
                        String deleteId = prompt("Идентификатор пользователя: ");
                        userController.deleteUser(deleteId);
                        break;
                    case PUT:

                    break;
                    case GET:

                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private Toy setUser(boolean forUpdate) {
        String idString = null;
        if (forUpdate) {
            idString = prompt("Идентификатор игрушки: ");
        }
        String name = prompt("Название: ");
        String count = prompt("Количество: ");
        String weight = prompt("Частота выпадения: ");
        if (forUpdate) {
            return new Toy(idString, name, count, weight);
        }
        return new Toy(name, count, weight);
    }
}
