import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KMeans {

    private final int k;
    private List<Iris> irises;
    private List<Iris> kCentroids;
    private Map<String, List<Iris>> cluster;
    private StringBuilder result;

    public KMeans(int k, List<Iris> irises) {
        this.k = k;
        this.irises = irises;
        setInitialCentroids(irises);
        cluster = new HashMap();
        result = new StringBuilder();
    }
    public void setInitialCentroids(List<Iris> ir){
        kCentroids = new ArrayList<>(ir.subList(0, k));
        int clusterLabel = k;
        for(Iris centroid : kCentroids){
            centroid.setGroup(String.valueOf(clusterLabel));
            clusterLabel--;
        }
    }
    public void doKmeans(List<Iris> ir){
        String group;
        boolean smthChanges = true;

        while (smthChanges) {
            smthChanges = false;
            for (Iris iris : ir) {
                group = getNearestKey(euclideanDistance(iris.getAtributes()));
                if (iris.getGroup()==null || !iris.getGroup().equals(group)) {
                    iris.setGroup(group);
                    smthChanges = true;
                }
                cluster.computeIfAbsent(iris.getGroup(), k -> new ArrayList<>()).add(iris);
            }setNewCentroids();
            if (smthChanges) {
                setNewCentroids();
                cluster.clear();
            }
        }displayGroups();
    }
    public void setNewCentroids(){
        kCentroids = new ArrayList<>();
        for (Map.Entry<String, List<Iris>> entry : cluster.entrySet()) {
            String group = entry.getKey();
            List<Iris> irisesInGroup = entry.getValue();
            double sepalLength = 0;
            double sepalWidth = 0;
            double petalLength = 0;
            double petalWidth = 0;
            for(Iris iris : irisesInGroup){
                  sepalLength += iris.getAtributes()[0];
                  sepalWidth += iris.getAtributes()[1];
                  petalLength += iris.getAtributes()[2];
                  petalWidth += iris.getAtributes()[3];
            }
            double[] centroid = {sepalLength/irisesInGroup.size(), sepalWidth/ irisesInGroup.size(), petalLength/ irisesInGroup.size(), petalWidth/irisesInGroup.size()};
            Iris cent = new Iris(centroid);
            cent.setGroup(group);
            kCentroids.add(cent);
        }

    }

    public Map<String, Double> euclideanDistance(double[] attributes){
        Map<String, Double> distances = new HashMap<>();
        for(Iris iris : kCentroids){
            double distance=0;
            for(int i = 0; i< attributes.length;i++){
                distance += Math.pow(iris.getAtributes()[i] - attributes[i],2);
                distances.put(iris.getGroup(), Math.sqrt(distance));
            }
        }return distances;
    }

   public String getNearestKey(Map<String, Double> distances){
       Double minValue = null;
       String minKey = null;
        for(Map.Entry<String, Double> entry : distances.entrySet()){
            if (minValue == null || entry.getValue().compareTo(minValue) < 0) {
                minKey = entry.getKey();
                minValue = entry.getValue();
            }
       }
    return minKey;}
    public void displayGroups() {
        for (Map.Entry<String, List<Iris>> entry : cluster.entrySet()) {
            String group = entry.getKey();
            List<Iris> irisesInGroup = entry.getValue();
            System.out.println("Cluster " + group + ":");
            for (Iris iris : irisesInGroup) {
                System.out.println(" - " + iris.toString());
            }
        }speciesCounter();
}
    public void speciesCounter() {
        double diversity;

        for (Map.Entry<String, List<Iris>> entry : cluster.entrySet()) {
            int setosa = 0;
            int virginica = 0;
            int versicolor = 0;

            String group = entry.getKey();
            int groupSize = entry.getValue().size();
            List<Iris> irisesInGroup = entry.getValue();

            for (Iris iris : irisesInGroup) {
                switch (iris.getAnswer()) {
                    case "Iris-virginica":
                        virginica++;
                        break;
                    case "Iris-versicolor":
                        versicolor++;
                        break;
                    case "Iris-setosa":
                        setosa++;
                        break;
                }
            }

            diversity = ((Math.exp(setosa) + Math.exp(virginica) + Math.exp(versicolor)) / (Math.exp(groupSize) - 2));
            result.append("Species difference in cluster " + group + ": " + diversity+"\n");

        }}
    public void doKmeansForNormalized(){
        System.out.println("\n=======\nNormalized: ");
        result.append("\n=====================\nNormalized data: \n");
        cluster = new HashMap();
        List<Iris> normalizedIrises = normalizedData();
        setInitialCentroids(normalizedIrises);
        doKmeans(normalizedIrises);
        System.out.println(result);
    }

    public List<Iris> normalizedData(){
        List<Iris> normalizedIrises = new ArrayList<>();
        for (Iris iris : irises) {
            double squareSum = 0;

                for (double val : iris.getAtributes()) {
                    squareSum += Math.pow(val, 2);
                }
                double length = Math.sqrt(squareSum);
                double[] normalizedVector = new double[iris.getAtributes().length];
                for(int i =0;i< normalizedVector.length;i++){
                    normalizedVector[i] = iris.getAtributes()[i]/length;
                }
                Iris iris1 = new Iris(normalizedVector);
                iris1.setAnswer(iris.getAnswer());
                normalizedIrises.add(iris1);
            }
            return normalizedIrises;
        }
}