<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container mt-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">Register User</li>
        </ol>
    </nav>
</div>
<div class="container mt-3">
    <div class="row">
        <div class="col-sm-6 offset-3">
            <%
                String userError = request.getParameter("usererror");
                String login = request.getParameter("login");
                String fullName = request.getParameter("full_name");
                if(userError!=null){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                User with login "<%=login%>" exists!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <%
                String passwordError = request.getParameter("passworderror");
                if(passwordError!=null){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Passwords are not same!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <%
                String success = request.getParameter("success");
                if(success!=null){
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                User added successfully!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <form action="/register" method="post">
                <div class="form-group">
                    <label>
                        LOGIN :
                    </label>
                    <input type="text" name="login" class="form-control" value="<%=(login!=null?login:"")%>" required>
                </div>
                <div class="form-group">
                    <label>
                        PASSWORD :
                    </label>
                    <input type="password" name="password" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>
                        REPEAT PASSWORD :
                    </label>
                    <input type="password" name="re_password" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>
                        FULL NAME :
                    </label>
                    <input type="text" name="full_name" class="form-control" value="<%=(fullName!=null?fullName:"")%>" required>
                </div>
                <div class="form-group">
                    <button class="btn btn-success">SIGN UP</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
