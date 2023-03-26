package edu.ucalgary.ensf409;

/** 
 * @author Issam Akhtar, Yousef Hammad, Bilal Pasha
 * @version 1.9
 * @since 1.0
 * 
 * The Hamper.java class is responsible for creating an algorithm that is as efficient as possible. The goal is to reach the overall needs of the family (in terms of protein, 
 * fruits/veggies, calories, whole grain, and other) without wasting any food. Therefore, it is necessary to ensure the overall food contents are greater than the 
 * needs while also not wasting any food. Our goal was to create an algorithm that is based around the protein, fruits/veggies, whole grain and other contents while taking into
 * account the calories through these alogirthms. This is because all food types have calories, but not every food contains other (for example).
*/
import java.util.*;

public class Hamper {
    private Family family;
    private double proCal;
    private double other;
    private double fvCal;
    private double wgCal;
    private double calories;

    private double minCal;
    private double minPro;
    private double minOther;
    private double minFV;
    private double minWGCal;

    private double totalFV;
    private double totalCal;
    private double totalOth;
    private double totalPro;
    private double totalWG;

    private double difference;
    private HashMap<Integer, Food> foodHash = new HashMap<Integer, Food>();
    private Set<Integer> plusSet = new HashSet<Integer>();
    private Set<Integer> negSet = new HashSet<Integer>();
    private Set<Integer> totalSet = new HashSet<Integer>();

    public Hamper(Family family, HashMap<Integer, Food> foodMap) {
        this.family = family;
        this.foodHash = foodMap;
        this.totalCal = getTotalCal() * 7; // multiplied by 7 to get weekly amounts
        this.totalFV = getTotalFV() * 7;
        this.totalOth = getTotalOth() * 7;
        this.totalPro = getTotalPro() * 7;
        this.totalWG = getTotalWG() * 7;
        createMin();
    }

    public Set<Integer> hamperAlgorithm() {
        fvCalAlgorithm();
        othCalAlgorithm();
        wgCalAlgorithm();
        proCalAlgorithm();
        listVerification();
        filterList();
        finalSet();
        return totalSet;
    }

    public Set<Integer> getTotalSet() {
        return this.totalSet;
    }

    public HashMap<Integer, Food> getFoodHash() {
        return this.foodHash;
    }

    // fruits and veggie algorithm
    public void fvCalAlgorithm() {
        for (int percent = 100; percent > 0; percent--) {
            for (Map.Entry<Integer, Food> iterEnt : foodHash.entrySet()) {
                if (iterEnt.getValue().getFvContent() >= percent && fvCal < totalFV
                        && totalSet.contains(iterEnt.getKey()) == false) {
                    calories += iterEnt.getValue().getCalories();
                    proCal += iterEnt.getValue().getProteinContentCal();
                    other += iterEnt.getValue().getOtherContentCal();
                    fvCal += iterEnt.getValue().getFvContentCal();
                    wgCal += iterEnt.getValue().getGrainContentCal();
                    difference = Math.abs(fvCal - totalFV);
                    if (difference > minFV && fvCal > totalFV) {
                        calories -= iterEnt.getValue().getCalories();
                        proCal -= iterEnt.getValue().getProteinContentCal();
                        other -= iterEnt.getValue().getOtherContentCal();
                        fvCal -= iterEnt.getValue().getFvContentCal();
                        wgCal -= iterEnt.getValue().getGrainContentCal();
                    } else {
                        totalSet.add(iterEnt.getKey());
                    }
                }
            }
        }
    }

    // other algorithm
    public void othCalAlgorithm() {
        for (int percent = 100; percent > 0; percent--) {
            for (Map.Entry<Integer, Food> iterEnt : foodHash.entrySet()) {
                if (iterEnt.getValue().getOtherContent() >= percent && other < totalOth
                        && totalSet.contains(iterEnt.getKey()) == false) {
                    calories += iterEnt.getValue().getCalories();
                    proCal += iterEnt.getValue().getProteinContentCal();
                    other += iterEnt.getValue().getOtherContentCal();
                    fvCal += iterEnt.getValue().getFvContentCal();
                    wgCal += iterEnt.getValue().getGrainContentCal();
                    difference = Math.abs(other - totalOth);
                    if (difference > minOther && other > totalOth) {
                        calories -= iterEnt.getValue().getCalories();
                        proCal -= iterEnt.getValue().getProteinContentCal();
                        other -= iterEnt.getValue().getOtherContentCal();
                        fvCal -= iterEnt.getValue().getFvContentCal();
                        wgCal -= iterEnt.getValue().getGrainContentCal();
                    } else {
                        totalSet.add(iterEnt.getKey());
                    }
                }
            }
        }
    }

