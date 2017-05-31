/**
 * Created by Vove on 2017/5/29.
 */
function checkScore(num) {
    for (var i = 1; i < num; i++) {
        var operation = $('#operation_' + i);
        if (operation.val() === "" || !isNum(operation.val())) {
            alert("请输入正确的分数");
            operation.focus();
            return;
        }
    }

    if (confirm("确认提交？")) {
        document.form.submit();
    }
}

function isNum(content) {
    var re = /\D/;   //判断字符串是否为数字  判断正整数 /^[1-9]+[0-9]*]*$/
    return !re.test(content);
}
