<%@ page import="java.util.Optional" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%
    Optional<Object> validationError = Optional.ofNullable( request.getAttribute("validation_errors"));
%>


<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Web calculator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<div class="container text-center">
    <div class="mx-auto p-2" style="width: 1000px;">
        <form class="row g-2" method="POST" action="/calculation">

            <div class="col-10">
                <input name="expression" class="form-control form-control-lg" type="text"
                       placeholder="your expression... exp 10+10/20" aria-label=".form-control-lg example">
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-outline-success btn-lg mb-3">Calculate</button>
            </div>
        </form>

        <% if (validationError.isPresent()) {%>
        <%
            Set<String> errors = (HashSet<String>)request.getAttribute("validation_errors");
            for(String error: errors){
                out.println("<p class=\"h4\" style=\"color: red\">Validation error: %s</div>".formatted(error));
            }
        %>

        <% } else { %>
            <p class="h4">${result}</p>
        <% } %>


    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
