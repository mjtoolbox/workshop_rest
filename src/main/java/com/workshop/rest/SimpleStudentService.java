package com.workshop.rest;

import com.workshop.model.Student;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by mijo on 2016-07-05.
 */
@Path("/students")
@Produces("application/json")
@Api(value = "/students", description = "Student service for simple object return in json type.")
public class SimpleStudentService {

    // To make the list dynamic based on add, update, delete, make students list static
//    static List<Student> students = new ArrayList<Student>();
    List<Student> students = new ArrayList<Student>();

    public SimpleStudentService() {
        // Populate test data. Later each WS method will retrieve data from DB.
        students.add(new Student(1001, "Mike", 17));
        students.add(new Student(1002, "Jane", 19));
        students.add(new Student(1003, "Bob", 19));
        students.add(new Student(1004, "Susan", 22));
        students.add(new Student(1005, "Daniel", 25));
        students.add(new Student(1006, "John", 26));
        students.add(new Student(1007, "Debbie", 28));
    }


    @GET
    @Path("/")
    @ApiOperation(value = "Retrieve a list of Student", notes = "Retrieve all students")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in the server")
    })
    public List<Student> findAll() {
        return students;
    }


    @GET
    @Path("{id}")
    @ApiOperation(value = "Retrieve a student by id", notes = "Retrieve one student")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in the server")
    })
    public Student findById(@ApiParam(value = "id", required = true) @PathParam("id") String anId) {
        return findStudentById(Integer.parseInt(anId));
    }


    @GET
    @Path("search/{query}")
    @ApiOperation(value = "Retrieve students by criteria (name)", notes = "Retrieve students by name for now")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in the server")
    })
    public List<Student> search(@ApiParam(value = "query", required = true) @PathParam("query") String aQuery) {
        List<Student> newList = new ArrayList<Student>();
        newList.add(findStudentByName(aQuery));
        return newList;
    }


    @DELETE
    @Path("{id}")
    @Consumes("application/x-www-form-urlencoded")
    @ApiOperation(value = "Remove Student", notes = "Remove a new student by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Resource deleted successfully"),
            @ApiResponse(code = 500, message = "Something wrong in the server")
    })
    public void remove(@ApiParam(value = "id", required = true) @PathParam("id") int anId) {
        removeStudentById(anId);
    }


    @PUT
    @Path("{id}")
    @ApiOperation(value = "Update Student", notes = "Update a student")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in the server")
    })
    public Student update(Student aStudent) {
        return updateStudent(aStudent);
    }


    @POST
    @Path("add")
    @Consumes("application/x-www-form-urlencoded")
    @ApiOperation(value = "Add Student", notes = "Adding a new student, id will be generated.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in the server")
    })
    public Student addStudent(@ApiParam(value = "Name", required = true) @FormParam("name") String aName,
                              @ApiParam(value = "Age", required = true) @FormParam("age") int anAge) {
        Student newStudent = new Student();
        newStudent.setName(aName);
        newStudent.setAge(anAge);
        newStudent.setId(generateNextId());
        students.add(newStudent);
        return newStudent;
    }


    private Student findStudentById(int anId) {
        for (Student student : students) {
            if (student.getId() == anId) {
                return student;
            }
        }
        return null;
    }

    private Student findStudentByName(String aName) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(aName)) {
                return student;
            }
        }
        return null;
    }

    private void removeStudentById(int anId) {
        ListIterator<Student> iter = students.listIterator();
        while (iter.hasNext()) {
            if (iter.next().getId() == anId) {
                iter.remove();
            }
        }
    }

    private Student updateStudent(Student aStudent) {

        Student newStudent = new Student();
        newStudent.setId(aStudent.getId());
        newStudent.setName(aStudent.getName());
        newStudent.setAge(aStudent.getAge());

        removeStudentById(aStudent.getId());
        students.add(newStudent);

        return newStudent;
    }

    private int generateNextId() {
        Collections.sort(students, Student.idComparator);
        return students.get(0).getId() + 1;
    }
}
