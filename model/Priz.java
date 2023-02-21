package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Priz {
    FileOperation fileOperation;
    FileOperationImpl fileOperationImpl;
    Repository repository;
    RepozitoryFileNew repozitoryFileNew;
    ToyMapper toyMapper;

    public PriorityQueue<Toy> putToy() {
        List<Toy> toys = repository.getAllUsers();
        List<Toy> temp = new ArrayList<>();
        PriorityQueue<Toy> prizes = new PriorityQueue<>();
        for (Toy toy : toys) {
            int count = Integer.parseInt(toy.getWeight())/10; 
                for (int i = 0; i < count; i++) {
                    temp.add(toy);
                }
            }
        if (temp.size() < 10) {
            int correct = 10 - temp.size();
            Random random = new Random();
            for (int i = 0; i < correct; i++){
                int randomIndex = random.nextInt(temp.size());
                temp.add(temp.get(randomIndex));
            }
        }
        Collections.shuffle(temp);
        for (Toy toy : temp) {
            prizes.add(toy);
        }
        return prizes;
    }

    public void getToy(PriorityQueue<Toy> prizes) throws Exception {
        Toy toysPriz = prizes.poll();
        String line = toyMapper.map(toysPriz);
        fileOperationImpl.savePriz(line);
        repozitoryFileNew.updateCount(toysPriz);
    }
}
