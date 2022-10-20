public class ImageClassifier {

    // Creates a feature vector (1D array) from the given picture.
    public static double[] extractFeatures(Picture picture) {

        //
        int col = picture.width();
        int row = picture.height();

        int counter = 0;
        double[] extract = new double[col * row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                extract[counter] = picture.get(j, i).getRed();
                // System.out.println(i + " " + j);
                counter++;
            }
        }
        return extract;
    }

    // See below.
    public static void main(String[] args) {
        In inn = new In(args[0]);
        int m = inn.readInt(); // number of classes
        int width = inn.readInt(); // width
        int height = inn.readInt(); // height

        MultiPerceptron mp = new MultiPerceptron(m, height * width);

        // Training Multi-Perceptron
        String image;
        int label;
        Picture picture;
        while (!inn.isEmpty()) {
            image = inn.readString();
            label = inn.readInt();
            // outt.println(image + " " + label);
            picture = new Picture(image);
            mp.trainMulti(extractFeatures(picture), label);
        }

        // Testing Multi-Perceptron
        In inn_testing = new In(args[1]);
        int m_ts = inn_testing.readInt();
        int width_ts = inn_testing.readInt();
        int height_ts = inn_testing.readInt();

        String image_ts;
        int label_ts;
        Picture picture_ts;
        int counter = 0;
        int kounter = 0;
        while (!inn_testing.isEmpty()) {
            image_ts = inn_testing.readString();
            label_ts = inn_testing.readInt();
            // System.out.println(image_ts + " " + label_ts);
            picture = new Picture(image_ts);
            int prediction = mp.predictMulti(extractFeatures(picture));
            if (prediction != label_ts) {
                System.out.println(image_ts + ", label = " + label_ts
                                           + ", predict = " + prediction);
                counter++;
            }
            kounter++;
        }
        System.out.println("test error rate = " + 1.0 * counter / kounter);
    }


}
