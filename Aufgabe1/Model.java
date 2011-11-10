package Aufgabe1;

import Model.MainModel;

public class Model extends MainModel {
	private int r1, r2, s1, s2, t1, t2, q, ggt;

	public int getGgt() {
		return ggt;
	}

	public int getS1() {
		return s1;
	}

	public int getT1() {
		return t1;
	}

	public int getR1() {
		return r1;
	}

	public void setR1(int r1) {
		this.r1 = r1;
	}

	public int getR2() {
		return r2;
	}

	public void setR2(int r2) {
		this.r2 = r2;
	}

	/**
	 * 
	 * ggt(a,b)
	 * 
	 * Berrechnungg des ggT von a und b mittels des erweiterten 
	 * Euklidischen Algorithmuses
	 * 
	 * ggt(a,0) = ggt(0,a) = a
	 * ggt(0,0) ist nicht definiert und wirft eine {@link GgtUndefinedValueException}
	 * 
	 * @throws GgtUndefinedValueException
	 */
	public void calculateGgt() throws GgtUndefinedValueException {
		//swap ist ein Zwischenvariable für einen Dreieckstausch
		int swap = 0;

		this.s1 = this.t2 = 0;
		this.s2 = this.t1 = 1;
		
		// sonderfälle des ggt's
		// ggt(a,0) oder ggt(0,a)
		// und ggt(0,0)
		if (this.r1 == 0 && this.r2 == 0) {
			throw new GgtUndefinedValueException("ggt(0,0) ist nicht definiert");
		} else if(this.r1 == 0 || this.r2 == 0){
			if(this.r1==0){
				ggt =  this.r2;	
			}else{
				ggt = this.r1;
				this.s1 = this.s2;
				this.t1 = this.t2;
			}
		}else {
			while (r2 > 0) {
				// berechne Quotient und Rest
				this.q = this.r1 / this.r2;
				this.r1 %= this.r2;

				// vertausche a <-> b für nächste iteration
				swap = this.r1;
				this.r1 = this.r2;
				this.r2 = swap;

				// berechne bizout-koeffizienten
				if (this.r2 > 0) {
					swap = this.s1;
					this.s1 = this.s2 - this.q * this.s1;
					this.s2 = swap;

					swap = this.t1;
					this.t1 = this.t2 - this.q * this.t1;
					this.t2 = swap;
				}
			}

			this.ggt = this.r1;
		}
		this.setChanged();
		this.notifyObservers();
	}
}
