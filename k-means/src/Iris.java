import java.util.Arrays;

public class Iris {

    private double[] atributes;
    private String group;
    private String answer;

    public Iris(double[] atributes) {
        this.atributes = atributes;
    }


    public double[] getAtributes() {
        return atributes;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Iris{" +
                "atributes=" + Arrays.toString(atributes) +
                ", group='" + group + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    public String getAnswer() {
        return answer;
    }
}
