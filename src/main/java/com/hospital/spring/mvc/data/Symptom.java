package com.hospital.spring.mvc.data;


import java.util.ArrayList;

public class Symptom implements Comparable<Symptom> {
	
	private String name;
	
	private ArrayList<Disease> allDiseasesWithThisSymptom;
	
	public Symptom(String name) {
		this.name = name;
		allDiseasesWithThisSymptom = new ArrayList<Disease>();
	}
	
	protected void addDisease(Disease d) {
		this.allDiseasesWithThisSymptom.add(d);
	}
	
	public int diseasesCount() {
		return allDiseasesWithThisSymptom.size();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Disease> getDiseases() {
		return allDiseasesWithThisSymptom;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Symptom other = (Symptom) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.toUpperCase().equals(other.name.toUpperCase()))
			return false;
		return true;
	}

	@Override
	public int compareTo(Symptom o) {
		if (o.getDiseases().size() > this.getDiseases().size()) {
			return 1;
		} else if (o.getDiseases().size() == this.getDiseases().size()) {
			return this.getName().toUpperCase().compareTo((o.getName().toUpperCase()));
		}
		else {
			return -1;
		}
	}
}