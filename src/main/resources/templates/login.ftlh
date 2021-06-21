<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style type="text/css">
        .login-button{
            width: 50%;
            margin-left: 25%;
            margin-right: 25%;
        }
    </style>
</head>
<body>
    <div class="text-center mt-2">
    <h2>Online Store</h2>
    <h3 class="mt-3">Login</h3>
    </div>
    <div class="w-50 mx-auto">
        <form action="/client/loginPost" method="post" modelAttribute="clientDto">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.paramaterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
            </#if>
            <div class="mb-3">
                <input type="email" class="form-control" name="email" aria-describedby="emailHelp" placeholder="Enter your email">
            </div>
            <div class="mb-3">
                <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Enter your password">
            </div>
            <button type="submit" class="btn btn-success login-button">Sign in</button>
        </form>
        <div class="mt-3">
            <form action="/client/register" method="get">
                <button type="submit" class="btn btn-success login-button">Register</button>
            </form>
        </div>
    </div>

</body>
</html>