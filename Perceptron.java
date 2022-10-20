public class Perceptron {
    private double[] weight; // an array of integers
    private int n; // number of inputs

    // Creates a perceptron with n inputs. It should create an array
    // of n weights and initialize them all to 0.
    public Perceptron(int n) {
        this.n = n;
        weight = new double[n];
    }

    // Returns the number of inputs n.
    public int numberOfInputs() {
        return (weight.length);
    }

    // Returns the weighted sum of the weight vector and x.
    public double weightedSum(double[] x) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += x[i] * weight[i];
        }
        return sum;
    }

    // Predicts the label (+1 or -1) of input x. It returns +1
    // if the weighted sum is positive and -1 if it is negative (or zero).
    public int predict(double[] x) {
        if (weightedSum(x) > 0) {
            return 1;
        }
        else {
            return -1;
        }
    }

    // Trains this perceptron on the labeled (+1 or -1) input x.
    // The weights vector is updated accordingly.
    public void train(double[] x, int label) {
        if (predict(x) == label) {
        }
        else if ((label == -1) && (predict(x) == 1)) { // false positive case
            for (int i = 0; i < n; i++) {
                weight[i] -= x[i];
            }
        }
        else { // false negative case
            for (int i = 0; i < n; i++) {
                weight[i] += x[i];
            }
        }
    }

    // Returns a String representation of the weight vector, with the
    // individual weights separated by commas and enclosed in parentheses.
    // Example: (2.0, 1.0, -1.0, 5.0, 3.0)
    public String toString() {
        String str = "(";
        for (int i = 0; i < n - 1; i++) {
            str += weight[i];
            str += ", ";
        }
        str += weight[n - 1];
        str += ")";
        return str;
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        int n = 3;

        double[] training1 = { 3.0, 4.0, 5.0 };  // yes
        double[] training2 = { 2.0, 0.0, -2.0 };  // no
        double[] training3 = { -2.0, 0.0, 2.0 };  // yes
        double[] training4 = { 5.0, 4.0, 3.0 };  // no

        Perceptron perceptron = new Perceptron(n);
        StdOut.println(perceptron);
        perceptron.train(training1, +1);
        StdOut.println(perceptron);
        perceptron.train(training2, -1);
        StdOut.println(perceptron);
        perceptron.train(training3, +1);
        StdOut.println(perceptron);
        perceptron.train(training4, -1);
        StdOut.println(perceptron);
    }
}
