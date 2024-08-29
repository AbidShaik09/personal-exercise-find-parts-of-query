/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.actalent.queryanalyzer.QueryOperator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author pulkit
 */
public class QueryAnalyzerTest {
    String query;
    public QueryAnalyzerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
         query = "select name , age from ipl.csv group by winRate";
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void TestSplit(){
        String []serializedString= QueryOperator.serialize(query);
        String [] s = {"select", "name" ,",", "age", "from", "ipl.csv", "group", "by", "winRate"};
        assertArrayEquals(s,serializedString);
    }
    
    @Test
    public void TestBaseQuery(){
        String serializedString= QueryOperator.getBaseQuery(query);
        String s = "select name , age from ipl.csv group by winRate";
        assertEquals(s,serializedString);
    }
    
    // File Name: ipl.csv
    @Test
    public void TestFileName(){
        
        String fileName =null;
        fileName = QueryOperator.getFileName(query);
        assertEquals("ipl.csv",fileName);
    }
    @Test
    public void TestExtractFields(){
        
        String[] serializedString= QueryOperator.extractFields(query);
        String[] s = {"name" ,"age"};
        assertArrayEquals(s,serializedString);
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
