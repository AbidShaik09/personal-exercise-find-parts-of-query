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
        fileName = QueryOperator.getFileName(query);
        System.out.println("File Name: "+fileName);
        
        //step1.3 -> extract base Query
        String baseQuery =  QueryOperator.getBaseQuery(query);
        System.out.println("Base Query: "+ baseQuery);
        
        //step2.1 -> Extract the selected fields or information
        
        String [] fields = QueryOperator.extractFields(query);
        System.out.println("Fields extracted from Query: ");
        if(fields!=null)
        for(String s: fields){
            System.out.println("\t"+s);
        }
        
        // step2.2 -> Extract part after where word before
        //'group by or order by' if they exist in the query
        String equations = QueryOperator.extractEquation(query);
        
        System.out.println("Equation extracted from Query: ");
        if(equations!=null)
        System.out.println(equations);
        
        
        
        // step 2.3 -> Eplit the equation into conditions equation if 'and' || 
        //'or' are present
        String [] conditions = QueryOperator.extractConditions(query);
        
        System.out.println("Extracted Conditions:");
        if(conditions!=null)
        for(String s: conditions){
            System.out.println("\t"+s);
        }
        //step 3.1 -> extract logical operators
        
        String [] logicalOperators= QueryOperator.extractLogicalOperators(query);
        System.out.println("Extracted Logical Operators:");
        
        for(String s: logicalOperators){
            System.out.println("\t"+s);
        }
        // step 3.2 -> extract order by field
        String orderByField = QueryOperator.extractField(" order by ",query);
        
        System.out.println("Extracted Order By field:");
        if(orderByField!=null)
        System.out.println("\t"+orderByField);
        
        
        //step 4.1 -> extract group by
        String groupByField=QueryOperator.extractField(" group by ",query);
        
        
        System.out.println("Extracted Group By field:");
        if(groupByField!=null)
        System.out.println("\t"+groupByField);
        
        //step 4.2 ->
        String []  findFunctions = QueryOperator.extractFunctions(query);
        System.out.println("Extracted functions:");
        
        if(findFunctions!=null)
        for(String s: findFunctions){
            
        System.out.println("\t"+s);
            
        }
    }
    
}
