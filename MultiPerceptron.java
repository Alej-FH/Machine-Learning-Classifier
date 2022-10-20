public class MultiPerceptron {
    private int m; // number of classes
    private int n; // number of inputs
    private Perceptron[] perceptron; // number of perceptrons

    // Creates a multi-perceptron object with m classes and n inputs.
    // It creates an array of m perceptrons, each with n inputs.
    public MultiPerceptron(int m, int n) {
        this.m = m;
        this.n = n;
        perceptron = new Perceptron[m];
        for (int i = 0; i < m; i++) {
            perceptron[i] = new Perceptron(n);
        }
    }

    // Returns the number of classes m.
    public int numberOfClasses() {
        return m;
    }

    // Returns the number of inputs n (length of the feature vector).
    public int numberOfInputs() {
        return n;
    }


    // Returns the predicted label (between 0 and m-1) for the given input.
    public int predictMulti(double[] x) {
        double max = Double.NEGATIVE_INFINITY;
        int returnable = 0;
        for (int i = 0; i < m; i++) {
            if (perceptron[i].weightedSum(x) > max) {
                returnable = i;
                max = perceptron[i].weightedSum(x);
            }
        }
        return returnable;
    }

    // Trains this multi-perceptron on the labeled (between 0 and m-1) input.
    public void trainMulti(double[] x, int label) {
        for (int i = 0; i < m; i++) { // this loop is right
            if (label == i) {
                perceptron[i].train(x, 1);
            }
            else {
                perceptron[i].train(x, -1);
            }

            // Few Questions:
            // 1. This code does not use predictMulti
            // 2. This omits the pointer to
            // train perceptron i on input vector x with label +1
            // train the other m - 1 perceptrons on input vector x with label -1

        }

    }

    // Returns a String representation of this MultiPerceptron, with
    // the string representations of the perceptrons separated by commas
    // and enclosed in parentheses.
    // Example with m = 2 and n = 3: ((2.0, 0.0, -2.0), (3.0, 4.0, 5.0))
    public String toString() {
        String str = "(";
        for (int i = 0; i < m - 1; i++) {
            str += perceptron[i].toString();
            str += ", ";
        }
        str += perceptron[m - 1].toString();
        str += ")";
        return str;
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        int m = 2;
        int n = 3;

        double[] training1 = { 3.0, 4.0, 5.0 };  // class 1
        double[] training2 = { 2.0, 0.0, -2.0 };  // class 0
        double[] training3 = { -2.0, 0.0, 2.0 };  // class 1
        double[] training4 = { 5.0, 4.0, 3.0 };  // class 0

        MultiPerceptron perceptron = new MultiPerceptron(m, n);
        StdOut.println(perceptron);
        perceptron.trainMulti(training1, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training2, 0);
        StdOut.println(perceptron);
        perceptron.trainMulti(training3, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training4, 0);
        StdOut.println(perceptron);
    }
}
