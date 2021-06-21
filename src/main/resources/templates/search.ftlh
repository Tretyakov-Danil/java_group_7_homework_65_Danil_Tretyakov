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
                    <a class="nav-link active" aria-current="page" href="/orders">Orders</a>
                </li>
                <li class="nav-item">
                    <form action="/products/filter" method="get">
                        <button class="btn active px-4 btn-outline-success" type="submit">Search</button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>

    <form class="mx-auto w-75 mt-3" action="/products/filter/result" method="get" modelAttribute="filter">
            <input type="text" class="form-control" name="name" placeholder="Enter the name of the product">
            <div class="text-center">
                <select class="form-select mt-2 py-2" style="width: 49.5%" aria-label=".form-select-sm example" name="brand">
                    <option selected value="">Brand</option>
                    <option value="Apple">Apple</option>
                    <option value="Samsung">Samsung</option>
                    <option value="Xiaomi">Xiaomi</option>
                </select>
                <select class="form-select mt-2 py-2" style="width: 49.5%" aria-label=".form-select-sm example" name="type">
                    <option selected value="">Type</option>
                    <option value="SMARTPHONE">SMARTPHONE</option>
                    <option value="CASE">CASE</option>
                    <option value="HEADSET">HEADSET</option>
                </select>
            </div>
        <div class="mt-2 mx-auto text-center">
        <button type="submit" class="btn btn-outline-success px-4">Search</button>
        </div>
    </form>
    <#if products?size=0>
        <div>Type something to find it</div>
    <#else>
        <#if products?size=0>
            <div class="text-center fs-4">Sorry, we didn't find anything.</div>
        <#else>
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
        </#if>
    </#if>
</body>
</html>