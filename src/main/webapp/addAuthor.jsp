<%-- 
    Document   : CreateAuthor
    Created on : Oct 3, 2015, 10:28:47 PM
    Author     : kowal_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create Author</h1>
        <div>
            <form method="POST" name="addAuthorForm" id="addAuthor" action="AuthorController?action=add">
                <input placeholder="Enter name of new author" type="text" name="authorName" id="authorName"  />
                <br>
                <input type="submit" name="recSubmit" value="Submit" id="recSubmit" />
            </form>
        </div>
    </body>
</html>