    // whole grain algorithm
    public void wgCalAlgorithm() {
        for (int percent = 100; percent > 0; percent--) {
            for (Map.Entry<Integer, Food> iterEnt : foodHash.entrySet()) {
                if (iterEnt.getValue().getGrainContent() >= percent && wgCal < totalWG
                        && totalSet.contains(iterEnt.getKey()) == false) {
                    calories += iterEnt.getValue().getCalories();
                    proCal += iterEnt.getValue().getProteinContentCal();
                    other += iterEnt.getValue().getOtherContentCal();
                    fvCal += iterEnt.getValue().getFvContentCal();
                    wgCal += iterEnt.getValue().getGrainContentCal();
                    difference = Math.abs(wgCal - totalWG);
                    if (difference > minWGCal && wgCal > totalWG) {
                        calories -= iterEnt.getValue().getCalories();
                        proCal -= iterEnt.getValue().getProteinContentCal();
                        other -= iterEnt.getValue().getOtherContentCal();
                        fvCal -= iterEnt.getValue().getFvContentCal();
                        wgCal -= iterEnt.getValue().getGrainContentCal();
                    } else {
                        totalSet.add(iterEnt.getKey());
                    }
                }
            }
        }
    }

    // protein algorithm
    public void proCalAlgorithm() {
        for (int percent = 100; percent > 0; percent--) {
            for (Map.Entry<Integer, Food> iterEnt : foodHash.entrySet()) {
                if (iterEnt.getValue().getProteinContent() >= percent && proCal < totalPro
                        && totalSet.contains(iterEnt.getKey()) == false) {
                    calories += iterEnt.getValue().getCalories();
                    proCal += iterEnt.getValue().getProteinContentCal();
                    other += iterEnt.getValue().getOtherContentCal();
                    fvCal += iterEnt.getValue().getFvContentCal();
                    wgCal += iterEnt.getValue().getGrainContentCal();
                    difference = Math.abs(proCal - totalPro);
                    if (difference > minPro && proCal > totalPro) {
                        calories -= iterEnt.getValue().getCalories();
                        proCal -= iterEnt.getValue().getProteinContentCal();
                        other -= iterEnt.getValue().getOtherContentCal();
                        fvCal -= iterEnt.getValue().getFvContentCal();
                        wgCal -= iterEnt.getValue().getGrainContentCal();
                    } else {
                        totalSet.add(iterEnt.getKey());
                    }
                }
            }
        }
    }

    public void createMin() {
        minCal = 50000000;
        minPro = 50000000;
        minOther = 50000000;
        minFV = 50000000;
        minWGCal = 50000000;

        for (Map.Entry<Integer, Food> iterEnt : foodHash.entrySet()) {
            if (minCal > iterEnt.getValue().getCalories() && iterEnt.getValue().getCalories() != 0
                    && totalSet.contains(iterEnt.getKey()) == false) {
                minCal = iterEnt.getValue().getCalories();
            }
            if (minPro > iterEnt.getValue().getProteinContentCal() && iterEnt.getValue().getProteinContentCal() != 0
                    && totalSet.contains(iterEnt.getKey()) == false) {
                minPro = iterEnt.getValue().getProteinContentCal();
            }
            if (minOther > iterEnt.getValue().getOtherContentCal() && iterEnt.getValue().getOtherContentCal() != 0
                    && totalSet.contains(iterEnt.getKey()) == false) {
                minOther = iterEnt.getValue().getOtherContentCal();
            }
            if (minFV > iterEnt.getValue().getFvContentCal() && iterEnt.getValue().getFvContentCal() != 0
                    && totalSet.contains(iterEnt.getKey()) == false) {
                minFV = iterEnt.getValue().getFvContentCal();
            }
            if (minWGCal > iterEnt.getValue().getGrainContentCal() && iterEnt.getValue().getGrainContentCal() != 0
                    && totalSet.contains(iterEnt.getKey()) == false) {
                minWGCal = iterEnt.getValue().getGrainContentCal();
            }
        }
    }

