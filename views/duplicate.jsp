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
    .primary-color {
      color: #428bca
    }
    .vertical-center {
      min-height: 100%;
      min-height: 100vh;
      width: 100%;
      display: -webkit-box;
      display: -moz-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      display: flex;

      -webkit-box-align : center;
      -webkit-align-items : center;
      -moz-box-align : center;
      -ms-flex-align : center;
      align-items : center;
    }
  </style>
  <script>
    function buttonClick() {
        window.location.href = '/architechApp';
    }
  </script>
</head>
<body>
  <div class="vertical-center">
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <h1 class="text-center primary-color">Our records indicate that you have already registered with us <c:out value="${userName}"/></h1>
        </div>
      </div>
      <button type="submit" class="btn btn-block btn-primary" onclick="buttonClick()">Back To Main</button>
    </div>
  </div>
</body>
</html>

