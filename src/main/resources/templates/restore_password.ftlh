<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="${.locale}">
<head>
    <meta charset="UTF-8">
    <title>Online Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
</head>
<body>
    <div class="w-50 mx-auto text-center">
        <h3 class="mt-3"><@spring.message "restore-password.title"/></h3>
        <#if !email??>
            <form action="/client/restore" method="post" class="mb-3">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
                </#if>
                <div class="mb-3">
                    <label for="email" class="form-label"><@spring.message "restore-password.message"/></label>
                    <input type="email" id="email" class="form-control" name="email" aria-describedby="emailHelp" placeholder="<@spring.message "restore-password.placeholder"/>" required>
                </div>
                <button type="submit" class="btn btn-success login-button mt-2"><@spring.message "restore-password.button-get-password"/></button>
            </form>
        <#else>
            <div class="mb-3" style="font-size: 18px">
                <p><@spring.message "restore-password.email"/>: ${email}</p>
                <hr>
                <p><@spring.message "restore-password.message-remember"/>:</p>
                <p style="font-size: 24px; font-weight: bold">${password}</p>
                <span><@spring.message "restore-password.message-warning"/></span>
            </div>
        </#if>
        <div class="text-center">
            <a href="/client/login" class="btn btn-success" style="width: 10%"><@spring.message "restore-password.button.back"/></a>
        </div>
    </div>

</body>
</html>