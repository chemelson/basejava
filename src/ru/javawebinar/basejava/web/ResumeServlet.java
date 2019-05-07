package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private static Storage storage = Config.get().getStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        List<Resume> resumes = storage.getAllSorted();
        String htmlPage = getHtmlPage(resumes);
        response.getWriter().write(htmlPage);
    }

    private String getHtmlPage(List<Resume> resumes) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><title>All resumes</title></head><body>");
        if (resumes.size() > 0) {
            sb.append("<table>");
            sb.append("<tr><th>UUID</th><th>Fullname</th></tr>");
            resumes.forEach(resume -> {
                sb.append("<tr><td>");
                sb.append(resume.getUuid());
                sb.append("</td><td>");
                sb.append(resume.getFullName());
                sb.append("</td></td>");
            });
            sb.append("</table>");
        } else {
            sb.append("<p>No resumes</p>");
        }
        sb.append("</body></html>");
        return sb.toString();
    }
}
