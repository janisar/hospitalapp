package com.hospital.spring.mvc.data;

import java.util.ArrayList;

public class Disease implements Comparable<Disease>{
	
	private ArrayList<Symptom> symptoms;
	private String name;
	
	public Disease(String name) {
		this.name = name;
		symptoms = new ArrayList<Symptom>();
	}

	public String getName() {
		return name;
	}
	protected void addSymptom(Symptom symptom) {
		this.symptoms.add(symptom);
	}
	
	public int countSymptoms() {
		return symptoms.size();
	}

	public ArrayList<Symptom> getAllSymptoms() {
		return this.symptoms;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int compareTo(Disease o) {
		if (o.countSymptoms() > this.countSymptoms()) {
			return 1;
		} else if (o.countSymptoms() == this.countSymptoms()) {
			return getName().toUpperCase().compareTo(o.getName().toUpperCase());
		}
		else {
			return -1;
		}
	}
}