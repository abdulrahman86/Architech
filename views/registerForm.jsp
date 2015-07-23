<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
  <title>Architect Registration Form</title>
  <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato&subset=latin,latin-ext">
  <style type="text/css">
    body {
      font: 14px/1.5 Lato, "Helvetica Neue", Helvetica, Arial, sans-serif
    }
    .col-centered{
      float: none;
      margin: 0 auto;
    }
    .margin-top-50 {
      margin-top: 50px;
    }
  </style>

</head>
<script>
  function bodyOnLoadHandler() 
  {
    if (document.getElementById('invalidUserNamePassword').value === "true")
    {
      document.getElementById('invalidUserNamePasswordDIV').style.display = 'block';
    }
    else
    {
      document.getElementById('invalidUserNamePasswordDIV').style.display = 'none';
    }
  }
</script>
<body onload="bodyOnLoadHandler()">
  <div class="container">
    <div class="row">
      <div class="col-lg-6 col-centered margin-top-50">
        <form class="login-form" method="POST" name="registrationForm">
          <h2 class="text-center">Architech Registration Form</h2>
          <div class="alert alert-danger" id="invalidUserNamePasswordDIV" role="alert">UserName/Password did not follow the specified pattern.</div>
          <div class="form-group">
            <input type="hidden" id="invalidUserNamePassword" name="hidden" value="${invalidUserNamePasswordFlag}"/>
            <input type="hidden" id="token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <label for="username">Username</label>
            <input type="text" id = "username" class="form-control" id="userName" placeholder="username" name="userName" pattern="[a-zA-Z0-9 ]+" minlength="5" required>
            <small>&#9734;should contain only alphanumeric characters</small>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" placeholder="password" name="password" pattern="(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).*" minlength="8" required>
            <small>&#9734;should contain at least 1 number, 1 uppercase, and 1 lowercase character</small>
          </div>
          <button type="submit" class="btn btn-block btn-primary">Submit</button>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
