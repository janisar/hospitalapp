package com.hospital.spring.mvc.diagnose.query;

import com.hospital.spring.mvc.data.Symptom;


public class PatientAnswer {
	
	private boolean answer;
	private Symptom symptom;
	
	public Symptom getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
}
