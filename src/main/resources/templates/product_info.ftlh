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
                <li class="nav-item mx-5">
                    <form action="/products/filter" method="get">
                        <button class="btn active px-4 mr-5 btn-outline-success" type="submit">Search</button>
                    </form>
                </li>
                <li class="nav-item mr-4">
                    <a href="/orders/basket"><i class="fas fa-shopping-cart mt-1" style="font-size: 24px"></i></a>
                </li>
                <#if isOnline>
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
                <#else>
                    <li class="nav-item">
                        <a href="/client/login" class="btn active px-4 btn-outline-success">Sign in</a>
                    </li>
                </#if>
            </ul>
        </div>
    </nav>
    <div class="card mt-3 mx-auto w-75 border-0">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="/images/${product.imagePath}" alt="Product's photo" class="img-fluid">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h3 class="card-title">${product.brand.name}</h3>
                    <h3 class="card-title">${product.name}</h3>
                    <p class="card-text">${product.description}</p>
                    <p class="card-text">Price: ${product.price}$</p>
                    <p class="card-text"> Amount: ${product.amount}</p>
                </div>
                <hr>
                <form action="/orders/make" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
                    </#if>
                    <label for="amount">Choose the amount:</label>
                    <input hidden id="amount" name="productId" value="${product.id}">
                    <select class="form-select mt-2 py-2 w-50" aria-label=".form-select-sm example" name="amount">
                        <option selected value="1">1</option>
                        <#list 2..product.amount as x>
                            <option value="${x}">${x}</option>
                        </#list>
                    </select>
                    <button class="btn btn-outline-success ml-3" type="submit">Add to basket</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>