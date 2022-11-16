//请求
function get(url)
{//js不能重载,但是可以根据参数个数判读执行操作
    //所以直接传参即可
    let ares = null;

    if(arguments.length === 1)
    {//普通请求,参数:url
        axios({
            method: 'get',
            url: url + '?' + "username=" + _this.username + "&" + "password=" + _this.password,
        }).then(res => {
            console.log(res);
            ares = res;
        }, err => {
            console.log(err)
        })
    }
    else
    {//页面展示请求参数:url, page, pageSize, id
        axios({
            method: 'get',
            url: url + "?" + "page=" + arguments[1] + "&pageSize=" + arguments[2] + "&id=" + arguments[3],
        }).then(res=>{
            console.log(res);
            ares = res;
        },err=>{
            console.log(err);
        })
    }
    return ares;
}
//加密请求
async  function post(url,records)
{
    console.log(url)
    let ares = null;
    const _this = records
    await axios({
        method: "post",
        url: url,
        data: {
            userName: _this.username,
            userPassword: _this.password
        }
    }).then(res=>{
        console.log("axios中：",res);
        ares = res;
    },err=>{
        console.log(err);
    })
    console.log("要返回时：",ares)
     return  ares;
}//加密请求
async function register_post(url,_this)
{
    let ares = null;
    await axios({
        method: "post",
        url: url,
        data: {
            userName: _this.username,
            userPassword: _this.password
        }
    }).then(res=>{
        console.log(res);
        ares = res;

    },err=>{
        console.log(err);
    })
    return ares;
}
//更新
function put(url)
{
    let ares = null;
    axios({
        method: 'put',
        url: url,
        data: {
            username: arguments[1],
            password: arguments[2]
        }
    }).then(res=>{
        console.log(res);
        ares = res;
    },err=>{
        console.log(err)
    })
    return ares;
}
//删除
function del(url)
{
    let ares = res;
    axios({
        method: 'delete',
        url: url,
    }).then(res=>{
        console.log(res);
        ares = res;
    },err=>{
        console.log(err)
    })
    return res;
}
//展示主页面
function showMyTopic(url, page, pageSize)
{
    axios({
        url: url + "?page=" + page + "&pageSize=" + pageSize,
        method: 'get',
    }).then(res => {
        _this.dataList = res.dataList;
    }, err => {
        console.log(err)
    })

}

//跳转index.html后获取主信息数组，将本地数组赋值，然后v-model、v-for绑定
function showList(url)
{
    let reply;
    axios({
        url: url,
        method: 'get',
    }).then(res => {
        //获取结果
        reply = res;
    }, err => {
        console.log(err)
    });
    _this.dataList = reply.dataList;
}

//register页面校验成功后的请求注册并接收注册结果
function register(url)
{
    axios({
        url: url,
        method: 'get',
    }).then(res => {
        return res.code === 1;
    }, err => {
        console.log(err)
    });
}

/*页面data模板
let app = new Vue({
    data: {
        serialVersionUID: null,
        id: null,
        userAccountId: null,
        userName: null,
        userPassword: null,
        userEmail: null,
        userSex: null,
        userClass: null,
        userStatement: null,
        createTime: null,
        updateTime: null,
    }
})
*/
