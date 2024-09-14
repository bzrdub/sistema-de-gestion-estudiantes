package com.miempresa;

import com.miempresa.entidades.Estudiante;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

@WebServlet("/nuevoEstudiantes")
public class EstudiantesServlet extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        List<Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e", Estudiante.class).getResultList();
        request.setAttribute("estudiantes", estudiantes);
        request.getRequestDispatcher("/WEB-INF/jsp/listarEstudiantes.jsp").forward(request, response);
        em.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Estudiante estudiante = new Estudiante(nombre, apellido);
        em.persist(estudiante);
        em.getTransaction().commit();
        em.close();

        response.sendRedirect(request.getContextPath() + "/nuevoEstudiantes");
    }

    @Override
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }
}
