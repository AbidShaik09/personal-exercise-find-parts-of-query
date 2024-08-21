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
    public static String getFileName(String [] serializedString){
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
                    s.contains("SELECT") || s.contains("(") 
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
    
    public static String extractEquations(String query){
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
}
