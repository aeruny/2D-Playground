package Playground.AI;

import java.util.ArrayList;
import java.util.List;

public class Network {

    public static void main(String[] args) {
        int numLayer = 3;
        List<Integer> layerSize = new ArrayList<Integer>(numLayer);
        int numInput = 3;
        int numHidden = 2;
        int numOutput = 2;
        layerSize.add(numInput);
        layerSize.add(numHidden);
        layerSize.add(numOutput);

        int numNeuron;
        List<List<Neuron>> neurons = new ArrayList<List<Neuron>>(numLayer);
        List<Neuron> inputs = new ArrayList<Neuron>(numInput);
        List<Neuron> hidden = new ArrayList<Neuron>(numHidden);
        List<Neuron> outputs = new ArrayList<Neuron>(numOutput);
        inputs.add(new Neuron(3));
        inputs.add(new Neuron(2));
        inputs.add(new Neuron(1));

        neurons.add(inputs);
        neurons.add(hidden);
        neurons.add(outputs);
        for(int i = 1; i < numLayer; i++){ //Skip the Input Layer
            int prevLayerSize = neurons.get(i-1).size(); //The size of the previous layer for inputs/weights
            for(int j = 0; j < layerSize.get(i); j++) {

                double[] input = new double[prevLayerSize];
                double[] weight = new double[prevLayerSize];
                double bias = (int) (Math.random() * 10 - 5); //random number (-5 <= x <= 5)
                for (int k = 0; k < prevLayerSize; k++) {
                    input[k] = neurons.get(i-1).get(k).getOutput();
                    weight[k] = (int) (Math.random() * 10 - 5);
                }
                neurons.get(i).add(new Neuron(input, weight, bias));
                System.out.println(neurons.get(i).get(j).getOutput());
            }
        }
        //System.out.println(neurons.get(1).get(0).getOutput());


    }
/*
    public static Neuron[][] setNeurons(int numLayer, int numNeuron){
        Neuron[][] neurons = new Neuron[numLayer][numNeuron];
        for(int i = 0; i < numLayer; i++) {
            for(int j = 0; j < numNeuron; j++) {
                //neurons[i][j] =
            }
        }
        return neurons;
    }

 */
}
