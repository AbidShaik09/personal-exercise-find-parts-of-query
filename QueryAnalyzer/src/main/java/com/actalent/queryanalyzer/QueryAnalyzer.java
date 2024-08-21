/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.actalent.queryanalyzer;

import java.util.Scanner;

/**
 *
 * @author pulkit
 */
public class QueryAnalyzer {

    public static void main(String[] args) {
        System.out.println("Enter the Query");
        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();
        //step1.1 -> split query into a string array
        String []serializedString= QueryOperator.serialize(query);
        
       //step1.2 -> extract filename
        String fileName =null;
        fileName = QueryOperator.getFileName(serializedString);
        System.out.println("File Name: "+fileName);
        
        //step1.3 -> extract base Query
        String baseQuery =  QueryOperator.getBaseQuery(query);
        System.out.println("Base Query: "+ baseQuery);
        
        //step2.1 -> Extract the selected fields or information
        
        String [] fields = QueryOperator.extractFields(query);
        System.out.println("Fields extracted from Query: ");
        for(String s: fields){
            System.out.println("\t"+s);
        }
        
        // step2.2 -> Extract part after where word before
        //'group by or order by' if they exist in the query
        String equations = QueryOperator.extractEquations(query);
        System.out.println("Equation extracted from Query: ");
        System.out.println(equations);
        
        // step 2.3 -> Eplit the equation into individual equation if 'and' || 'or' are present
        
    }
    
}
