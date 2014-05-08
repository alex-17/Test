$(function () {
    var othervalid = [];
    if( $('#ym').length > 0 ){
        othervalid[0] = false;
    } else {
        //如果不存在这个元素，那么取消验证码的验证
        othervalid[0] = true;
    }

    $("input,select,textarea").not("[type=submit]").jqBootstrapValidation({otherValidResult : othervalid});

    $('form').on('blur','#ym',function(event){

   // $('#ym').blur(function(){

        if($.trim($(this).val()) == ''){
            return false;
        }
        var aj1 = $.post("/forest/l/s-l-i-c.do",$("#ym,input[type=hidden][name=tcs110]").serialize(),function(result) {
            $.each(result, function(i, obj){
                if(obj.f == 'd'){
                    othervalid[0] = false;
                    alert('非法请求！');
                    return false;
                }
                if(obj.f == 'n'){
                    othervalid[0] = false;
                    $('#ym').next('.help-block').html('<ul style=\'color: #c09853\' role=\'alert\'><li>验证码输入错误！</li></ul>');
                }
                if(obj.f == 'in'){
                    othervalid[0] = false;
                    $('#ym').next('.help-block').html('<ul style=\'color: #c09853\' role=\'alert\'><li>验证码不能为空！</li></ul>');
                }
                if(obj.f == 'y'){
                    othervalid[0] = true;
                }
                //$('input[type=hidden][name=tcs110]').val(obj.C);
                //返回结果只有一条，直接返回
                return false;
            });

        },"json").fail(function() { othervalid[0] = false;alert("发生网络错误，请重试！"); });

    });

    $('form').on('click','#imgbtcode',function(event){
   // $('#imgbtcode').click(function(){
        $('#imgbtcode').attr('src', '/forest/c/c-l-i.do?' + Math.floor(Math.random()*100) );
    });

});