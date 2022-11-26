/**
 * searchedShow.js
 */
let app = new Vue({
    el: "#app",
    data: {
        urlBase: "http://localhost:8080/",
        id: null,
        userAccountId: null,
        username: null,
        password: null,
        userEmail: null,
        userSex: null,
        userClass: null,
        userStatement: null,
        createTime: null,
        updateTime: null,
        userSearch: null,//搜索输入框输入值
        searchedShow: null,//返回值结果
        nowPage: 1,
        hasNext: null,
    },
    methods: {
        click_search: function () {
            //打开searchedShow页面并传入参数
            window.open("searchedShow.html" + "?content=" + this.userSearch);
        },
    }
});

/**
 * DOM加载完执行查询操作
 * @type {number}
 */
let page = 1, pageSize = 10;
const readyState = document.readyState;
if(readyState === "complete") {
    //DOM加载完成
    let searchData = window.local.url.split("?").split("=")[1];
    app.data.userSearch = searchData;
    //使用四参数get函数
    /**
     * 搜索功能暂定
     */
    /*
    let res = post(app.urlBase + "query/", page, pageSize, app.userSearch);
    app.searchedShow = res.search;
     */


    /**
     *  li添加点击切换事件
     */
    let lis = document.querySelector("#app").querySelectorAll("li");
    for(let i = 0; i < lis.length; i++){
        lis[i].addEventListener("click", function (){
            window.location.open(app.searchedShow[i]);
        });
    }
}



