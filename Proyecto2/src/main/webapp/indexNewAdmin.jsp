<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Form new Admin</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style2.css">
</head>
<body>
    <div class="main">
        <div class="container">
            <div class="signup-content">
                <div class="signup-img">
                    <img src="images/adminn-4-2.jpg" alt="">
                </div>
                <div class="signup-form">
                    <form method="POST" action="AdminControllerServlet" class="register-form" id="register-form">
                        <h2>Registro Nuevo Administrador</h2>

                        <div class="form-group">
                            <label for="name">Nombre Completo:</label>
                            <input type="text" name="name" id="name" required />
                        </div>

                        <div class="form-group">
                            <label for="identificationNumber">Numero de identificacion:</label>
                            <input type="text" name="identificationNumber" id="identificationNumber" required />
                        </div>

                        <div class="form-group">
                            <label for="birthday">Birthday:</label>
                            <input type="date" name="birthday" id="birthday">
                        </div>

                        <div class="form-group">
                            <label for="cityOfBorn">Ciudad de Nacimiento:</label>
                            <input type="text" name="cityOfBorn" id="cityOfBorn">
                        </div>

                        <div class="form-submit">
                            <input type="button" onclick="#" name="Eliminar" class="submit" value="Eliminar">
                            <input type="button" onclick="#" name="Modificar" class="submit" value="Modificar">
                            <input type="submit" value="Submit Form" class="submit" name="submit" id="submit" />
                            
                            
                        </div>
                    </form>
                    <section>
                        <div>
                            <input type="button" onclick="history.back()" name="Go Back" value="Go Back">
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </div>

    <!-- JS -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
