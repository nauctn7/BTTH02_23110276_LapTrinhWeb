package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;
import service.UserServiceImpl;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GET: Hiển thị trang đăng ký
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Lấy session hiện tại (không tạo mới nếu chưa có)
        HttpSession session = req.getSession(false);
        
        // Nếu đã có session (đã đăng nhập) thì chuyển về admin
        if (session != null && session.getAttribute("username") != null) {
            resp.sendRedirect(req.getContextPath() + "/admin");
            return;
        }

        // Nếu có cookie "username" thì tạo session mới từ cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("username".equals(c.getName())) {
                    session = req.getSession(true);
                    session.setAttribute("username", c.getValue());
                    resp.sendRedirect(req.getContextPath() + "/admin");
                    return;
                }
            }
        }

        // Nếu chưa đăng nhập → hiện form đăng ký
        req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
    }

    // POST: Xử lý form đăng ký
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu từ form
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email    = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone    = req.getParameter("phone");

        UserService service = new UserServiceImpl();

        // Kiểm tra email tồn tại
        if (service.checkExistEmail(email)) {
            req.setAttribute("alert", "Email đã tồn tại!");
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra username tồn tại
        if (service.checkExistUsername(username)) {
            req.setAttribute("alert", "Tài khoản đã tồn tại!");
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
            return;
        }

        // Thực hiện đăng ký
        boolean ok = service.register(username, password, email, fullname, phone);
        if (ok) {
            // Đăng ký thành công → chuyển về trang login
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            // Nếu lỗi hệ thống → ở lại trang đăng ký
            req.setAttribute("alert", "System error!");
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
        }
    }
}
