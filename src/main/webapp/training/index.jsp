<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
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
    <title>Bootstrap Example</title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-body">
                <form>
                    <div class="form-group">
                        <label for="inputFirstName" class="col-sm-2 col-form-label">Имя</label>
                        <input type="text" class="form-control" id="inputFirstName">
                    </div>
                    <div class="form-group">
                        <label for="inputLastName" class="col-sm-2 col-form-label">Фамилия</label>
                        <input type="text" class="form-control" id="inputLastName">
                    </div>
                    <div class="form-group">
                        <label for="inputSex" class="col-sm-2 col-form-label">Пол</label>
                        <select class="form-control" id="inputSex">
                            <option>Мужской</option>
                            <option>Женский</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="inputDescription" class="col-sm-2 col-form-label">Описание</label>
                        <textarea class="form-control" id="inputDescription" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead class="thead-dark">
        <tr>
            <th scope="col">№</th>
            <th scope="col">Имя</th>
            <th scope="col">Фамилия</th>
            <th scope="col">Пол</th>
            <th scope="col">Описание</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <%--        <th scope="row">1</th>--%>
            <%--        <td> </td>--%>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>