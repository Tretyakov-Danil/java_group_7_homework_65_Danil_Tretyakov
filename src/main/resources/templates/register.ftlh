<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <form action="/products" method="get">
                <button class="border-0 bg-transparent" style="font-size: 28px" type="submit">Navbar</button>
            </form>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item mr-4">
                    <a class="nav-link active" aria-current="page" href="/orders">Orders</a>
                </li>
                <li class="nav-item">
                    <form action="/products/filter" method="get">
                        <button class="btn active px-4 mr-5 btn-outline-success" type="submit">Search</button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
    <div class="text-center mt-2">
        <h2 class="mt-3">Register</h2>
    </div>
    <div class="w-50 mx-auto">
        <form action="/client/register" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <div class="mb-3">
                <input type="text" class="form-control" name="username" aria-describedby="emailHelp" placeholder="Enter your username" value="${(dto.name)!''}">
            </div>
            <div class="mb-3">
                <input type="email" class="form-control" name="email" aria-describedby="emailHelp" placeholder="Enter your email" value="${(dto.email)!''}">
            </div>
            <div class="mb-3">
                <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Enter your password" value="">
            </div>
            <button type="submit" class="btn btn-success login-button">Register</button>
        </form>
    </div>
</body>
</html>