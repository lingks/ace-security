/* 
 * drag 1.0
 * create by tony@jentian.com
 * date 2015-08-18
 * 拖动滑块
 */
(function($){
    $.fn.drag = function(options){


        var x, drag = this, isMove = false, defaults = {
        };
        var options = $.extend(defaults, options);
        //添加背景，文字，滑块
        var html = '<div class="drag_bg"></div>'+
                    '<div class="drag_text" onselectstart="return false;" unselectable="on">拖动滑块验证</div>'+
                    '<div class="handler handler_bg"></div>';
        this.append(html);
        
        var handler = drag.find('.handler');
        var drag_bg = drag.find('.drag_bg');
        var text = drag.find('.drag_text');
        var maxWidth = drag.width() - handler.width();  //能滑动的最大间距
        
        //鼠标按下时候的x轴的位置
        handler.mousedown(function(e){
            isMove = true;
            x = e.pageX - parseInt(handler.css('left'), 10);


        });
        
        //鼠标指针在上下文移动时，移动距离大于0小于最大间距，滑块x轴位置等于鼠标移动距离
        $(document).mousemove(function(e){
            var _x = e.pageX - x;
            if(isMove){
                if(_x > 0 && _x <= maxWidth){
                    handler.css({'left': _x});
                    drag_bg.css({'width': _x});
                }else if(_x > maxWidth){  //鼠标指针移动距离达到最大时清空事件
                    dragOk();
                }
            }
        }).mouseup(function(e){
            isMove = false;
            var _x = e.pageX - x;
            if(_x < maxWidth){ //鼠标松开时，如果没有达到最大距离位置，滑块就返回初始位置
                handler.css({'left': 0});
                drag_bg.css({'width': 0});
                //$(".btnSubmit").unbind('click');
            }
        });

        console.log($(".btnSubmit").css("background"))
        if($(".btnSubmit").css("background") != '#5670BF'){
            $(".btnSubmit").off('click');
        }
        //清空事件
        function dragOk(){
            handler.removeClass('handler_bg').addClass('handler_ok_bg');
            text.text('验证通过');
            drag.css({'color': '#fff'});
            $(".btnSubmit").css({"background":"#5670BF"});
            handler.unbind('mousedown');
            $(document).unbind('mousemove');
            $(document).unbind('mouseup');

            $("#btnSubmit").on('click',function(){

                layui.use('layer', function(){

                    var $ = layui.jquery, layer = layui.layer;
                    if($("#username").val() == ""){
                        layer.msg('账号不能为空！');
                        return;
                    }

                    if($("#password").val() == ""){
                        layer.msg('密码不能为空！');
                        return;
                    }

                    $.ajax({
                        type: "GET",
                        url: base.url+"/blog/api/user/login",
                        contentType: "application/json; charset=utf-8",
                        dataType: 'jsonp',
                        jsonp: 'callback',
                        data:{
                            "username":$("#username").val(),
                            "password":$("#password").val()
                        },
                        success: function (data, status, xhr) {
                            if(data.rel == true){
                                if(data.result.length > 0){
                                    console.log(data.result[0])
                                    localStorage.setItem("user",JSON.stringify(data.result[0]));
                                    console.log(JSON.stringify(data.result[0]))
                                    //window.location.href="/center";

                                }else{
                                    layer.msg("账号或者密码不对")
                                }
                            }else{
                                layer.msg("登录失败")
                            }
                        },
                        error: function (xhr, status, error) {
                            console.log(error)
                        }
                    });
                })

            })



            $("#btnSubmit2").on('click',function(){

                layui.use('layer', function(){

                    var $ = layui.jquery, layer = layui.layer;
                    if($("#username").val() == ""){
                        layer.msg('账号不能为空！');
                        return;
                    }

                    if($("#password").val() == ""){
                        layer.msg('密码不能为空！');
                        return;
                    }

                    if($("#password2").val() == ""){
                        layer.msg('确认密码不能为空！');
                        return;
                    }else if($("#password2").val() != $("#password").val()){
                        layer.msg("密码不一致")
                    }
                    $.ajax({
                        url: base.url + '/blog/api/user/register',
                        type: 'POST',


                        dataType: 'json',
                        data:{
                            "username":$("#username").val(),
                            "password":$("#password").val()
                        },
                        success: function (data, status, xhr) {
                            if(data.rel == false){
                                layer.msg("用户名已经存在，请重新注册");
                                return;
                            }
                            layer.msg("注册成功,等待跳转。。。", { icon: 1 });


                            console.log(JSON.stringify(data.result[0]))
                            localStorage.setItem("user",JSON.stringify(data.result[0]));
                            setTimeout(function(){
                                window.location.href="/center";
                            },3000);
                        }
                    })

                })

            })
        }



    };
})(jQuery);


