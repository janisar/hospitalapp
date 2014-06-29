package com.hospital.spring.mvc.patient;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.hospital.spring.mvc.data.Symptom;


public class PatientBean {
	
	@NotNull
	@NotEmpty(message="At least one symptom must be checked, otherwise you are fine!")
	private String[] symptomNames;
	
	private Symptom[] symptomParents;

	public Symptom[] getSymptomParents() {
		return symptomParents;
	}

	public void setSymptomParents(Symptom[] symptomParents) {
		this.symptomParents = symptomParents;
	}

	public String[] getSymptomNames() {
		return symptomNames;
	}

	public void setSymptomNames(String[] symptomNames) {
		this.symptomNames = symptomNames;
	}
}
