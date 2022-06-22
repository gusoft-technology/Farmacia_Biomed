/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import DAO.ClienteDAO;
import DAO.TipodocumentoDAO;
import Entidades.Cliente;
import Entidades.Tipodocumento;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public class Controladorcliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private HttpSession sesion;
    Cliente cli = new Cliente();
    ClienteDAO clidao = new ClienteDAO();
    TipodocumentoDAO tpdoc = new TipodocumentoDAO();
    int ide;
    private int cod = 0;
    private String mensaje = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Principal")) {
            response.sendRedirect("Principal.jsp");

        }
        if (menu.equals("Clientes")) {
            sesion = request.getSession();
            sesion.removeAttribute("listaClientes");
            List lista = clidao.listarClientes();
            sesion.setAttribute("listaClientes", lista);

            List listatd = tpdoc.listardocumentos();
            sesion.setAttribute("listadocumentos", listatd);

            response.sendRedirect("Cliente/Cliente.jsp");
        }
        if (menu.equals("Editar")) {

            cod = Integer.parseInt(request.getParameter("id"));

            try {

                sesion = request.getSession();
                sesion.removeAttribute("msgPostOperacion");
                sesion.removeAttribute("listaCliente");
                sesion.removeAttribute("msgListado");
                sesion.removeAttribute("msg");

                sesion.setAttribute("msg", cod);

                // response.sendRedirect("newjsp.jsp");
            } catch (Exception e) {

            }

        }

        if (menu.equals("Cliente")) {
            switch (accion) {
                case "Listar":
                    sesion = request.getSession();
                    sesion.removeAttribute("listaClientes");
                    List lista = clidao.listarClientes();
                    sesion.setAttribute("listaClientes", lista);

                    List listatd = tpdoc.listardocumentos();
                    sesion.setAttribute("listadocumentos", listatd);

                    break;

                case "Agregar":

                    //em.setId_persona(Integer.parseInt(request.getParameter("id")));
                    cli.setNom_persona(request.getParameter("nom"));
                    cli.setApe_persona(request.getParameter("ape"));
                    cli.setTipodocumento(new Tipodocumento(Integer.parseInt(request.getParameter("tpdoc"))));
                    cli.setNumero_identicacion(Integer.parseInt(request.getParameter("numero")));
                    cli.setFecha_naci_persona(request.getParameter("edad"));
                    cli.setNacioalidad_persona(request.getParameter("nacio"));
                    cli.setEmpresa_persona(request.getParameter("empresa"));
                    cli.setCorreo_persona(request.getParameter("correo"));
                    cli.setTelf_persona(request.getParameter("telf"));
                    cli.setDir_persona(request.getParameter("dire"));
                    cli.setSex_persona(request.getParameter("sexo"));

                    boolean resp = clidao.insertar(cli);

                    if (resp == true) {

                    } else {
                        sesion = request.getSession();
                        sesion.removeAttribute("listaClientes");
                        List listame = clidao.listarClientes();
                        sesion.setAttribute("listaClientes", listame);

                        response.sendRedirect("Cliente/Cliente.jsp");
                    }

                    //request.getRequestDispatcher("Controladorempleado1?menu=Empleado&accion=Listar");
                    break;

                case "Eliminar":

                    cli.setId_persona(Integer.parseInt(request.getParameter("id")));
                    clidao.Eliminar(cli);
                    sesion = request.getSession();
                    sesion.removeAttribute("listaClientes");
                    List listas = clidao.listarClientes();
                    sesion.setAttribute("listaClientes", listas);

                    response.sendRedirect("Cliente/Cliente.jsp");

                    //request.getRequestDispatcher("Controladorempleado1?menu=Empleado&accion=Listar");
                    break;

                case "Editar":

                    cli.setId_persona(Integer.parseInt(request.getParameter("id")));
                    cli.setNom_persona(request.getParameter("nom"));
                    cli.setApe_persona(request.getParameter("ape"));
                    cli.setTipodocumento(new Tipodocumento(Integer.parseInt(request.getParameter("tpdoc"))));
                    cli.setNumero_identicacion(Integer.parseInt(request.getParameter("numero")));
                    cli.setFecha_naci_persona(request.getParameter("edad"));
                    cli.setNacioalidad_persona(request.getParameter("nacio"));
                    cli.setEmpresa_persona(request.getParameter("empresa"));
                    cli.setCorreo_persona(request.getParameter("correo"));
                    cli.setTelf_persona(request.getParameter("telf"));
                    cli.setDir_persona(request.getParameter("dire"));
                    cli.setSex_persona(request.getParameter("sexo"));

                    clidao.actualiza(cli);

                    sesion = request.getSession();
                    sesion.removeAttribute("listaClientes");
                    List listas2 = clidao.listarClientes();
                    sesion.setAttribute("listaClientes", listas2);

                    response.sendRedirect("Cliente/Cliente.jsp");

                    //request.getRequestDispatcher("Controladorempleado1?menu=Empleado&accion=Listar");
                    break;
                default:
                    throw new AssertionError();

            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
