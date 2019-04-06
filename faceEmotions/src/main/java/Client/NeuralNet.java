package Client;

/**
 * Created by klevis.ramo on 11/27/2017.
 */

import org.apache.spark.ml.classification.MultilayerPerceptronClassificationModel;
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier;
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Client.IdxReader;
import Client.LabeledImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class NeuralNet {

    private final static Logger LOGGER = LoggerFactory.getLogger(NeuralNet.class);

    private SparkSession sparkSession;
    private MultilayerPerceptronClassificationModel model;

    public void init() {
        initSparkSession();
//        if (model == null) {
//            LOGGER.info("Loading the Neural Network from saved model ... ");
//            model = MultilayerPerceptronClassificationModel.load("resources/nnTrainedModels/ModelWith60000");
//            LOGGER.info("Loading from saved model is done");
//        }
    }

    public void train(Integer trainData, Integer testFieldValue) {

        initSparkSession();

        List<LabeledImage> labeledImages = IdxReader.loadData(trainData);
        List<LabeledImage> testLabeledImages = IdxReader.loadTestData(testFieldValue);

        Dataset<Row> train = sparkSession.createDataFrame(labeledImages, LabeledImage.class).checkpoint();
        Dataset<Row> test = sparkSession.createDataFrame(testLabeledImages, LabeledImage.class).checkpoint();
        System.out.print("Test labels are " +test);

        int[] layers = new int[]{784, 128, 64, 3};

        MultilayerPerceptronClassifier trainer = new MultilayerPerceptronClassifier()
                .setLayers(layers)
                .setBlockSize(100)
                .setSeed(1234L)
                .setMaxIter(100);

        model = trainer.fit(train);

        evalOnTest(test);
        evalOnTest(train);
    }

    private void evalOnTest(Dataset<Row> test) {
        Dataset<Row> result = model.transform(test);
        Dataset<Row> predictionAndLabels = result.select("prediction", "label");
        MulticlassClassificationEvaluator evaluator = new MulticlassClassificationEvaluator()
                .setMetricName("accuracy");
        System.out.println(evaluator.evaluate(predictionAndLabels));
        LOGGER.info("Test set accuracy = " + evaluator.evaluate(predictionAndLabels));
    }

    private void initSparkSession() {
        if (sparkSession == null) {

            sparkSession = SparkSession.builder()
                    .master("local[*]")
                    .appName("Digit Recognizer")
                    .getOrCreate();
        }

        sparkSession.sparkContext().setCheckpointDir("checkPoint");
    }

    public LabeledImage predict(LabeledImage labeledImage) {
        double predict = model.predict(labeledImage.getFeatures());
        labeledImage.setLabel(predict);
        return labeledImage;
    }

    private static double[] transformImageToOneDimensionalVector(BufferedImage img) {

        double[] imageGray = new double[28 * 28];
        int w = img.getWidth();
        int h = img.getHeight();
        int index = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color color = new Color(img.getRGB(j, i), true);
                int red = (color.getRed());
                int green = (color.getGreen());
                int blue = (color.getBlue());
                double v = 255 - (red + green + blue) / 3d;
                imageGray[index] = v;
                index++;
            }
        }
        return imageGray;
    }

    public static void main(String[] args){
        System.out.println("YESSSS");
        NeuralNet neuralNetwork = new NeuralNet();
        //neuralNetwork.init();
        neuralNetwork.train(290,3);
        try {
            File image = new File("/Users/sanaydevi/Desktop/hciai/project-03/faceEmotions/src/main/resources/myGood.png");
            BufferedImage img = ImageIO.read(image);
            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            double[] scaledPixels = transformImageToOneDimensionalVector(bimage);
            LabeledImage labeledImage = new LabeledImage(0, scaledPixels);
            LabeledImage predict = neuralNetwork.predict(labeledImage);
            neuralNetwork.predict(predict);
            System.out.println((int) predict.getLabel());
        }
        catch (IOException ie){
            System.out.println(ie);
        }
//        LabeledImage predict = neuralNetwork.predict(labeledImage);
//        neuralNetwork.predict()

    }


}

