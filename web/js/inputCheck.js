
function loginCheck() {
    var username = document.getElementById("user.username").value;
    var password = document.getElementById("user.password").value;
    if(username === ""){
        alert("请输入用户名！");
    }
    else if(username.length<=3||username.length>=15){
        alert("用户名长度不正确！");
    }
    else if(password === ""){
        alert("请输入密码！");
    }
    else{
        document.loginForm.submit();
    }
}

function ChgPwCheck() {
    var username = document.getElementById("user.username").value;
    var oldPassword = document.getElementById("user.password").value;
    var newPasswordOne = document.getElementById("user.userPasswordOne").value;
    var newPasswordTwo = document.getElementById("re-password").value;
    if(username === ""){
        alert("请输入用户名！");
    }
    else if(username.length!==3){
        alert("用户名长度不正确！");
    }
    else if(oldPassword === "" || newPasswordOne ==="" || newPasswordTwo === ""){
        alert("所有密码均不能为空！");
    }
    else if(oldPassword === newPasswordOne){
        alert("原密码和新密码不能相同！")
    }
    else if(newPasswordOne !== newPasswordTwo){
        alert("两次密码输入不相同");
    }
    else{
        document.form1.action = "ChgPw.action";
    }
}