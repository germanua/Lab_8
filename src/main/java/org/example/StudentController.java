package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final Database database = DatabaseSingleton.getInstance();

    @GetMapping("/{student_id}/courses")
    public String viewBooks(Model model, @PathVariable("student_id") String student_id) {
        model.addAttribute("courses", database.getCoursesForStudent(Integer.parseInt(student_id)));
        model.addAttribute("student_id", Integer.parseInt(student_id));
        return "student-view-courses";
    }

    @GetMapping("/{student_id}/enroll/{course_id}")
    public RedirectView addBookView(Model model, @PathVariable("student_id") String student_id, @PathVariable("course_id") String course_id) {
        Course courseToUpdate = database.getCourseByUid(course_id);
        CourseBuilder courseBuilder = new CourseBuilder(courseToUpdate);

        if (courseToUpdate.getStudents().contains(Integer.parseInt(student_id))) {
            return new RedirectView("/" + student_id + "/courses", true);
        }

        List<Integer> students = courseToUpdate.getStudents();
        students.add(Integer.parseInt(student_id));
        courseBuilder.setStudents(students);

        Course course = database.updateCourse(courseBuilder.build());
        model.addAttribute("course", course);

        return new RedirectView("/student/" + student_id + "/courses", true);
    }

    @GetMapping("/{student_id}/course/{id}")
    public String showCourse(Model model, @PathVariable("student_id") String student_id, @PathVariable("id") String id){
        Course course = database.getCourseByUid(id);
        model.addAttribute("course", course);
        return "student-view-course";
    }

    @GetMapping("/{student_id}/course/{id}/tasks/{task_id}/complete")
    public RedirectView completeTask(@PathVariable("student_id") String student_id, @PathVariable("id") String id, @PathVariable("task_id") String task_id) {

        database.markTaskAsDone(id, Integer.parseInt(task_id), Integer.parseInt(student_id));

        return new RedirectView("/student/" + student_id + "/course/" + id, true);
    }
}