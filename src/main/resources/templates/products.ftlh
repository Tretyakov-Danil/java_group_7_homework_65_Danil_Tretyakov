<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <form action="/products" method="get">
                <button class="border-0 bg-transparent" style="font-size: 28px" type="submit">Navbar</button>
            </form>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item mr-4">
                    <a href="/orders"><i class="fas fa-shopping-cart"></i></a>
                </li>
                <li class="nav-item">
                    <form action="/products/filter" method="get">
                        <button class="btn active px-4 mr-5 btn-outline-success" type="submit">Search</button>
                    </form>
                </li>
                <#if isOnline>
                    <li class="nav-item">
                        ${client.username}
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
                <#else>
                    <li class="nav-item">
                        <a href="/client/login" class="btn active px-4 btn-outline-success">Sign in</a>
                    </li>
                </#if>
            </ul>
        </div>
    </nav>
    <div class="row row-cols-1 row-cols-md-2 row-cols-xl-3 mx-auto mt-5">
        <#list products as product>
            <div class="card mb-3 mx-2" style="max-width: 540px;">
                <div class="col-md-4">
                    <img src="..." alt="Product's photo">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h4 class="card-title">${product.brand.name}</h4>
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">${product.description}</p>
                        <form action="/products/${product.id}">
                            <button class="btn btn-outline-success" type="submit">More info</button>
                        </form>
                    </div>
                </div>
            </div>
        </#list>
    </div>
    <a href="/products?size=${pages.getPageSize()}&page=${pages.pageNumber+1}" type="button">Next Page</a>
</body>
</html>