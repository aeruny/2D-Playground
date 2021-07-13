package Playground.AI;

public class Neuron {
    private double[] inputs;
    private double[] weights;
    private double bias;
    private double perceptron;
    private double step;
    private double sigmoid;
    private double output;

    public Neuron(double[] inputs, double[] weights, double bias) {
        this.inputs = inputs;
        this.weights = weights;
        this.bias = bias;
        perceptron = perceptron();
        step = stepFunction();
        sigmoid = sigmoidFunction();
        output = sigmoidFunction();

    }
    public Neuron(double input){
        this.output = input;
    }


    private double perceptron() {
        double sum = 0;
        for(int i = 0; i < inputs.length; i++) {
            sum += weights[i] * inputs[i];
        }
        return sum + bias;
    }

    private double stepFunction() {
        if(perceptron <= 0)
            return 0;
        else
            return 1;
    }

    private double sigmoidFunction() {

        return 1/(1 + Math.pow(Math.E, -perceptron));
    }

    public double getPerceptron() {
        return perceptron;
    }
    public double getStepFunction() {
        return step;
    }
    public double getSigmoid() {
        return sigmoid;
    }
    public double getOutput() {
        return output;
    }

}
