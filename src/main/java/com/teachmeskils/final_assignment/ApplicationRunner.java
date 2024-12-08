package com.teachmeskils.final_assignment;

import com.teachmeskils.final_assignment.execption.NumberNotFoundException;
import com.teachmeskils.final_assignment.execption.TotalLineNotFoundException;
import com.teachmeskils.final_assignment.operations.FileOperation;
import com.teachmeskils.final_assignment.service.CalculationTotalTurnover;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ApplicationRunner {

    public static void main(String[] args) throws NumberNotFoundException, TotalLineNotFoundException, IOException {
        Map<String, List<File>> documents = FileOperation.fileOperation();
        CalculationTotalTurnover.reportCalculationTotalTurnover(documents);
    }
}
