package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final Database database = DatabaseSingleton.getInstance();

    @GetMapping("/{teacher_id}/courses")
    public String viewCourses(Model model, @PathVariable("teacher_id") String teacher_id) {
        model.addAttribute("courses", database.getCoursesForTeacher(Integer.parseInt(teacher_id)));
        model.addAttribute("teacher_id", Integer.parseInt(teacher_id));
        return "teacher-view-courses";
    }

    @GetMapping("/{teacher_id}/addCourse")
    public String addCourseView(Model model, @PathVariable("teacher_id") String teacher_id) {
        Course course = new Course();
        course.setTeachers(Arrays.asList(new Integer[]{Integer.parseInt(teacher_id)}));
        model.addAttribute("course", course);
        model.addAttribute("teacher_id", Integer.parseInt(teacher_id));
        return "teacher-add-course";
    }

    @PostMapping("/{teacher_id}/addCourse")
    public RedirectView addCourse(@ModelAttribute("course") Course course, RedirectAttributes redirectAttributes, @PathVariable("teacher_id") String teacher_id) {
        final RedirectView redirectView = new RedirectView("addCourse", true);
        CourseBuilder courseBuilder = new CourseBuilder(course);
        courseBuilder.setTeachers(Arrays.asList(new Integer[]{Integer.parseInt(teacher_id)}));
        courseBuilder.setUid(null);
        courseBuilder.setTasks(new ArrayList<>());
        courseBuilder.setStudents(new ArrayList<>());
        Course savedCourse = database.addCourse(courseBuilder.build());
        redirectAttributes.addFlashAttribute("savedCourse", savedCourse);
        redirectAttributes.addFlashAttribute("addCourseSuccess", true);
        return redirectView;
    }

    @GetMapping("/{teacher_id}/course/{id}")
    public String showCourse(Model model, @PathVariable("id") String id, @PathVariable("teacher_id") String teacher_id) {
        Course course = database.getCourseByUid(id);
        model.addAttribute("course", course);
        model.addAttribute("teacher_id", teacher_id);
        return "teacher-view-course";
    }

    @GetMapping("/{teacher_id}/course/{id}/addTask")
    public String addTaskView(Model model, @PathVariable("id") String id, @PathVariable("teacher_id") String teacher_id) {
        Task task = new Task();
        task.setId(-1);
        task.setCompleted(new ArrayList<>());
        model.addAttribute("task", task);
        model.addAttribute("course", database.getCourseByUid(id));
        model.addAttribute("teacher_id", teacher_id);
        return "teacher-add-task";
    }

    @PostMapping("/{teacher_id}/course/{id}/addTask")
    public RedirectView addTask(@ModelAttribute("task") Task task, RedirectAttributes redirectAttributes, @PathVariable("id") String id, @PathVariable("teacher_id") String teacher_id) {
        final RedirectView redirectView = new RedirectView("/teacher/" + teacher_id + "/course/" + id + "/addTask", true);
        task.setCompleted(new ArrayList<>());
        Course savedCourse = database.addTask(task, id);
        redirectAttributes.addFlashAttribute("savedCourse", savedCourse);
        redirectAttributes.addFlashAttribute("addTaskSuccess", true);
        return redirectView;
    }
}