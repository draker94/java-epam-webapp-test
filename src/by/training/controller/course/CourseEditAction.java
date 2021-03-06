package by.training.controller.course;

import by.training.controller.Action;
import by.training.controller.Forward;
import by.training.di.ServiceCreationException;
import by.training.domain.Course;
import by.training.domain.Instructor;
import by.training.service.CourseService;
import by.training.service.InstructorService;
import by.training.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public class CourseEditAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(CourseEditAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            CourseService courseService = getServiceCreator().getCourseService();
            InstructorService instructorService = getServiceCreator().getInstructorService();
            List<Instructor> instructorList = instructorService.findAll();
            request.setAttribute("instructorList", instructorList);
            if (id != null) {
                Course course = courseService.findById(Long.parseLong(id));
                if (course != null) {
                    request.setAttribute("course", course);
                } else {
                    throw new IllegalArgumentException("Course ID is wrong!");
                }
            }
        } catch (ServiceException | ServiceCreationException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
        catch (IllegalArgumentException e) {
            response.sendError(404, e.getMessage());
        }
        return null;
    }
}
