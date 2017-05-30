/**
 * Created by mac on 2017/5/24.
 */
$
(function(){
    /*
     * 鼠标点击，该元素包括该元素之前的元素获得样式,并给隐藏域input赋值
     * 鼠标移入，样式随鼠标移动
     * 鼠标移出，样式移除但被鼠标点击的该元素和之前的元素样式不变
     * 每次触发事件，移除所有样式，并重新获得样式
     * */
    var stars = $('.stars');
    var Len = stars.length;
    //遍历每个评分的容器
    for(i=0;i<Len;i++){
        //每次触发事件，清除该项父容器下所有子元素的样式所有样式
        function clearAll(obj){
            obj.parent().children('i').removeClass('on');
        }
        stars.eq(i).find('i').click(function(){
            var num = $(this).index();
            clearAll($(this));
            //当前包括前面的元素都加上样式
            $(this).addClass('on').prevAll('i').addClass('on');
            //给隐藏域input赋值
            $(this).siblings('input').val(num);
        });
        stars.eq(i).find('i').mouseover(function(){
            var num = $(this).index();
            clearAll($(this));
            //当前包括前面的元素都加上样式
            $(this).addClass('on').prevAll('i').addClass('on');
        });
        stars.eq(i).find('i').mouseout(function(){
            clearAll($(this));
            //触发点击事件后input有值
            var score = $(this).siblings('input').val();
            for(i=0;i<score;i++){
                $(this).parent().find('i').eq(i).addClass('on');
            }
        });
    }
})
