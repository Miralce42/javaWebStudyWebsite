/**
 * Created by Vove on 2017/5/26.
 * 保存提交作业
 */
var choiceNum;
var completionNum;
function setNum(ch,com) {
    choiceNum=ch;
    completionNum=com;
}
function saveHomework() {
    $('#action').attr("value","save");
    document.form.submit();
}

function commitHomework() {
    if(!checkHomework()){
        return;
    }
    $('#action').attr("value","commit");
    if(confirm("确认提交?")) {
        document.form.submit();
    }
}
function checkHomework() {
    for(var i=1;i<choiceNum;i++){
        var choice=$("input[name=choice_"+i+"][type='radio']:checked");//
        if(typeof (choice.val())==="undefined"){
            return !!confirm("选择题"+i+"未做,是否提交作业");
        }
    }
    for(i=1;i<completionNum;i++){
        var text=document.getElementById("completion_answer_"+i);
        if(text.value.trim()===""){
            if(confirm("还有填空题未做,是否提交作业")){
                return true;
            }else {
                text.focus();
                return false;
            }
        }
    }
    return true;
}