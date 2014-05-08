$(function () {
    var othervalid = [];
    //默认不允许立即提交
    othervalid[0] = false;
    othervalid[1] = false;
$("input,select,textarea").not("[type=submit]").jqBootstrapValidation({otherValidResult : othervalid});
$('#un').blur(function(){

    if($.trim($(this).val()) == ''){
        return false;
    }
    var v1= $('.help-block').html();
if( v1.indexOf("邮箱地址格式不正确") < 0 ){
    var aj1 = $.post("s-a.do",$("#un,input[type=hidden][name=tcs110]").serialize(),function(result) {
        $.each(result, function(i, obj){
            if(obj.f == 'd'){
                othervalid[0] = false;
                alert('非法请求！');
                return false;
            }
            if(obj.f == 'n'){
                othervalid[0] = false;
                $('#un').next('.help-block').html('<ul style=\'color: #c09853\' role=\'alert\'><li>该邮箱已注册！</li></ul>');
            }
            if(obj.f == 'e'){
                othervalid[0] = false;
                $('#un').next('.help-block').html('<ul style=\'color: #c09853\' role=\'alert\'><li>出现异常，请重试！</li></ul>');
            }
            if(obj.f == 'in'){
                othervalid[0] = false;
                $('#un').next('.help-block').html('<ul style=\'color: #c09853\' role=\'alert\'><li>邮箱不能为空！</li></ul>');
            }
            if(obj.f == 'y'){
                othervalid[0] = true;
            }
           // $('input[type=hidden][name=tcs110]').val(obj.C);
            //返回结果只有一条，直接返回
            return false;
        });

    },"json").fail(function() { othervalid[0] = false;alert("发生网络错误，请重试！"); });
}

});


    $('#ym').blur(function(){

        if($.trim($(this).val()) == ''){
            return false;
        }
        var aj1 = $.post("s-i-c.do",$("#ym,input[type=hidden][name=tcs110]").serialize(),function(result) {
            $.each(result, function(i, obj){
                if(obj.f == 'd'){
                    othervalid[1] = false;
                    alert('非法请求！');
                    return false;
                }
                if(obj.f == 'n'){
                    othervalid[1] = false;
                    $('#ym').next('.help-block').html('<ul style=\'color: #c09853\' role=\'alert\'><li>验证码输入错误！</li></ul>');
                }
                if(obj.f == 'in'){
                    othervalid[1] = false;
                    $('#ym').next('.help-block').html('<ul style=\'color: #c09853\' role=\'alert\'><li>验证码不能为空！</li></ul>');
                }
                if(obj.f == 'y'){
                    othervalid[1] = true;
                }
                //$('input[type=hidden][name=tcs110]').val(obj.C);
                //返回结果只有一条，直接返回
                return false;
            });

        },"json").fail(function() { othervalid[1] = false;alert("发生网络错误，请重试！"); });

    });

    $('#imgbtcode').click(function(){
        $(this).attr('src', '/forest/c/c-i.do?' + Math.floor(Math.random()*100) );
    });

} );
