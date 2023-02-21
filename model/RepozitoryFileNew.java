package model;

import java.util.ArrayList;
import java.util.List;

public class RepozitoryFileNew implements Repository {

    ToyMapper mapper = new ToyMapper();
    FileOperation fileOperation;

    public RepozitoryFileNew(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<Toy> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<Toy> toys = new ArrayList<>();
        for (String line : lines) {
            toys.add(mapper.map(line));
        }
        return toys;
    }

    @Override
    public String CreateUser(Toy toy) {
        List<Toy> toys = getAllUsers();
        int max = 0;
        for (Toy item : toys) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        toy.setId(id);
        toys.add(toy);
        saveUsers(toys);
        return id;
    }

    void saveUsers(List<Toy> toys) {
        List<String> lines = new ArrayList<>();
        for (Toy item : toys) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }

    @Override
    public Toy updateUser(Toy toy) throws Exception {
        List<Toy> toys = getAllUsers();
        Toy foundedToy = findUserById(toys, toy.getId());
        foundedToy.setWeight(toy.getWeight());
        saveUsers(toys);
        return foundedToy;
    }

    public void updateCount(Toy toy) throws Exception {
        List<Toy> toys = getAllUsers();
        Toy foundedToy = findUserById(toys, toy.getId());
        foundedToy.setCount(Integer. toString(Integer.parseInt(foundedToy.getCount()) - 1));
        saveUsers(toys);
        // return foundedToy;
    }

    private Toy findUserById(List<Toy> toys, String toyId) throws Exception {
        for (Toy toy : toys) {
            if (toy.getId().equals(toyId)) {
                return toy;
            }
        }
        throw new Exception("Toy not found");
    }

    @Override
    public Toy readUser(String toyId) throws Exception {
        List<Toy> toys = getAllUsers();
        return findUserById(toys, toyId);
    }

    @Override
    public void deleteUser(String deleteId) throws Exception {
        List<Toy> toys = getAllUsers();
        Toy deleteToy = findUserById(toys, deleteId);
        toys.remove(deleteToy);
        saveUsers(toys);
    }


}
