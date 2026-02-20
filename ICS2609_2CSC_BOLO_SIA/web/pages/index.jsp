<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <header>
            <!-- <h1><%= application.getInitParameter("header")%></h1> -->
        </header>

        <div class="main-content">
            <div class="login-box">
                <h2>Welcome Back</h2>

                <form action="<%= request.getContextPath()%>/LoginServlet" method="post">
                    <div>
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" placeholder="Enter your username"/>
                    </div>

                    <div>
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" placeholder="Enter your password"/>
                    </div>

                    <button type="submit" id="login-btn">
                        Login <i class="fas fa-sign-in-alt"></i>
                    </button>
                </form>

                <div class="divider">
                    <span>OR</span>
                </div>

                <form action="<%= request.getContextPath()%>/LoginServlet" method="post">
                    <button type="submit" name="action" value="guest" id="guest-btn">
                        Continue as Guest <i class="fas fa-user-secret"></i>
                    </button>
                </form>
            </div>
        </div>

        <footer>
            <h1>
                <%= application.getAttribute("dateTime")%> 
                | <%= application.getAttribute("mpNumber")%>
            </h1>
        </footer>
    </body>
</html>