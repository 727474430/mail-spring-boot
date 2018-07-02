$("ul").on("click", "li", function (e) {
    e.preventDefault();
    $(this).siblings().removeClass("active");
    $(this).addClass("active");
    resetForm();
    // 选择性显示输入框
    var text = $(this).text();
    if (text == "注册") {
        $("#email").show();
        $("#password").show();
        $("#confirmPassword").show();
    } else if (text == "登录") {
        $("#email").show();
        $("#password").show();
        $("#confirmPassword").hide();
    }
});

/**
 * 重置表单
 */
function resetForm() {
    $("form")[0].reset();
}

$("#subBtn").click(function (e) {
    var text = $("li.active").find("a").html();
    if (text == "注册") {
        registered();
    } else if (text == "登录") {
        login();
    }
});

/**
 * 注册
 */
function registered() {
    $.post("/login/registered", $("form").serialize(), function (data) {
        if (data['code'] == "success") {
            layer.alert(data['message'], {icon: 1, title: "提示", closeBtn: 0});
        } else {
            layer.alert(data['message'], {icon: 2, title: "提示", closeBtn: 0});
        }
        console.log(data)
        console.log(typeof(data))
    }, "json");
}

/**
 * 登录
 */
function login() {
    $.post("/login/login", $("form").serialize(), function (data) {
        if (data['code'] == "success") {
            layer.alert(data['message'], {icon: 1, title: "提示", closeBtn: 0});
        } else {
            layer.alert(data['message'], {icon: 2, title: "提示", closeBtn: 0});
        }
    }, "json");
}

