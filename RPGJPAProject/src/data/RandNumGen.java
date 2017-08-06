package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//takes in low and high and generates a random number (or random number list, if numNum is given)
public class RandNumGen {
	private Random rand = new Random();
	private int lowNum;
	private int highNum;
	private int numNum = 1;

	public Integer getRNG(int lowNum, int highNum) {
		this.lowNum = lowNum;
		this.highNum = highNum;
		
		int randomNumber = rand.nextInt((highNum - lowNum) + 1) + lowNum;

		return randomNumber;
	}
	
	public List<Integer> getRNG(int lowNum, int highNum, int numNum) {
		this.lowNum = lowNum;
		this.highNum = highNum;
		
		List<Integer> randomNumberList = new ArrayList<>(numNum);
		for (Integer integer : randomNumberList) {
			integer = rand.nextInt((highNum - lowNum) + 1) + lowNum;
		}

		return randomNumberList;
	}
	
	public Double getRNG(double lowNumD, double highNumD) {
		this.lowNum = (int)(100*lowNumD);
		this.highNum = (int)(100*highNumD);
		
		double randomNumber = (double)( (rand.nextInt((highNum - lowNum) + 1) + lowNum) /100);

		return randomNumber;
	}
	
	public List<Double> getRNG(double lowNumD, double highNumD, int numNum) {
		this.lowNum = (int)(100*lowNumD);
		this.highNum = (int)(100*highNumD);
		
		List<Double> randomNumberList = new ArrayList<>(numNum);
		for (Double d : randomNumberList) {
			d = (double)( (rand.nextInt((highNum - lowNum) + 1) + lowNum) /100 );
		}

		return randomNumberList;
	}

	public RandNumGen() {
		this.lowNum = 0;
		this.highNum = 0;
		this.numNum = 1;
	}

	public int getLowNum() {
		return lowNum;
	}

	public void setLowNum(int lowNum) {
		this.lowNum = lowNum;
	}

	public int getHighNum() {
		return highNum;
	}

	public void setHighNum(int highNum) {
		this.highNum = highNum;
	}

	public int getNumNum() {
		return numNum;
	}

	public void setNumNum(int numNum) {
		this.numNum = numNum;
	}

}
