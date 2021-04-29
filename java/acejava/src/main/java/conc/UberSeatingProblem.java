package conc;

public class UberSeatingProblem {
	
	private int nDemocrats;
	private int nRepublicans;
	private int iDemocrats;
	private int iRepublicans;
	
	public synchronized void requestRide (boolean isDemocrat) {
		//increment the party members count
		if (isDemocrat)
			nDemocrats++;
		else
			nRepublicans++;
		
		//if we have at least 2 of each, let's get seated
		if (nDemocrats >= 2 && nRepublicans >= 2) {
			//reduce the party count
			if (isDemocrat) {
				nDemocrats--;
				iDemocrats++;
			} else {
				nRepublicans--;
				iRepublicans++;
			}
			//notify the waiting party members
			notifyAll();
		} else { //else we wait
			//we wait till boarding has begun, after 2 pairs were found
			while (iDemocrats == 0 && iRepublicans == 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
			//boarding has begun
			if (isDemocrat) {
				if (iDemocrats < 2) { //is there space left for a democrat?
					nDemocrats--;
					iDemocrats++;
				}
			} else {
				if (iRepublicans < 2) { //is there space left for a republican?
					nRepublicans--;
					iRepublicans++;
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
