<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="${.locale}">
<head>
    <meta charset="UTF-8">
    <title>Online Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
    <div class="w-50 text-center mx-auto">
        <h3><@spring.message "invalid.login.message"/></h3>
    </div>
    <div class="text-center mt-4">
        <form action="/client/login" method="get">
            <button type="submit" class="btn btn-success"><@spring.message "invalid.login.back"/></button>
        </form>
    </div>
</body>
</html>