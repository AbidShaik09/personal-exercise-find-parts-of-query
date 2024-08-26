/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.actalent.queryanalyzer;

import java.util.* ;

/**
 *
 * @author pulkit
 */
public class QueryOperator {
    public static String[] serialize(String query ){
        String [] res;
        res= query.split(" ");
        
        return res;
    }
    public static String getFileName(String query){
        String []  serializedString = serialize(query);
        for(String s : serializedString){
            if(s.contains(".")){
                return s;
            }
        }
        return null;
    }
    public static String getBaseQuery(String  query){
        
        String [] base = query.split("WHERE");
        if(base.length<2){
             base = query.split("where");
       
        }
        if(base.length!=0)
            base[0] = base[0].strip();
        return base[0];
    }
    public static String[] extractFields(String query){
        
        String [] base = query.split("from");
        if(base.length<2){
            base = query.split("FROM");
        }
       
        List <String> ls = new ArrayList<>();
        if(base.length>1){
            String [] temp;
            temp = base[0].split("[,\\s]+");
            for(String s: temp){
                
                
                if(
                    s.contains("select")||
                    s.contains("SELECT") 
                    )
                    continue;
                ls.add(s.trim());
                
                
            }
        }
        String [] fields = null;
        if(ls.size()>0)
            fields = new String[ls.size()];
        int counter=0;
        for(String s:ls){
            fields[counter++] = s;
        }
        return fields;
    }
    
    public static String extractEquation(String query){
        String equation="" ;
        String [] temp = query.split("(?i)\\bwhere\\b");
        if(temp.length<2)
            return null;
        
        temp= temp[1].split(" ");
        for(String s: temp){
            String word = s.toLowerCase();
            if(word.equals("order")||word.equals("group"))
                break;
            equation+=" "+s;
        }
        
        return equation;
    }
    
    public static String[] extractConditions(String query){
        String equation = extractEquation(query);
        if(equation==null){
            return null;
        }
        List<String> ls = new ArrayList<>();
        
        String w = equation.toUpperCase();
        String[] parts = w.split("\\s+AND\\s+|\\s+OR\\s+");
        return parts;
        
    }
    
    public static String [] extractLogicalOperators(String query){
      
        String [] serializedQuery= serialize(query);
        int count = 0;
        for(String s:serializedQuery){
            if( s.toUpperCase().equals("AND") || s.toUpperCase().equals("OR") 
                    || s.toUpperCase().equals("NOT")  ){
                count+=1;
                
            }
        }
        String [] sol = new String[count];
        int counter =0;
        for(String s:serializedQuery){
            if( s.toUpperCase().equals("AND") || s.toUpperCase().equals("OR") 
                    || s.toUpperCase().equals("NOT")  ){
                sol[counter++] = s;
                
            }
        }
        return sol;
    }
    
    public static String extractField(String field, String query){
        String res=null;
        if(query.contains(field.toUpperCase())){
            String [] t = query.split(field.toUpperCase()) ;
            if(t.length>1){
                t= t[1].split(" ");
               return res;
            }
        }
        if(query.contains(field.toLowerCase())){
            String [] t = query.split(field.toLowerCase()) ;
            if(t.length>1){
                t= t[1].split(" ");
                res=t[0];
            }
        }
        return res;
    }
    
    public static String[] extractFunctions(String query){
        String[] res;
        String [] str = query.toLowerCase().split("\\s+|,+");
        int count = 0;
        for(String s:str){
            if(s.startsWith("min(")||s.startsWith("max(")||
                    s.startsWith("count(")||s.startsWith("avg(")
                    ){
                count++;
            }
        }
        if(count ==0 ){
            return null;
        }
        res = new String[count];
        int c = 0;
        for(String s:str){
            if(s.startsWith("min(")||s.startsWith("max(")||
                    s.startsWith("count(")||s.startsWith("avg(")
                    ){
                res[c++] = s;
            }
        }
        
        return res;
    }
    
    
    
}
