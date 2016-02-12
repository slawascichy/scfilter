package pl.slawas.filter.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * SubClauses obiekt definiujacy listę klauzul zapytań zebranych jako
 * podzapytanie
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.1 $
 * 
 * @param <QueClause>
 *            klasa oryginalnej implementacji klauzuli zapyania
 */
public class SubClauses<QueClause> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6372472624332208420L;

	private Set<QueClause> clauses;

	/**
	 * trafność wyników zapytania jaki ma być spełniony ogólny wynik
	 * podzapytania.
	 */
	private Accuracy accuracy = Accuracy.MUST;

	public SubClauses() {
	}

	/**
	 * 
	 * @param clauses
	 *            lista klauzul zapytań zebranych do podzapytania podzapytanie
	 * @param accuracy
	 *            trafność z jaką ma być spełniony ogólny wynik podzapytania
	 */
	public SubClauses(Set<QueClause> clauses, Accuracy accuracy) {
		this.clauses = clauses;
		if (accuracy != null)
			this.accuracy = accuracy;
	}

	/**
	 * @return the clauses
	 */
	public Set<QueClause> getClauses() {
		return clauses;
	}

	/**
	 * @param clauses
	 *            the clauses to set
	 */
	public void setClauses(Set<QueClause> clauses) {
		this.clauses = clauses;
	}

	/**
	 * @return the accuracy
	 */
	public Accuracy getAccuracy() {
		return accuracy;
	}

	/**
	 * @param accuracy
	 *            the accuracy to set
	 */
	public void setAccuracy(Accuracy accuracy) {
		this.accuracy = accuracy;
	}

	public SubClauses<QueClause> addClause(QueClause clause) {
		if (this.clauses == null) {
			this.clauses = new HashSet<QueClause>();
		}
		this.clauses.add(clause);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accuracy == null) ? 0 : accuracy.hashCode());
		result = prime * result + ((clauses == null) ? 0 : clauses.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		try {

			SubClauses<QueClause> other = (SubClauses<QueClause>) obj;
			if (accuracy == null) {
				if (other.accuracy != null) {
					return false;
				}
			} else if (!accuracy.equals(other.accuracy)) {
				return false;
			}

			if (this.clauses == null && other.clauses == null) {
				return true;
			}
			if (this.clauses == null && other.clauses != null) {
				return false;
			}
			if (this.clauses != null && other.clauses == null) {
				return false;
			}
			if ((this.clauses == null ? 0 : this.clauses.size()) != (other.clauses == null ? 0
					: other.clauses.size())) {
				return false;
			}
			for (QueClause clause : other.clauses) {
				if (!this.clauses.contains(clause)) {
					return false;
				}
			}

		} catch (ClassCastException e) {
			return false;
		}

		return true;
	}

}
