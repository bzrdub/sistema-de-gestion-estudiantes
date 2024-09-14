package com.miempresa.controladores;

import com.miempresa.entidades.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cursos")
public class CursoController extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        List<Curso> cursos = em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
        request.setAttribute("cursos", cursos);
        request.getRequestDispatcher("/WEB-INF/jsp/listarCursos.jsp").forward(request, response);
        em.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Curso curso = new Curso(nombre);
        em.persist(curso);
        em.getTransaction().commit();
        em.close();

        response.sendRedirect("cursos");
    }

    @Override
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }
}
