/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.carvoting;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class VotingPole implements Serializable{
    
    private String carName;
    private int numberOfVotes;

    public VotingPole() {
    }

    public VotingPole(String carName, int numberOfVotes) {
        this.carName = carName;
        this.numberOfVotes = numberOfVotes;
    }
    
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    @Override
    public String toString() {
        return "WorkerClass{" + "carName=" + carName + ", numberOfVotes=" + numberOfVotes + '}';
    }
    
    
    
}
