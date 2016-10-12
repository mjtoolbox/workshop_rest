import com.workshop.model.Student;
import com.workshop.rest.SimpleStudentService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mijo on 2016-10-12.
 */
public class TestStudentService {

    private static SimpleStudentService studentService;

    @BeforeClass
    public static void initStudentService() {
        studentService = new SimpleStudentService();
    }

    @Test
    public void testDisplayStudents() {
        Assert.assertEquals(String.valueOf(studentService.findAll().size()), "28");
    }

    @Test
    public void testFindStudents() {

        System.out.println("********** Before JDK 8 : Find over 30 BC Male count ***********");
        List<Student> studentList = studentService.findAll();
        ArrayList<Student> newList = new ArrayList<Student>();

        for (Student aStudent: studentList){
            if ( aStudent.getAge() > 30 && aStudent.getGender().equals("M") && aStudent.getProvince().equals("BC") )
            {
                newList.add(aStudent);
                System.out.println("Adding to new list: " + aStudent);
            }
        }

        System.out.println("Total list size: " + newList.size());

        Assert.assertNotNull(newList.size());
    }


    @Test
    public void testFindStudentsWithStream() {

        System.out.println("********** JDK 8 : Find over 30 BC Male count ***********");
        long size = studentService.findAll().stream()
                .filter(aStudent -> aStudent.getAge() > 30)
                .filter(aStudent -> aStudent.getGender().equals("M"))
                .filter(aStudent -> aStudent.getProvince().equals("BC"))
                .count();

        System.out.println("Total list size: " + size);
        Assert.assertNotNull(size);
    }

    @Test
    public void testAverageAgeWithStream() {

        System.out.println("********** JDK 8 : Find average age of Females ***********");
        double avgAge = studentService.findAll().stream()
                .filter(aStudent -> aStudent.getGender().equals("F"))
                .mapToInt(aStudent-> aStudent.getAge())
                .average()
                .getAsDouble();

        System.out.println("Average age of Female is : " + avgAge);
        Assert.assertNotNull(avgAge);
    }

    @Test
    public void testSortedByAgeGroupByStateStream() {

        System.out.println("********** JDK 8 : Group by Province and sort by age within the group ***********");
        Map<String, List<Student>> newList = studentService.findAll().stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.groupingBy(Student::getProvince));

        newList.entrySet().stream()
                .forEach( aList -> aList.getValue()
                        .forEach( aStudent -> System.out.println(aStudent)));

        Assert.assertNotNull(newList);
    }

}
