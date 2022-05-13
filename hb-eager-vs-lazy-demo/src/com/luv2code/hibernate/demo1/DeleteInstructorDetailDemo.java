package com.luv2code.hibernate.demo1;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//import javax.security.auth.login.Configuration;

public class DeleteInstructorDetailDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //Create session
        Session session=factory.getCurrentSession();

        try{


            //start a transaction
            session.beginTransaction();

            //get the instructor detail object
            int theId=3;
            InstructorDetail tempInstructorDetail=session.get(InstructorDetail.class,theId);

            //print the instructor detail
            System.out.println("tempInstructorDetail: " + tempInstructorDetail);

            //print associated instructor
            System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor()) ;

            //delete the instructordetail
            System.out.println("Deleting tempInstructorDetail " + tempInstructorDetail);

            //remove the associated object reference
            //break bi-directional link
            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            session.delete(tempInstructorDetail);
            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
        finally {
            {
                session.close();
                factory.close();
            }
        }
    }
}
