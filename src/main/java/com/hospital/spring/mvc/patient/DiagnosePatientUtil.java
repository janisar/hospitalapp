package com.hospital.spring.mvc.patient;

import java.util.ArrayList;

import com.hospital.spring.mvc.data.Data;
import com.hospital.spring.mvc.data.Disease;
import com.hospital.spring.mvc.data.Symptom;


public class DiagnosePatientUtil {

	static ArrayList<Symptom> symptom;
	static ArrayList<Disease> disease;
	
	public static ArrayList<Disease> findDiagnose(String[] symptoms) {
		if (symptom == null || disease == null) {
			Data data = new Data();
			symptom = data.getUniqueSymptoms();
			disease = data.getDiseases();
		}
		ArrayList<Symptom> userSymptoms = new ArrayList<Symptom>();
		for (String s : symptoms) {
			userSymptoms.add(new Symptom(s));			
		}
		
		ArrayList<Disease> userDiseases = diagnose(userSymptoms);
		return userDiseases;
	}
	
	private static ArrayList<Disease> diagnose(ArrayList<Symptom> userSymptoms) {
		ArrayList<Disease> possibleDiseases = new ArrayList<Disease>();
		for (int i = 0; i < disease.size(); i++) {
			if (disease.get(i).getAllSymptoms().containsAll(userSymptoms)) {
				possibleDiseases.add(disease.get(i));
			}
		}
		return possibleDiseases;
	}

}
