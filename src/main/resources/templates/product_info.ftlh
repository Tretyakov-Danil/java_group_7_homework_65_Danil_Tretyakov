<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <form action="/products" method="get">
            <button class="border-0 bg-transparent" style="font-size: 28px" type="submit">ONLINE STORE</button>
        </form>
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item mr-4">
                <a href="/orders"><i class="fas fa-shopping-cart mt-1" style="font-size: 24px"></i></a>
            </li>
            <li class="nav-item">
                <form action="/products/filter" method="get">
                    <button class="btn active px-4 mr-5 btn-outline-success" type="submit">Search</button>
                </form>
            </li>
            <li class="nav-item mr-3 my-auto">
                ${username}
            </li>
            <#if _csrf??>
                <li class="nav-item">
                    <form action="/logout" method="post">
                        <input type="hidden"
                               name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}">
                        <button class="btn btn-outline-success active px-4" type="submit">Logout</button>
                    </form>
                </li>
            <#else>
                <li class="nav-item">
                    <a href="/logout" class="btn btn-outline-success active px-4">Logout</a>
                </li>
            </#if>
        </ul>
    </div>
</nav>
<img src="..static/images/${product.imagePath}">
    ${product.brand}
    ${product.name}
    ${product.amount}
    ${product.description}
</body>
</html>