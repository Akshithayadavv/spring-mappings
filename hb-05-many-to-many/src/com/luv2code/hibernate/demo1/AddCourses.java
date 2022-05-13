package com.luv2code.hibernate.demo1;


import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCourses {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass((InstructorDetail.class))
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            //start transaction
            session.beginTransaction();
            //get student
            int studentId=2;
            Student tempStudent = session.get(Student.class,studentId);

            //save course
            System.out.println("Loaded student"+tempStudent);
            System.out.println("Course"+tempStudent.getCourses());

            //create courses
            Course tempCourse1 = new Course("painting");
            Course tempCourse2 = new Course("piano");
            Course tempCourse3 = new Course("guitar");
            //add courses
            tempCourse1.addStudent(tempStudent);
            tempCourse2.addStudent(tempStudent);
            tempCourse3.addStudent(tempStudent);
            //save courses
            System.out.println("Saving courses");
            session.save(tempCourse1);
            session.save(tempCourse2);
            session.save(tempCourse3);
            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done");
        }
        finally {{
            session.close();
            factory.close();
        }
        }
    }
}