    public void finalSet() {
        ArrayList<Integer> finalList = new ArrayList<Integer>(totalSet);
        calories = 0;
        other = 0;
        fvCal = 0;
        wgCal = 0;
        proCal = 0;
        for (int i = 0; i < finalList.size(); i++) {
            calories += foodHash.get(finalList.get(i)).getCalories();
            proCal += foodHash.get(finalList.get(i)).getProteinContentCal();
            other += foodHash.get(finalList.get(i)).getOtherContentCal();
            fvCal += foodHash.get(finalList.get(i)).getFvContentCal();
            wgCal += foodHash.get(finalList.get(i)).getGrainContentCal();
        }
    }

    public void listVerification() {
        for (Map.Entry<Integer, Food> iterEnt : foodHash.entrySet()) {
            if ((other < totalOth || fvCal < totalFV || wgCal < totalWG
                    || proCal < totalPro) && totalSet.contains(iterEnt.getKey()) == false) {
                calories += iterEnt.getValue().getCalories();
                proCal += iterEnt.getValue().getProteinContentCal();
                other += iterEnt.getValue().getOtherContentCal();
                fvCal += iterEnt.getValue().getFvContentCal();
                wgCal += iterEnt.getValue().getGrainContentCal();
                totalSet.add(iterEnt.getKey());
            }
        }
    }

    // Responsible for taking care of food waste.
    // It will filter out any foods that will bring us closer to the food needs without going below
    public void filterList() {
        List<Integer> listFilter = new ArrayList<Integer>(totalSet);
        for (int i = 0; i < listFilter.size(); i++) {
            if (other > totalOth || fvCal > totalFV || wgCal > totalWG || proCal > totalPro) {
                calories -= foodHash.get(listFilter.get(i)).getCalories();
                proCal -= foodHash.get(listFilter.get(i)).getProteinContentCal();
                other -= foodHash.get(listFilter.get(i)).getOtherContentCal();
                fvCal -= foodHash.get(listFilter.get(i)).getFvContentCal();
                wgCal -= foodHash.get(listFilter.get(i)).getGrainContentCal();
                negSet.add(listFilter.get(i));
                if (other < totalOth || fvCal < totalFV || wgCal < totalWG || proCal < totalPro) {
                    calories += foodHash.get(listFilter.get(i)).getCalories();
                    proCal += foodHash.get(listFilter.get(i)).getProteinContentCal();
                    other += foodHash.get(listFilter.get(i)).getOtherContentCal();
                    fvCal += foodHash.get(listFilter.get(i)).getFvContentCal();
                    wgCal += foodHash.get(listFilter.get(i)).getGrainContentCal();
                    plusSet.add(listFilter.get(i));
                }
            }
        }
        negSet.removeAll(plusSet);
        totalSet.removeAll(negSet);
    }

    private int getTotalCal() {
        Integer total = family.getFamily()
                .parallelStream()
                .reduce(0, (sum, s) -> sum += s.getCalories(), (sum1, sum2) -> sum1 + sum2);
        return total;
    }

    private Double getTotalOth() {
        Double total = family.getFamily()
                .parallelStream()
                .reduce(0.0, (sum, s) -> sum += s.getOtherPercent() * s.getCalories(),
                        (sum1, sum2) -> sum1 + sum2);
        return total;
    }

    private Double getTotalPro() {
        Double total = family.getFamily()
                .parallelStream()
                .reduce(0.0, (sum, s) -> sum += s.getProteinPercent() * s.getCalories(),
                        (sum1, sum2) -> sum1 + sum2);
        return total;
    }

    private Double getTotalFV() {
        Double total = family.getFamily()
                .parallelStream()
                .reduce(0.0, (sum, s) -> sum += s.getFruitVeggiePercent() * s.getCalories(),
                        (sum1, sum2) -> sum1 + sum2);
        return total;
    }

    private Double getTotalWG() {
        Double total = family.getFamily()
                .parallelStream()
                .reduce(0.0, (sum, s) -> sum += s.getWholeGrainsPercent() * s.getCalories(),
                        (sum1, sum2) -> sum1 + sum2);
        return total;
    }
}