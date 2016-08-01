/*
* 设计器私有的配置说明 
* 一
* UE.kenFormDesignUrl  插件路径
* 
* 二
*UE.getEditor('myFormDesign',{
*          toolken:true,//是否显示，设计器的清单 tool
*/
UE.kenFormDesignUrl = 'kendesign';

UE.registerUI('componentsDialog',function(editor,uiName){

    var dialog = new UE.ui.Dialog({
        iframeUrl:'/kensite/information/showPageList.do',
        editor:editor,
        name:uiName,
        title:"表单元素",
        cssRules:"width:600px;height:300px;",
        buttons:[
            {
                className:'edui-okbutton',
                label:'确定',
                onclick:function () {
                    dialog.close(true);
                }
            },
            {
                className:'edui-cancelbutton',
                label:'取消',
                onclick:function () {
                    dialog.close(false);
                }
            }
        ]});

    var btn = new UE.ui.Button({
        name:'dialogbutton' + uiName,
        title:'dialogbutton' + uiName,
        cssRules :'background-position: -500px 0;',
        onclick:function () {
            dialog.render();
            dialog.open();
        }
    });

    return btn;
});