<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="resources/css/formhack.css">

    <title>Работа мечты</title>
</head>
<body>
<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/post/posts.do">Вакансии</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/candidate/candidates.do">Кандидаты</a>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Регистрация
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/reg.do" id="registration" method="post">

                    <div class="form-group">
                        <label for="name">
                            <span>Имя</span>
                            <input type="text" id="name" class="form-control" name="name" required>
                            <ul class="input-requirements">
                                <li>Длинна не менее 4 букв</li>
                                <li>Используйте цифры и буквы латинского алфавита</li>
                            </ul>
                        </label>
                    </div>

                    <div class="form-group">
                        <label for="password">
                            <span>Пароль</span>
                            <input type="password" id="password" class="form-control" name="password" required>
                            <ul class="input-requirements">
                                <li>Длинна не менее 4 и не более 12 символов</li>
                                <li>Используйте цифры</li>
                                <li>Используйте латинские буквы нижнего регистра</li>
                                <li>Используйте латинские буквы верхнего регистра</li>
                                <li>Используйте спец.символы</li>
                            </ul>
                        </label>
                    </div>

                    <div class="form-group">
                        <label for="password_repeat">
                            <span>Повторите пароль</span>
                            <input type="password" id="password_repeat" class="form-control" name="password_repeat"
                                   required>
                        </label>
                    </div>

                    <div class="form-group">
                        <label for="email">
                            <span>Почта</span>
                            <input type="text" id="email" class="form-control" name="email" required>
                            <ul class="input-requirements">
                                <li>Введите адрес электронной почты</li>
                            </ul>
                        </label>
                    </div>

                    <button type="submit" class="btn btn-primary">Войти</button>
                    <c:if test="${not empty error}">
                        <div style="color:red; font-weight: bold; margin: 30px 0;">
                                ${error}
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="resources/js/validate.js"></script>
</body>
</html>