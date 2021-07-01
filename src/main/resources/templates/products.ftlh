<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="${.locale}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-sm navbar-light bg-light">
        <div class="container-fluid">
            <form action="/products" method="get">
                <button class="border-0 bg-transparent" style="font-size: 28px" type="submit">ONLINE STORE</button>
            </form>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 mr-3">
                <li class="nav-item mx-5">
                    <form action="/products/filter" method="get">
                        <button class="btn active px-4 mr-5 btn-outline-success" type="submit"><@spring.message "button.search"/></button>
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
                                <button class="btn btn-outline-success active px-4" type="submit"><@spring.message "button.logout"/></button>
                            </form>
                        </li>
                    <#else>
                        <li class="nav-item">
                            <a href="/logout" class="btn btn-outline-success active px-4"><@spring.message "button.logout"/></a>
                        </li>
                    </#if>
                <#else>
                    <li class="nav-item">
                        <a href="/client/login" class="btn active px-4 btn-outline-success"><@spring.message "button.login"/></a>
                    </li>
                </#if>
            </ul>
        </div>
    </nav>
    <div class="row mx-auto mt-5 justify-content-center">
        <#list products as product>
            <div class="card mb-3 mx-2 col-sm-12 col-md-5 col-lg-5 col-xl-3" style="max-width: 540px">
                <div class="row g-0">
                    <div class="col-md-4">
                        <img src="/images/${product.imagePath}" alt="Product's photo" class="img-fluid">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h4 class="card-title">${product.brand.name}</h4>
                            <h5 class="card-title">${product.name}</h5>
                            <p class="card-text">${product.description}</p>
                            <form action="/products/${product.id}">
                                <button class="btn btn-outline-success" type="submit"><@spring.message "button.info"/></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>
    <div class="text-center">
    <#if pages.pageNumber=0>
        <a href="/products?size=${pages.getPageSize()}&page=${pages.pageNumber+1}" type="button" class="btn btn-success">Next Page</a>
        <#elseif pages.pageNumber=pages.getPageSize()>
        <a href="/products?size=${pages.getPageSize()}&page=${pages.pageNumber-1}" type="button" class="btn btn-success">Previous Page</a>
        <#else>
        <a href="/products?size=${pages.getPageSize()}&page=${pages.pageNumber-1}" type="button" class="btn btn-success">Previous Page</a>
        <a href="/products?size=${pages.getPageSize()}&page=${pages.pageNumber+1}" type="button" class="btn btn-success">Next Page</a>
    </#if>
    </div>

    <form action="/products" method="get" class="mt-5">
        <select class="form-select mt-2 py-2" style="width: 49.5%" aria-label=".form-select-sm example" name="lang">
            <option selected value=""><@spring.message "label.lang"/></option>
            <option value="" disabled>-------</option>
            <option value="ru_RU">Русский</option>
            <option value="">English(USA)</option>
        </select>
        <button type="submit" class="btn btn-success mt-3"><@spring.message "button.language-change.text"/></button>
    </form>
</body>
</html>